package data_mangement;

import java.util.Iterator;
import java.util.Stack;

public class BSTHashServices {

	private final RedBlackBST<Integer, ProviderDataObject> tree;
	private final SeperateChainingHash<Integer, ProviderDataObject> table;
	
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
		allkeys = (Stack<Object>) this.tree.keys(0, 10000000);

		
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
		allkeys = (Stack<Object>) this.tree.keys(0, 10000);

		
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

	public Iterable<Object> getAddresses(Iterable<Object> allkeys) {
		Stack<Object> addresses = new Stack<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			addresses.add(((ProviderDataObject) m.next()).getProviderAddress());
		}
		return (Iterable<Object>) addresses;
	}

	public Iterable<Object> getCities(Iterable<Object> allkeys) {
		Stack<Object> cities = new Stack<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			cities.add(((ProviderDataObject) m.next()).getProviderCity());
		}
		return (Iterable<Object>) cities;
	}

	public Iterable<Object> getZips(Iterable<Object> allkeys) {
		Stack<Object> zips = new Stack<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			zips.add(((ProviderDataObject) m.next()).getProviderZip());
		}
		return (Iterable<Object>) zips;
	}

}
