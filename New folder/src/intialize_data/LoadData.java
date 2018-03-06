package intialize_data;

public class LoadData {
	public void Load() {
		
	}
	
	/*public void InsertData() {
		RedBlackBST tree = new RedBlackBST();
		for(int i = 0; i < data.length; i++) {
			tree.put(data[i][ZIPINDEX], data[i]);
		}
	}*/
	
	public static void main(String[] args) {
		System.out.println(parseDouble("$30,2,3.07"));
	}
	
	public static double parseDouble(String a) {
		String b = a.substring(1);
		String c = b.replaceAll(",", "");
		return Double.parseDouble(c);
	}
}
