package intialize_data;

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
		
		Node(Key key, Value val, int n, boolean color){
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
	
	void flipColours(Node h) {
		h.colour = RED;
		h.left.colour = BLACK;
		h.right.colour = BLACK;
	}
	
}
