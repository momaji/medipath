package data_mangement;

public class SeperateChainingHash<Key, Value> {
	private int m;
	private SequentialSearchST<Key, Value>[] st;
	
	public SeperateChainingHash() {
		this(2943);
	}

	@SuppressWarnings("unchecked")
	private SeperateChainingHash(int i) {
		this.m = i;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for(int k = 0; k < m; k++)
			st[k] = new SequentialSearchST<Key, Value>();	
	}
	
	private int hash(Key key) {return (int) key % m;}
	
	public Iterable<Object> get(Key key) {return st[hash(key)].get(key);}
	
	public void put(Key key, Value val) {st[hash(key)].put(key, val);}
}

