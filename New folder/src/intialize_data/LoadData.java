package intialize_data;

public class LoadData {
	public void Load() {
		
	}
	
	public void InsertData() {
		RedBlackBST tree = new RedBlackBST();
		for(int i = 0; i < data.length; i++) {
			tree.put(data[i][ZIPINDEX], data[i]);
		}
	}
}
