package data_mangement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Class to read in the medipath csv file and turn each line into a provider data object
 * @author moziah San Vicente
 *
 */
public class ReadExcel {

	

	private static final RedBlackBST<Integer, ProviderDataObject> tree = new RedBlackBST<Integer, ProviderDataObject>();
	private static final SeperateChainingHash<Integer, ProviderDataObject> table = new SeperateChainingHash<Integer, ProviderDataObject>();
	public static BSTHashServices combine = new BSTHashServices();

	/**
	 * Parses a string that is an integer with a comma to take the comma out
	 * @param string the string that represents the integer
	 * @return the double representation of the string value
	 */
	private static Double parse(String string) {
		return Double.parseDouble((string).substring(1).replace(",", ""));
	}

	/**
	 * function to read in the data from the csv and turn each line into a provider data object, then put each
	 * object into the BST hash table as represented as the static variable combine
	 * @throws IOException
	 */
	public static void read() throws IOException {
			File file = new File("medicare2.csv");
			try{
				Scanner inputStream = new Scanner(file);
				String data = inputStream.nextLine();
				int v = 0;
				while(inputStream.hasNext()){
					v++;
					data = inputStream.nextLine();
					
					String[] values = data.split(",");
				
					
					//All of the if statements are needed because our csv file had lines with varying 
					//amounts of comma's due to commas being in certain names for cities and procedures
					
					if (values.length == 12){
						String procedure;
						char quote = '"';
						procedure = values[0]; 
						if (values[0].charAt(0) == quote)
							procedure = values[0].substring(1);
						
						int zip = Integer.parseInt(values[6]);
						int discharges = Integer.parseInt(values[8]);
						int providerID = Integer.parseInt(values[1]);
					
						
						double ACC = Double.parseDouble(values[9].substring(1));
						double ATP = Double.parseDouble(values[10].substring(1));
						double AMP = Double.parseDouble(values[11].substring(1));
						
						ProviderDataObject a = new ProviderDataObject(procedure, providerID, values[2], values[3], values[4], values[5], zip, values[7], discharges, ACC, ATP, AMP); 
			
						tree.put(a.getProviderZip(),a);
						table.put(a.getProviderZip(),a);
					}
					
					if (values.length == 13){
						String procedure;
						char quote = '"';
						procedure = values[0]; 
						if (values[0].charAt(0) == quote)
							procedure = values[0].substring(1);
						
		
						int zip = Integer.parseInt(values[7]);
						int discharges = Integer.parseInt(values[9]);
						int providerID = Integer.parseInt(values[1]);
						
						double ACC = Double.parseDouble(values[10].substring(1));
						double ATP = Double.parseDouble(values[11].substring(1));
						double AMP = Double.parseDouble(values[12].substring(1));
						
						ProviderDataObject a = new ProviderDataObject(procedure, providerID, values[2], values[3]+values[4], values[4], values[5], zip, values[7], discharges, ACC, ATP, AMP); 

						tree.put(a.getProviderZip(),a);
						table.put(a.getProviderZip(),a);
					}
					
					if (values.length == 14){	
						String procedure;
						char quote = '"';
						procedure = values[0]; 
						if (values[0].charAt(0) == quote)
							procedure = values[0].substring(1);
						
						
						int zip = Integer.parseInt(values[8]);
						int discharges = Integer.parseInt(values[10]);
						int providerID = Integer.parseInt(values[1]);
					
						double ACC = Double.parseDouble(values[11].substring(1));
						double ATP = Double.parseDouble(values[12].substring(1));
						double AMP = Double.parseDouble(values[13].substring(1));
						
						ProviderDataObject a = new ProviderDataObject(procedure, providerID, values[2], values[3]+values[4]+values[5], values[7], values[7], zip, values[9], discharges, ACC, ATP, AMP); 

						tree.put(a.getProviderZip(),a);
						table.put(a.getProviderZip(),a);
					}
						
						
					
					
					if (values.length == 15){
						String procedure;
						char quote = '"';
						procedure = values[0]; 
						if (values[0].charAt(0) == quote)
							procedure = values[0].substring(1);
						
						int zip = Integer.parseInt(values[9]);
						int discharges = Integer.parseInt(values[11]);
						int providerID = Integer.parseInt(values[1]);
						
						double ACC = Double.parseDouble(values[12].substring(1));
						double ATP = Double.parseDouble(values[13].substring(1));
						double AMP = Double.parseDouble(values[14].substring(1));
						
						ProviderDataObject a = new ProviderDataObject(procedure, providerID, values[2], values[3]+values[4]+values[5]+values[6], values[7], values[8], zip, values[10], discharges, ACC, ATP, AMP); 
						tree.put(a.getProviderZip(),a);
						table.put(a.getProviderZip(),a);
					}
					
					
				}
				
				inputStream.close();
				combine = new BSTHashServices(tree, table);
				
			}	
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
	}
}



