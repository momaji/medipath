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
		PriorityQueue<Object> hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(1040,1740),57);
		//Distance.setDistances(hospitals, originAddress,originZip);
		while(hospitals.size() != 0){
			((ProviderDataObject) hospitals.poll()).setDistance(100);
		}
		
		hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(1040,1740),57);
		while(hospitals.size() != 0){
			System.out.println(hospitals.poll());
		
		}
	}
}

		
		
