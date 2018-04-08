package data_mangement;

// https://www.youtube.com/watch?v=65igZdK9Vd4 (code is in description)

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class ReadExcel {

	public ReadExcel(){
		try {
			read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final RedBlackBST<Integer, ProviderDataObject> tree = new RedBlackBST<Integer, ProviderDataObject>();
	private static final SeperateChainingHash<Integer, ProviderDataObject> table = new SeperateChainingHash<Integer, ProviderDataObject>();
	public static BSTHashServices combine = new BSTHashServices();


	private static Double parse(String string) {
		return Double.parseDouble((string).substring(1).replace(",", ""));
	}

	public void read() throws IOException {
			File file = new File("medipath.csv");
			try{
				Scanner inputStream = new Scanner(file);
				String data = inputStream.nextLine();
				int v = 0;
				while(inputStream.hasNext()){
					v++;
					data = inputStream.nextLine();
					//System.out.println(data);
					
					String[] values = data.split(",");
					
					//System.out.print(values.length+"/");
					/*System.out.println(values[0] + "/");
					System.out.println(values[1] + "/");
					System.out.println(values[2] + "/");
					System.out.println(values[3] + "/");
					System.out.println(values[4] + "/");
					System.out.println(values[5] + "/");*/
					//System.out.print(values[6]);
					/*System.out.println(values[7] + "/");
					System.out.println(values[8] + "/");
					System.out.println(values[9] + "/");
					System.out.println(values[10] + "/");
					System.out.println(values[11] + "/");*/
					//System.out.println();
					
					
					//6 8 9 10 11 need conversion int int double double double
					System.out.println(values.length);
					if (values.length == 12){
						int zip = Integer.parseInt(values[6]);
						int discharges = Integer.parseInt(values[8]);
						int providerID = Integer.parseInt(values[1]);
						//System.out.println("checked");
						//getting rid of commas and dollar sign for 9,10,11 and converting to type int
						//double ACC = Double.parseDouble((cell9.getContents()).substring(1).replace(",", ""));
						//double ATP = Double.parseDouble((cell10.getContents()).substring(1).replace(",", ""));;
						//double AMP = Double.parseDouble((cell11.getContents()).substring(1).replace(",", ""));;
						
						
						double ACC = Double.parseDouble(values[9].substring(1));
						double ATP = Double.parseDouble(values[10].substring(1));
						double AMP = Double.parseDouble(values[11].substring(1));
						
						ProviderDataObject a = new ProviderDataObject(values[0], providerID, values[2], values[3], values[4], values[5], zip, values[7], discharges, ACC, ATP, AMP); 
						System.out.println(a);
						tree.put(a.getProviderZip(),a);
						table.put(a.getProviderZip(),a);
					}
					
					if (values.length == 13){
						System.out.println(v);
						int zip = Integer.parseInt(values[7]);
						int discharges = Integer.parseInt(values[9]);
						int providerID = Integer.parseInt(values[1]);
						//System.out.println("checked13");
						//getting rid of commas and dollar sign for 9,10,11 and converting to type int
						//double ACC = Double.parseDouble((cell9.getContents()).substring(1).replace(",", ""));
						//double ATP = Double.parseDouble((cell10.getContents()).substring(1).replace(",", ""));;
						//double AMP = Double.parseDouble((cell11.getContents()).substring(1).replace(",", ""));;
						
						double ACC = Double.parseDouble(values[10].substring(1));
						double ATP = Double.parseDouble(values[11].substring(1));
						double AMP = Double.parseDouble(values[12].substring(1));
						
						ProviderDataObject a = new ProviderDataObject(values[0], providerID, values[2], values[3]+values[4], values[4], values[5], zip, values[7], discharges, ACC, ATP, AMP); 
						System.out.println(a);
						tree.put(a.getProviderZip(),a);
						table.put(a.getProviderZip(),a);
					}
					
					if (values.length == 14){
						int zip = Integer.parseInt(values[8]);
						int discharges = Integer.parseInt(values[10]);
						int providerID = Integer.parseInt(values[1]);
						//System.out.println("checked13");
						//getting rid of commas and dollar sign for 9,10,11 and converting to type int
						//double ACC = Double.parseDouble((cell9.getContents()).substring(1).replace(",", ""));
						//double ATP = Double.parseDouble((cell10.getContents()).substring(1).replace(",", ""));;
						//double AMP = Double.parseDouble((cell11.getContents()).substring(1).replace(",", ""));;
						
						double ACC = Double.parseDouble(values[11].substring(1));
						double ATP = Double.parseDouble(values[12].substring(1));
						double AMP = Double.parseDouble(values[13].substring(1));
						
						ProviderDataObject a = new ProviderDataObject(values[0], providerID, values[2], values[3]+values[4]+values[5], values[4], values[5], zip, values[7], discharges, ACC, ATP, AMP); 
						System.out.println(a);
						tree.put(a.getProviderZip(),a);
						table.put(a.getProviderZip(),a);
					}
					
					if (values.length == 15){
						int zip = Integer.parseInt(values[9]);
						int discharges = Integer.parseInt(values[11]);
						int providerID = Integer.parseInt(values[3]);
						//System.out.println("checked13");
						//getting rid of commas and dollar sign for 9,10,11 and converting to type int
						//double ACC = Double.parseDouble((cell9.getContents()).substring(1).replace(",", ""));
						//double ATP = Double.parseDouble((cell10.getContents()).substring(1).replace(",", ""));;
						//double AMP = Double.parseDouble((cell11.getContents()).substring(1).replace(",", ""));;
						
						double ACC = Double.parseDouble(values[12].substring(1));
						double ATP = Double.parseDouble(values[13].substring(1));
						double AMP = Double.parseDouble(values[14].substring(1));
						
						ProviderDataObject a = new ProviderDataObject(values[0]+values[1], providerID, values[3], values[4]+values[5]+values[6], values[7], values[8], zip, values[10], discharges, ACC, ATP, AMP); 
						System.out.println(a);
						tree.put(a.getProviderZip(),a);
						table.put(a.getProviderZip(),a);
					}
					
					
					
				}
				inputStream.close();
				combine = new BSTHashServices(tree, table);
				
			}	catch(FileNotFoundException e){
				e.printStackTrace();
			}
		/*File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			System.out.println(sheet.getRows());
			// getCell is (column,row)

			// loop over first 10 column and lines
			for (int j = 1; j < sheet.getRows(); j++) {

				Cell cell0 = sheet.getCell(0, j);
				Cell cell1 = sheet.getCell(1, j);
				Cell cell2 = sheet.getCell(2, j);
				Cell cell3 = sheet.getCell(3, j);
				Cell cell4 = sheet.getCell(4, j);
				Cell cell5 = sheet.getCell(5, j);
				Cell cell6 = sheet.getCell(6, j);
				Cell cell7 = sheet.getCell(7, j);
				Cell cell8 = sheet.getCell(8, j);
				Cell cell9 = sheet.getCell(9, j);
				Cell cell10 = sheet.getCell(10, j);
				Cell cell11 = sheet.getCell(11, j);

				// getting rid of commas and dollar sign for 9,10,11 and converting to type int
				double cell9x = parse(cell9.getContents());
				double cell10x = parse(cell10.getContents());
				double cell11x = parse(cell11.getContents());
				Integer cell1x = Integer.parseInt(cell1.getContents());
				Integer cell6x = Integer.parseInt(cell6.getContents());
				Integer cell8x = Integer.parseInt(cell8.getContents());

				ProviderDataObject a = new ProviderDataObject(cell0.getContents(), cell1x, cell2.getContents(),
						cell3.getContents(), cell4.getContents(), cell5.getContents(), cell6x, cell7.getContents(),
						cell8x, cell9x, cell10x, cell11x);

				tree.put(a.getProviderZip(), a);
				table.put(a.getProviderZip(), a);
			}
		} catch (BiffException e) {
			return;
		}

		combine = new BSTHashServices(tree, table);*/
	}
	/*
	public static void main(String[] args) throws IOException {

		begin();
		//this gets all the objects of a certain procedure number in a zip code range, and iterates through it.
		Iterator<Object> itr = combine.getsDRGobjects(combine.tree.keys(52032,67890),203).iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}

		/*
		 * int i, size = 0; double average = 0; Stack<Long> times = new Stack<Long>();
		 * for (i = 0; i < 20; i++) { 
		 * System.out.println("timing"); 
		 * long start = System.nanoTime();
		 * long end = System.nanoTime(); 
		 * times.push(((end - start) / (1000000000))); }
		 * 
		 * size = times.size(); System.out.println("pop"); 
		 * while (!times.empty()) {average += times.pop(); } 
		 * System.out.println(average /= size);
		

		/*
		 * Stack<Object> allkeys = new Stack<Object>(); 
		 * allkeys = (Stack<Object>) combine.tree.keys();
		 * while (!allkeys.isEmpty()) { 
		 * Iterator<Object> m = (combine.table.get((Integer) allkeys.pop())).iterator(); 
		 * while (m.hasNext()){
		 * Object element = m.next(); 
		 * // System.out.println(element); } 
		 * }
		 

		// best way to get cheapest objects in a range
		Iterator<Object> all = tree.keys(1040, 5600).iterator(); // change keys to the range(int, int) or to an
		while (all.hasNext()) {
			ProviderDataObject cheapest = combine.getCheapestObject((int) all.next());
			System.out.println(cheapest);
			// all.next();
		}

		PriorityQueue<Object> allZips = (PriorityQueue<Object>) combine.getZips(tree.keys());
		Vector<Object> allObjects = (Vector<Object>) combine.returnObjects(tree.keys());

		while (true) {
			// System.out.println(allObjects.firstElement());
			allObjects.removeElementAt(0);
			if (allObjects.isEmpty()) {
				break;
			}
		}

		// combine.printIterable(1040,1040);
		// System.out.println(combine.getACCs(tree.keys()));

		Iterator<Object> itrObjects = (Iterator<Object>) combine.getATPs(tree.keys()).iterator();
		Iterator<Object> itrZips = (Iterator<Object>) combine.getZips(tree.keys()).iterator();
		int objects = 0;
		int zips = 0;
		while (itrZips.hasNext()) {
			objects++;
			zips++;
			itrZips.next();
			itrObjects.next();
			// System.out.println("ZIP: " + itrZips.next() + ", ACC + itrObjects.next());
			// note these are unrelated info points.
		}

		while (itrObjects.hasNext()) {
			objects++;
			itrObjects.next();
			// System.out.println("ZIPs: complete, ACC: "+ itrObjects.next());
		}

		System.out.println("");
		System.out.println("#Total Zip Codes: " + zips + ", #Total ProviderDataObjects: " + objects);
		System.out.println("#Total Zip Codes (from queue): " + allZips.size());
		System.out.println("");
		System.out.println(combine.returnObjects(tree.keys(1040, 10040)));
		System.out.println(combine.getCheapestObject(1040));
		System.out.println(combine.getObject(77504, 60643.68));
		System.out.println(combine.getObject(77504, 60643.68).getDRGDefNum());
	}*/
}



