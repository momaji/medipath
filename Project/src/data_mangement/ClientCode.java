package data_mangement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * The functions needed for the UI to interact with the rest of the java program 
 * @author Eoin Lynagh
 *
 */
public class ClientCode {
	public static void main(String[] args) throws IOException{
		
		ReadExcel test = new ReadExcel();
		test.read();
		
		String originAddress = "940 virginia avenue";
		String originZip = "46203";
		
		int[] prod = {57,57,292,207,39};
		
		/*PriorityQueue hospitials = (PriorityQueue) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(), 39 );
		while(!hospitials.isEmpty()){
			System.out.println(hospitials.poll());
		}*/
		
		//PriorityQueue hospitials = (PriorityQueue) ReadExcel.combine.getAll(ReadExcel.combine.tree.keys());
		//int i =0;
		//System.out.println(hospitials.size());
				
		//ShortPath(57,originAddress,originZip);
		
		//this gets all the objects of a certain procedure number in a zip code range, 
		//and sets the distance from the user for all objects matching the described conditions.
//<<<<<<< HEAD		
	}
	
	public static void display(WeightedEdge[] path, ArrayList<ProviderDataObject> locations, int start) {
		int trace = start;
		for(int i = 0; i < locations.size()-1; i++) {
			System.out.println(locations.get(trace) + "\n");
			trace = path[trace].from();
		}
		System.out.println(locations.get(trace));
	}
	
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
		
		
