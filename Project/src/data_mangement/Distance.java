package data_mangement;

import java.io.*;
import java.net.*;
import java.util.*;

public class Distance {
	
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
		setDistances(hospitals, originAddress,originZip);
		while(hospitals.size() != 0)
			System.out.println(hospitals.poll());
		//System.out.println(ReadExcel.combine.getCities(ReadExcel.combine.tree.keys(1040,5000)));

	}
	
	private static double getDistance(String originAddress, String originZip, String destAddress, String destZip ) {
		double distance = 0;
		ArrayList<String> data = getJSON(originAddress, originZip, destAddress, destZip);
		
		int index = 0;
		
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).length() > 9 && data.get(i).substring(1, 9).equals("distance")) {
				index = i;
				break;
			}
		}
		
		index++;
		int length = data.get(index).length();
		distance = Double.parseDouble(data.get(index).substring(8, length-4).replace(",", ""));
		return distance;
	}

	private static double getDistance(ProviderDataObject start, String destAddress, String destZip) {
		return getDistance(start.getProviderAddress(), start.getProviderZipStr(), destAddress, destZip);
	}
	
	private static double getDistance(ProviderDataObject start, ProviderDataObject dest) {
		return getDistance(start.getProviderAddress(), start.getProviderZipStr(), dest.getProviderAddress(), dest.getProviderZipStr());
	}
	
	private static void setDistances(Iterable<Object> hospitals, String destAddress, String destZip) {
		Iterator<Object> itr = hospitals.iterator();
		while(itr.hasNext()){
			ProviderDataObject temp = (ProviderDataObject) itr.next();
			temp.setDistance(getDistance(temp,destAddress,destZip));
		}
	}

	private static ArrayList<String> getJSON(String originAddress, String originZip, String destAddress, String destZip) {
		
		ArrayList<String> result = new ArrayList<String>();
		
		String data = "ERROR: Data could not be loaded";
		String address;
		
		String[] temp;
		temp = originAddress.split(" ");
		String newOA = temp[0];
		for(int i = 1; i < temp.length; i++) {
			newOA = newOA + "+" + temp[i];
		}
		
		temp = destAddress.split(" ");
		String newDA = temp[0];
		for(int i = 1; i < temp.length; i++) {
			newDA = newDA + "+" + temp[i];
		}
		
		address = "https://maps.googleapis.com/maps/api/directions/json?origin=" + newDA.toLowerCase() + ",+" + destZip + "&destination=" + newOA.toLowerCase() + ",+" + originZip;
		
		try {

			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			System.out.println("Sending GET request to URL: " + address);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.add(line.replaceAll("\\s+", ""));
			}
			rd.close();
			data = result.toString();

		}catch(Exception e) {
			System.out.println(data);
		}

		return result;
	}
}
