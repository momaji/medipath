package data_mangement;

import java.io.IOException;
import java.util.PriorityQueue;

public class ClientCode {
	public static void main(String[] args) {
		ReadExcel test = new ReadExcel();
		test.setInputFile("medipath.xls");
		
		try {
			test.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String originAddress = "McMaster University";
		String originZip = "L8S4L8";
		String state = "MA";
		
		//this gets all the objects of a certain procedure number in a zip code range, and sets the distance from the user for all objects matching the described conditions.
		PriorityQueue<Object> hospitals = (PriorityQueue<Object>) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(1040,1060),57, state);
		Distance.setDistances(hospitals, originAddress,originZip);
		while(hospitals.size() != 0)
			System.out.println(hospitals.poll());
		//System.out.println(ReadExcel.combine.getCities(ReadExcel.combine.tree.keys(1040,5000)));

	}
}
