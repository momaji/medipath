package data_mangement;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ClientCode {
	public static void main(String[] args){
		
		new ReadExcel("medipath.xls");
		
		String originAddress = "McMaster University";
		String originZip = "L8S4L8";
		String state = "MA";
		
		ArrayList<ProviderDataObject> locations= new ArrayList<ProviderDataObject>();
		
		//this gets all the objects of a certain procedure number in a zip code range, 
		//and sets the distance from the user for all objects matching the described conditions.
//<<<<<<< HEAD
		PriorityQueue<Object> hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(1040,1201),57);
		//Distance.setDistances(hospitals, originAddress,originZip);
		int count = 100;
		while(hospitals.size() != 0){
			((ProviderDataObject) hospitals.poll()).setDistance(count);
			count += 10;
		}
		
		hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(1040,1201),57);
		while(hospitals.size() != 0){
			locations.add((ProviderDataObject)hospitals.poll());
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
		
		BellmanFordSP path = new BellmanFordSP(map, index);
		
		//display(path.getPath(), locations, index);
		
		path.String();
		//System.out.println(map);
		//System.out.println(map.E());
		
	}
	
	public static void display(Edge[] path, ArrayList<ProviderDataObject> locations, int start) {
		int trace = start;
		for(int i = 0; i < locations.size(); i++) {
			System.out.println(locations.get(trace) + "\n");
			System.out.println(path[trace]);
		}
	}
}

		
		
