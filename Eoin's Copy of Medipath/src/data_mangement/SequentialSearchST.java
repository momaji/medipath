package data_mangement;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class SequentialSearchST<Key, Value> {
	private Node first;
	
	private class Node{
		Key key;
		Value val;
		Node next;
		
		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	public Iterable<Object> get(Key key) {
		Stack<Object> m = new Stack<Object>();
		for(Node x = first; x!= null; x = x.next) if(key.equals(x.key)) m.add(x.val);
		return m;
	}

	public void put(Key key, Value val) {
		for(Node x = first; x != null; x = x.next) {
			if(x.next == null && x != null) {
				x.next = new Node(key, val, null);
				return;
			}
		}
		first = new Node(key, val, first);
		
	}
	
	public int size() {
		int i = 0;
		for(Node x = first; x!= null; x = x.next) i++;
		return i;
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue =  new PriorityQueue<Key>();
		for(Node x = first; x != null; x = x.next) {
			queue.add(x.key);			
		}
		return queue;
	}
	
	public void delete(Key key) {
		for(Node x = first; x != null; x = x.next) {
			if(x.next.key.equals(key)) {
				x.next = x.next.next;
			}
		}	
	}
}
