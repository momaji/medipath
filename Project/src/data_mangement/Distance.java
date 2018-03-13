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
		
		Vector Objects = (Vector) ReadExcel.combine.returnObjects(ReadExcel.combine.tree.keys());
		Iterator itr = Objects.iterator();
		
		ProviderDataObject obj1 = (ProviderDataObject) (((PriorityQueue) itr.next()).poll()); //gets the cheapest object at lowest zip
		ProviderDataObject obj2 = (ProviderDataObject) (((PriorityQueue) itr.next()).poll()); //gets the cheapest object at the second lowest zip
		
		while(itr.hasNext()) {
			obj2 = (ProviderDataObject) (((PriorityQueue) itr.next()).poll());
		}
		
		System.out.println(obj1);
		System.out.println(obj2);
		
		String originAddress = obj1.getProviderAddress();
		String originZip = obj1.getProviderZipStr();
		String destAddress = obj2.getProviderAddress();
		String destZip = obj2.getProviderZipStr();
		
		double x = getDistance( originAddress,  originZip,  destAddress,  destZip );
		System.out.println("DISTANCE: " + x + " miles");

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
		distance = Double.parseDouble(data.get(index).substring(8, length-4));
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
		
		address = "https://maps.googleapis.com/maps/api/directions/json?origin=" + newOA.toLowerCase() + ",+" + originZip + "&destination=" + newDA.toLowerCase() + ",+" + destZip;
		
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
