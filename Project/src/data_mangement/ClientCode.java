package data_mangement;

import java.util.PriorityQueue;

public class ClientCode {
	public static void main(String[] args){
		
		new ReadExcel("medipath.xls");

		String originAddress = "McMaster University";
		String originZip = "L8S4L8";
		String state = "MA";
		
		//this gets all the objects of a certain procedure number in a zip code range, 
		//and sets the distance from the user for all objects matching the described conditions.
//<<<<<<< HEAD
		PriorityQueue<Object> hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(5000,7000),57);
		//Distance.setDistances(hospitals, originAddress,originZip);
		while(hospitals.size() != 0){
			((ProviderDataObject) hospitals.poll()).setDistance(100);
		}
		
		hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(5000,90000),57);
		while(hospitals.size() != 0){
			System.out.println(hospitals.poll());
		}
//=======
		
		/*PriorityQueue<Object> hospitals1 = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(), 57, state);
		//Distance.setDistances(hospitals, originAddress,originZip);
		while(hospitals1.size() != 0){((ProviderDataObject) hospitals1.poll()).setDistance(100);}
//>>>>>>> 86c9bd58cb2076becd6f991e13b1f53b87553a50
		
		hospitals1 = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(),57, state);
		while(hospitals1.size() != 0){System.out.println(hospitals1.poll());}*?
	}
	
	
	/*public static Iterable<Object> getRange(int key) {
		if()
	}*/
}}
