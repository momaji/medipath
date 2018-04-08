package data_mangement;

import java.util.PriorityQueue;
import java.util.Queue;
/**
 * 
 * @author lynag
 *
 * @param <Key> - the key of the of object
 * @param <Value> - the object that you are storing
 */
public class SequentialSearchST<Key, Value> {
	
	private Node first;
	private class Node{
		Key key;
		Value val;
		Node next;
		/**
		 * Constructor for nodes in the SSST
		 * @param key - key of object to be inserted
		 * @param val - val of object to be inserted
		 * @param next - the next node in the Linked List
		 */
		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	/**
	 * Gets the objects in the SSST that have the given key
	 * @param key - the key of the object you are looking for
	 * @return a priority queue of all the values with the given key
	 */
	public Iterable<Object> get(Key key) {
		PriorityQueue<Object> m = new PriorityQueue<Object>();
		for(Node x = first; x!= null; x = x.next) if(key.equals(x.key)) m.add(x.val);
		return (Iterable<Object>) m;
	}
	/**
	 * puts a new object into the SSST
	 * @param key - the key of the new object
	 * @param val - the value of the new object
	 */
	public void put(Key key, Value val) {
		for(Node x = first; x != null; x = x.next) {
			if(x.next == null && x != null) {
				x.next = new Node(key, val, null);
				return;
			}
		}
		first = new Node(key, val, first);
		
	}
	/**
	 * gets the size of the linked list
	 * @return the size of the linked list
	 */
	public int size() {
		int i = 0;
		for(Node x = first; x!= null; x = x.next) i++;
		return i;
	}
	/**
	 * gets the keys of the SSST
	 * @return a queue of all the keys
	 */
	public Iterable<Key> keys(){
		Queue<Key> queue =  new PriorityQueue<Key>();
		for(Node x = first; x != null; x = x.next) {
			queue.add(x.key);			
		}
		return queue;
	}
	/**
	 * removes a node from the linked list
	 * @param key - the key of the node to be deleted
	 */
	public void delete(Key key) {
		for(Node x = first; x != null; x = x.next) {
			if(x.next.key.equals(key)) {
				x.next = x.next.next;
			}
		}	
	}

}
