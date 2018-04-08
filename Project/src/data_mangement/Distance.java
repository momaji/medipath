package data_mangement;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This Distance class holds all the methods which deal with the GET requests to the google maps API, and setting the distances between and origin address
 * and hospital object, and between hospital objects
 */
public class Distance {
	
	/**
	 * Function takes in the address and zip code  of an origin address and destination address and returns the distance between them according to the 
	 * distance set on google maps taking into account road travel. Does this by sending a request for a JSON file to the google maps API using the 
	 * format as it is saved in in the data objects, then searches through the JSON file for the specific string needed.
	 * @param originAddress - Origin address string
	 * @param originZip - Origin zip code string
	 * @param destAddress - Destination address string
	 * @param destZip - Destination zip code string
	 * @return Returns the distance in miles in double format
	 */
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

	/**
	 * Function takes in the address and zip code  of an origin address and destination hospital object and returns the distance between them, uses
	 * the first getDistance function and also reverses the origin and destination addresses so that the distance will always be returned in miles
	 * because all of the hospital objects are in the states. 
	 * @param start - Hospital object you are going to
	 * @param destAddress - Origin address string
	 * @param destZip - Origin zip code string
	 * @return Returns the distance in miles in double format
	 */
	public static double getDistance(ProviderDataObject start, String destAddress, String destZip) {
		return getDistance(start.getProviderAddress(), start.getProviderZipStr(), destAddress, destZip);
	}
	
	/**
	 * Function takes in the address and zip code  of an origin hospital object and destination hospital object and returns the distance between them, uses
	 * the first getDistance function and also reverses the origin and destination addresses so that the distance will always be returned in miles
	 * because all of the hospital objects are in the states. 
	 * @param start - Hospital object you are going to
	 * @param dest - Hospital object you are coming from
	 * @return Returns the distance in miles in double format
	 */
	public static double getDistance(ProviderDataObject start, ProviderDataObject dest) {
		return getDistance(start.getProviderAddress(), start.getProviderZipStr(), dest.getProviderAddress(), dest.getProviderZipStr());
	}
	
	/**
	 * Function takes in an iterable of hospital objects, and an origin address and zip code. Then sets the distance state of each hospital
	 * to the distance between the hospital location and the origin location using the get distance function. Again switching the origin
	 * and destination for the purposes of keeping the distance units in miles when sending the request to the Google API.
	 * @param start - Hospital object you are going to
	 * @param dest - Hospital object you are coming from
	 * @return Returns the distance in miles in double format
	 */
	public static void setDistances(Iterable<Object> hospitals, String destAddress, String destZip) {
		Iterator<Object> itr = hospitals.iterator();
		while(itr.hasNext()){
			ProviderDataObject temp = (ProviderDataObject) itr.next();
			temp.setDistance(getDistance(temp,destAddress,destZip));
		}
	}

	/**
	 * Function takes in the address and zip code of an origin location and destination location formatted in the same way the hospital objects
	 * are formatted. The function will then generate a url from the given information, add an API key string to the end of it. Then create a 
	 * url connection to send a get request to the google maps API with which a JSON file in the form of a string will be returned.
	 * @param originAddress - Origin address string
	 * @param originZip - Origin zip code string
	 * @param destAddress - Destination address string
	 * @param destZip - Destination zip code string
	 * @return Returns the JSON file in string format
	 */
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
		
		// + "&key=AIzaSyAd6Dl_urDCt6ockL52VaIm1_QC6vcwfuU"
		
		address = "https://maps.googleapis.com/maps/api/directions/json?origin=" + newDA.toLowerCase() + ",+" + destZip + "&destination=" + newOA.toLowerCase() + ",+" + originZip + "&key=AIzaSyAd6Dl_urDCt6ockL52VaIm1_QC6vcwfuU";
		
		try {

			URL url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			System.out.println("Sending GET request to URL: " + address);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				result.add(line.replaceAll("\\s+", ""));
			}
			input.close();
			data = result.toString();

		}catch(Exception e) {
			System.out.println(data);
		}

		return result;
	}
}
