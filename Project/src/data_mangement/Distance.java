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
		String state = "AL";
		String destAddress;
		String destZip;
		
		//this gets all the objects of a certain procedure number in a zip code range, and iterates through it.
		PriorityQueue hospitals = (PriorityQueue) ReadExcel.combine.getHospitalsInRange(ReadExcel.combine.tree.keys(),57, state);
		Iterator<Object> itr =  hospitals.iterator();
		
		//ProviderDataObject[] temp = new ProviderDataObject[hospitals.size()];
		while(itr.hasNext()) {
			ProviderDataObject temp = (ProviderDataObject) ((itr.next()));
			temp.setDistance(getDistance( originAddress,  originZip,  temp.getProviderAddress(),  temp.getProviderZipStr() ));
		}
		Iterator<Object> itr2 =  hospitals.iterator();
		while(itr2.hasNext()) {
			System.out.println(itr2.next());
			System.out.println();
		}
			

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
			int r = conn.getResponseCode();
			System.out.println("Sending GET request to URL: " + address);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.add(line.replaceAll("\\s+", ""));
			}
			rd.close();
			data = result.toString();

		}catch(Exception e) {
			System.out.println("ERROR: Data could not be loaded");
		}

		return result;
	}
}
