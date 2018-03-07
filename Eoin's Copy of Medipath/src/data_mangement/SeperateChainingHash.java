package data_mangement;

public class SeperateChainingHash<Key, Value> {
	private final int m = 2943;
	
	private SequentialSearchST<Key, Value>[] st;

	@SuppressWarnings("unchecked")
	public SeperateChainingHash() {
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for(int k = 0; k < m; k++)
			st[k] = new SequentialSearchST<Key, Value>();	
	}
	
	private int hash(Key key) {return (int) key % m;}
	
	public Iterable<Object> get(Key key) {return st[hash(key)].get(key);}
	
	public void put(Key key, Value val) {st[hash(key)].put(key, val);}
}

