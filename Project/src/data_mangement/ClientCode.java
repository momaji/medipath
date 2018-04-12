package data_mangement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * The functions needed for the UI to interact with the rest of the java program 
 * @author Eoin Lynagh, Alex Kingsland
 *
 */
public class ClientCode {
	/**
	 * This main is for testing
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		ReadExcel test = new ReadExcel();
		test.read();
		
		String originAddress = "940 virginia avenue";
		String originZip = "46203";
		
		int[] prod = {57,57,292,207,39};
		
		
	}
	/**
	 * Prints out a given path from a generated array list
	 * @param path the path that you want to print out
	 * @param locations a list of objects that the path connects
	 * @param start the index of the first object in your path in your arraylist
	 */
	public static void display(WeightedEdge[] path, ArrayList<ProviderDataObject> locations, int start) {
		int trace = start;
		for(int i = 0; i < locations.size()-1; i++) {
			System.out.println(locations.get(trace) + "\n");
			trace = path[trace].from();
		}
		System.out.println(locations.get(trace));
	}
	/**|
	 * Sets the distances for the closest cheapest hospitals that perform a procedure (or procedures) from an address and zip code provided by the user
	 * @param prod the procedure number that you are looking for a hospital to perform
	 * @param address the users address
	 * @param zip the zip code of the user
	 */
	public static void ShortPath(int[] prod, String address, String zip) {
		int[] keys = new int[2];
		getKeys(keys, Integer.parseInt(zip));
		ArrayList<ProviderDataObject> locations= new ArrayList<ProviderDataObject>();
		PriorityQueue<Object> hospitals = new PriorityQueue<Object>();
		int count = 100;
		for(int i : prod) {
			hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(keys[0], keys[1]),i);
			ProviderDataObject cheapest = (ProviderDataObject) hospitals.poll();
			//(cheapest).setDistance(Distance.getDistance(cheapest,address,zip));
			(cheapest).setDistance(count++);
			locations.add(cheapest);
			

		}
		
		WeightedGraph map = GraphData.fillGraph(locations);
		double min = locations.get(0).getDistance();
		int index = 0;
		for(int i = 1; i < locations.size(); i++) {
			if(locations.get(i).getDistance() < min) {
				min = locations.get(i).getDistance();
				index = i;
			}
		}
		
		MinSpanningPath path = new MinSpanningPath(map, index);
		display(path.getPath(), locations, index);	
	}
	/**
	 * returns the closest cheapest hospital to the user that does a particular procedure
	 * @param prod the procedure number
	 * @param address the address of the user
	 * @param zip the zip code
	 * @return the closest cheapest hospital 
	 */
	public static String ShortPath(int prod, String address, String zip) {
		int[] keys = new int[2];
		String out = new String();
		getKeys(keys, Integer.parseInt(zip));
		ArrayList<ProviderDataObject> locations= new ArrayList<ProviderDataObject>();
		PriorityQueue<Object> hospitals = new PriorityQueue<Object>();
		int count = 100;
		hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(keys[0], keys[1]),prod);
		
		while(!hospitals.isEmpty()) {
			ProviderDataObject cheapest = (ProviderDataObject) hospitals.poll();
			out += cheapest.toString();
		}
		
		System.out.println(out);
		return out;
	}
	/**
	 * Puts the user zip codes in a range to allow the program to search zip codes in a circular range
	 * @param keys an array that will be filled with zip codes representing a reasonable range around the client
	 * @param zip the client zip code
	 */
	public static void getKeys(int[] keys, int zip) {
		if(zip - 400 < 1040) {
			keys[0] = 1040;
		}else {
			keys[0] = zip - 400;
		}
		
		if(zip + 400 > 99801) {
			keys[1] = 99801;
		}else {
			keys[1] = zip + 400;
		}
	}
	
}
		
		
