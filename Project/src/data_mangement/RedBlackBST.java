package data_mangement;

import java.util.Stack;
/**
 * the red black bst is used to store all the zip codes.
 * this makes it easy to prevent hash misses as we can get any keys in a range easily from the redblack-bst,
 * and because we hash objects using these keys it makes them accessible.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	/**
	 * This class is used within the tree, it is what the tree is made of
	 */
	private class Node { 
		Key key;
		Value val;
		Node left, right;
		int n;
		boolean colour;

		Node(Key key, Value val, int n, boolean colour) {
			this.key = key;
			this.val = val;
			this.n = n;
			this.colour = colour;
		}

	}
	/**
	 * This function checks if a node is red or black
	 * @param x - the node that you are checking the colour
	 * @return - true if x is a red node
	 */
	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.colour == RED;
	}
	/**
	 * This function rotates a node to the left in order to maintain tree balance
	 * @param x - the node that you are rotating
	 * @return - the rotated node
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.colour = h.colour;
		h.colour = RED;
		x.n = h.n;
		h.n = 1 + size(h.left) + size(h.right);
		return x;
	}
	/**
	 * This function rotates a node to the right in order to maintain tree balance
	 * @param x - the node that you are rotating
	 * @return - the rotated node
	 */
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.colour = h.colour;
		h.colour = RED;
		x.n = h.n;
		h.n = 1 + size(h.left) + size(h.right);
		return x;
	}
	/**
	 * This function switches the colour of a node and it's children
	 * @param - x, the node that you are changing the colour of
	 */
	private void flipColours(Node h) { 
		h.colour = RED;
		h.left.colour = BLACK;
		h.right.colour = BLACK;
	}
	/**
	 * This function gets the size of the red-black tree
	 * @return - the size of the tree
	 */
	public int size() { 
		return size(root);
	}
	/**
	 * This private function is used by the public size to compute the size of the tree
	 * @param x - the node that you are computing the size of
	 * @return - the size of x
	 */
	private int size(Node x) { //private function for 
		if (x == null)
			return 0;
		return x.n;
	}
	/**
	 * This function is used to add new nodes in the tree
	 * @param key - the key of the node that you are inserting (zip code)
	 * @param val - the value of the key that you are inserting.
	 */
	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.colour = BLACK;
	}
	/**
	 * This private function is used to add new nodes in the tree by recursively looking for its location in the tree
	 * @param h - the root of the tree, where you start searching for its spot in the tree
	 * @param key - the key of the node that you are inserting (zip code)
	 * @param val - the value of the key that you are inserting.
	 * @return - the root of the tree, now that it contains the new node
	 */
	private Node put(Node h, Key key, Value val) {
		if (h == null)
			return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if (cmp < 0)
			h.left = put(h.left, key, val);
		if (cmp > 0)
			h.right = put(h.right, key, val);
		else
			h.val = val;

		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.right) && isRed(h.left))
			flipColours(h);

		h.n = 1 + size(h.right) + size(h.left);
		return h;
	}
	/**
	 * This function returns the node with the matching key
	 * @param key - the key of the node that you are looking for
	 * @return - the node with the key
	 */
	public Value get(Key key) {
		return get(root, key);
	}
	/**
	 * This private function returns the node with the matching key by comparing it to all nodes recursively
	 * @param key - the key of the node that you are looking for
	 * @param x - the node that you are comparing the key to
	 * @return - the node with that key
	 */
	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		if (cmp > 0)
			return get(x.right, key);
		return x.val;
	}
	/**
	 * This function returns the all keys in a range, useful with our hash table
	 * @param lo - smallest value that you want a key you are returning to have
	 * @param hi - largest value that you want a key you are returning to have 
	 * @return - a stack of keys 
	 */
	public Iterable<Object> keys(Key lo, Key hi) {
		Stack<Object> stack = new Stack<Object>();
		keys(root, stack, lo, hi);
		return (Iterable<Object>) stack;
	}

	/**
	 * This function returns the keys of all nodes, useful with our hash table
	 * @return - all keys in the tree in a stack
	 */
	public Iterable<Object> keys() {
		Stack<Object> stack = new Stack<Object>();
		keys(root, stack);
		return (Iterable<Object>) stack;
	}
	/**
	 * This private function returns the all keys in a range recursively
	 * @param x - the node that you are currently comparing against
	 * @param stack - the stack of keys you are adding to
	 * @param lo - smallest value that you want a key you are returning to have
	 * @param hi - largest value that you want a key you are returning to have 
	 * @return - a stack of keys 
	 */
	private void keys(Node x, Stack<Object> stack, Key lo, Key hi) {// adds keys to created stack
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);

		if (cmplo < 0)
			keys(x.left, stack, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			stack.add(x.key);
		if (cmphi > 0)
			keys(x.right, stack, lo, hi);

	}
	/**
	 * This private function returns the all keys in the tree recursively
	 * @param x - the node that you are currently comparing against
	 * @param stack - the stack of keys you are adding to
	 * @return - a stack of keys 
	 */
	private void keys(Node x, Stack<Object> stack) {
		Integer lo = 0;
		Integer hi = 1000000;
		if (x == null)
			return;
		int cmplo = lo.compareTo((Integer) x.key);
		int cmphi = hi.compareTo((Integer) x.key);

		if (cmplo < 0)
			keys(x.left, stack);
		if (cmplo <= 0 && cmphi >= 0)
			stack.add(x.key);
		if (cmphi > 0)
			keys(x.right, stack);

	}
	/**
	 * This function returns the root of the tree
	 * @return - the root of the tree
	 */
	public Node getRoot() {
		return root;
	}
}
