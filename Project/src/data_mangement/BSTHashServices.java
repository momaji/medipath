package data_mangement;

import java.util.Iterator;
import java.util.PriorityQueue;
/**
 * Services that use both the hash table and the tree together
 * @author lynag
 */
public class BSTHashServices {

	public final RedBlackBST<Integer, ProviderDataObject> tree;
	public final SeperateChainingHash<Integer, ProviderDataObject> table;
	public BSTHashServices() {
		this.tree = null;
		this.table = null;
	}
	/** 
	 * This is the constructor for our combination services
	 * @param tree - the red black bst that you are using in conjunction with the hash table
	 * @param table - the hash table that you are using in conjunction with the red-black bst
	 */
	public BSTHashServices(RedBlackBST<Integer, ProviderDataObject> tree,
			SeperateChainingHash<Integer, ProviderDataObject> table) {
		this.tree = tree;
		this.table = table;
	}
	
	/**
	 * this gets all the hospitals in a range that perform a specific procedure
	 * @param allkeys - the range of zip codes that you want to search in for hospitals
	 * @param procedureNum - the procedure that you are looking for
	 * @return - an iterable priority queue (sorted by a funciton of price and distance) of all the hospitals meeting the criteria.
	 */
	public Iterable<Object> getHospitalsInRange(Iterable<Object> allkeys, int procedureNum) {
		boolean exists = false;
		PriorityQueue<Object> hospitals = new PriorityQueue<Object>();
		Iterator<Object> itr = allkeys.iterator();
		while (itr.hasNext()) {
			Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
			while(m.hasNext()) {
				ProviderDataObject that = (ProviderDataObject) m.next();
				if(that.getDRGDefNum() == procedureNum) {
						hospitals.add(that);
						exists = true;		
				}
			}
				
		}
		if(!exists) System.out.println("No hospitals in this in that range perform that procedure");
		
		return (Iterable<Object>) hospitals;
		
	}
	
	/**
	 * public Iterable<Object> getAll(Iterable<Object> allkeys) {
	 * PriorityQueue<Object> hospitals = new PriorityQueue<Object>();
	 * Iterator<Object> itr = allkeys.iterator();
	 * while (itr.hasNext()) {
	 * Iterator<Object> m = (this.table.get((Integer) itr.next())).iterator();
	 * while(m.hasNext()) {
	 * ProviderDataObject that = (ProviderDataObject) m.next();
	 * hospitals.add(that);	
	 * }
	 * }
	 * return (Iterable<Object>) hospitals;
	 * }
	 */
}
