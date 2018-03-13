package data_mangement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;

public class BSTHashServices {

	public final RedBlackBST<Integer, ProviderDataObject> tree;
	public final SeperateChainingHash<Integer, ProviderDataObject> table;
	
	public BSTHashServices() {
		this.tree = null;
		this.table = null;
	}
	
	public BSTHashServices(RedBlackBST<Integer, ProviderDataObject> tree,
			SeperateChainingHash<Integer, ProviderDataObject> table) {
		this.tree = tree;
		this.table = table;
	}

	public void printSeperate() {
		Stack<Object> allkeys = new Stack<Object>();
		allkeys = (Stack<Object>) this.tree.keys();
		
		while(!allkeys.isEmpty()){
			Iterator<Object> m = (this.table.get((Integer) allkeys.pop())).iterator();
			while (m.hasNext()) {
				Object element = m.next();
				System.out.println(element);
			}
		}
	}

	public void printSeperate(int lo, int hi) {
		Stack<Object> allkeys = new Stack<Object>();
		allkeys = (Stack<Object>) this.tree.keys(lo, hi);

		
		while(!allkeys.isEmpty()){
			Iterator<Object> m = (this.table.get((Integer) allkeys.pop())).iterator();
			while (m.hasNext()) {
				Object element = m.next();
				System.out.println(element);
			}
		}
	}

	public void printSeperate(Iterable<Object> allkeys) {
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			while (m.hasNext()) {
				System.out.println(m.next());
			}
		}
	}

	public void printIterable() {
		Stack<Object> allkeys = new Stack<Object>();
		allkeys = (Stack<Object>) this.tree.keys();

		while(!allkeys.isEmpty()){
			System.out.println(this.table.get((Integer) allkeys.pop()));
		}
	}

	public void printIterable(int lo, int hi) {
		Stack<Object> allkeys = new Stack<Object>();
		allkeys = (Stack<Object>) this.tree.keys(lo, hi);

		while(!allkeys.isEmpty()) {
			System.out.println(this.table.get((Integer) allkeys.pop()));
		}
	}

	public void printIterable(Iterable<Object> allkeys) {
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			System.out.println(this.table.get((Integer) itr.next()));
		}
	}
	
	public Iterable<Object> returnObjects(Iterable<Object> allkeys) {
		Iterator<Object> itr = allkeys.iterator();
		Vector<Object> fetch = new Vector<Object>();
		while (itr.hasNext()) {
			fetch.add(this.table.get((Integer) itr.next()));
		}
		return fetch;
	}
	
	public ProviderDataObject getObject(int key, double ACC) {
		int i = 0;
		PriorityQueue<Object> queue = (PriorityQueue<Object>) this.table.get(key);
		Object[] array = this.getACCsArray(singleton(key));
		while(i++ < (Arrays.binarySearch(array, ACC))) {
			queue.poll();
		}
		return (ProviderDataObject) queue.poll();
	}
	
	public ProviderDataObject getCheapestObject(int key) {
		PriorityQueue<Object> queue = (PriorityQueue<Object>) this.getACCs(singleton(key));
		return this.getObject(key, (double) queue.poll());
	}
	
	private Iterable<Object> singleton(Object key) {
		Stack<Object> stack = new Stack<Object>();
		stack.push(key);
		return stack;
	}
	
	public Iterable<Object> getAddresses(Iterable<Object> allkeys) {
		PriorityQueue<Object> addresses = new PriorityQueue<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			addresses.add(((ProviderDataObject) m.next()).getProviderAddress());
		}
		return (Iterable<Object>) addresses;
	}

	public Iterable<Object> getCities(Iterable<Object> allkeys) {
		PriorityQueue<Object> cities = new PriorityQueue<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			cities.add(((ProviderDataObject) m.next()).getProviderCity());
		}
		return (Iterable<Object>) cities;
	}

	public Iterable<Object> getZips(Iterable<Object> allkeys) {
		PriorityQueue<Object> zips = new PriorityQueue<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			zips.add(((ProviderDataObject) m.next()).getProviderZip());
		}
		return (Iterable<Object>) zips;
	}

	public Iterable<Object> getProviderNames(Iterable<Object> allkeys) {
		PriorityQueue<Object> zips = new PriorityQueue<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			zips.add(((ProviderDataObject) m.next()).getProviderID());
		}
		return (Iterable<Object>) zips;
	}
	
	public Iterable<Object> getACCs(Iterable<Object> allkeys) {
		PriorityQueue<Object> zips = new PriorityQueue<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			while(m.hasNext())
				zips.add(((ProviderDataObject) m.next()).getACC());
		}
		return (Iterable<Object>) zips;
	}
	
	private Object[] getACCsArray(Iterable<Object> allkeys) {
		PriorityQueue<Object> zips = new PriorityQueue<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			while(m.hasNext())
				zips.add(((ProviderDataObject) m.next()).getACC());
		}
		Object[] finale = zips.toArray();
		Arrays.sort(finale);
		return finale;
	}
	
	public Iterable<Object> getZips(int lo, int hi) {
		PriorityQueue<Object> zips = new PriorityQueue<Object>();
		for(int i = lo; i < hi; i++) {
			zips.add(table.get(i));
		}
		return (Iterable<Object>) zips;
	}
	
	public Iterable<Object> getsDRGnum(Iterable<Object> allkeys) {
		PriorityQueue<Object> zips = new PriorityQueue<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			while(m.hasNext())
				zips.add(((ProviderDataObject) m.next()).getACC());
		}
		return (Iterable<Object>) zips;
	}
}
