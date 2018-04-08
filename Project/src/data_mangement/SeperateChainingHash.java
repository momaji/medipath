package data_mangement;
/**
 * This is a separate chaining hash table that hashes DataProviderObjects by their zip code.
 * It hashes them as a series of linked lists and returns them as linked lists as well.
 */
public class SeperateChainingHash<Key, Value> { 
	private final int m = 2943; 
	private SequentialSearchST<Key, Value>[] st; 

	@SuppressWarnings("unchecked") 
	public SeperateChainingHash() {
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m]; //initalizes lists
		for(int k = 0; k < m; k++) //creates a list in each list
			st[k] = new SequentialSearchST<Key, Value>();	
	}
	/**
	 * hashes the objects by their zip codes
	 * @param key - the zip code of the object you are hashing
	 * @return the hashed value of the key
	 */
	private int hash(Key key) {return (Integer) key % m;}
	
	/**
	 * returns the list corresponding to a key
	 * @param key - the key of the list that you are looking for
	 * @return - the list in the hash table with the specified key
	 */
	public Iterable<Object> get(Key key) {return st[hash(key)].get(key);} //returns a list
	/**
	 * Puts a new object into the hash table
	 * @param key - the key of the the object that is going in
	 * @param val - the object that you are putting into the table
	 */
	public void put(Key key, Value val) {st[hash(key)].put(key, val);} //puts an object into the hash table

}

