package data_mangement;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ClientCode {
	public static void main(String[] args){
		
		new ReadExcel("medipath.xls");
		
		String originAddress = "McMaster University";
		String originZip = "L8S4L8";
		int[] prod = {57,57,292,207,39};
		
		ReadExcel.combine.printSeperate(ReadExcel.combine.tree.keys());
		ShortPath(prod,originAddress,originZip);
		
		//this gets all the objects of a certain procedure number in a zip code range, 
		//and sets the distance from the user for all objects matching the described conditions.
//<<<<<<< HEAD		
	}
	
	public static void display(Edge[] path, ArrayList<ProviderDataObject> locations, int start) {
		int trace = start;
		for(int i = 0; i < locations.size()-1; i++) {
			System.out.println(locations.get(trace) + "\n");
			trace = path[trace].from();
		}
		System.out.println(locations.get(trace));
	}
	
	public static void ShortPath(int[] prod, String address, String zip) {
		ArrayList<ProviderDataObject> locations= new ArrayList<ProviderDataObject>();
		PriorityQueue<Object> hospitals = new PriorityQueue<Object>();
		int count = 100;
		for(int i : prod) {
			hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(),i);
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
}
		
		
