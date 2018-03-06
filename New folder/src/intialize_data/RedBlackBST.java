package intialize_data;

import java.util.PriorityQueue;

public class RedBlackBST<Key extends Comparable<Key>, Value>{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	
	private class Node{	
		Key key;
		Value val;
		Node left, right;
		int n;
		boolean colour;
		
		Node(Key key, Value val, int n, boolean colour){
			this.key = key;
			this.val = val;
			this.n = n;
			this.colour = colour;
		}
		
	}
	private boolean isRed(Node x) {
			if(x==null) return false;
			return x.colour == RED;
	}
	
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
	
	private void flipColours(Node h) {
		h.colour = RED;
		h.left.colour = BLACK;
		h.right.colour = BLACK;
	}
	
	public int size() {return size(root);}
	
	private int size(Node x) {
		if(x == null) return 0;
		else return x.n;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.colour = BLACK;
	}
	
	private Node put(Node h, Key key, Value val) {
		if(h == null) return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if(cmp < 0) h.left = put(h.left, key, val);
		if(cmp > 0) h.right = put(h.right, key, val);
		else h.val = val;
		
		if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if(isRed(h.right) && isRed(h.left)) flipColours(h);
		
		h.n = 1 + size(h.right) + size(h.left);
		return h;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node x, Key key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return get(x.left, key);
		if(cmp > 0) return get(x.right, key);
		else return x.val;
	}
	
	public static void main(String[] args) {
		ProviderDataObject a = new ProviderDataObject("aa","aa", "aa", "aa", "aa", 5, "aa", 4, 3, 2, 1);
		ProviderDataObject b = new ProviderDataObject("aa","aa", "aa", "aa", "aa", 6, "aa", 4, 3, 2, 1);
		ProviderDataObject c = new ProviderDataObject("aa","aa", "aa", "aa", "aa", 7, "aa", 4, 3, 2, 1);
		ProviderDataObject d = new ProviderDataObject("aa","aa", "aa", "aa", "aa", 8, "aa", 4, 3, 2, 1);
		ProviderDataObject e = new ProviderDataObject("aa","aa", "aa", "aa", "aa", 900, "aa", 4, 3, 2, 1);
		RedBlackBST tree = new RedBlackBST();
		tree.put(a.getProviderZip(), a);
		tree.put(b.getProviderZip(), b);
		tree.put(c.getProviderZip(), c);
		tree.put(d.getProviderZip(), d);
		tree.put(e.getProviderZip(), e);
		tree.print(tree.root);
		PriorityQueue aa = new PriorityQueue();
		aa = (PriorityQueue) tree.keys(a.getProviderZip(), e.getProviderZip());
		System.out.println(aa.contains(e));
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		PriorityQueue<Key> queue = new PriorityQueue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	
	private void keys(Node x, PriorityQueue<Key> queue, Key lo, Key hi) {
		if(x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		
		if(cmplo < 0) keys(x.left, queue, lo, hi);
		if(cmplo <= 0 && cmphi >= 0) queue.add(x.key);
		if(cmplo > 0) keys(x.right, queue, lo, hi);
		
	}
	
	private void print(Node x) {
		if(x == null) return;
		print(x.left);
		System.out.println(x.key);
		print(x.right);
	}
}
