package data_mangement;

// https://www.youtube.com/watch?v=65igZdK9Vd4 (code is in description)

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;
//import java.util.Stack;
import java.util.Vector;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	private static final RedBlackBST<Integer, ProviderDataObject> tree = new RedBlackBST<Integer, ProviderDataObject>();
	private static final SeperateChainingHash<Integer, ProviderDataObject> table = new SeperateChainingHash<Integer, ProviderDataObject>();
	public static BSTHashServices combine = new BSTHashServices();

	private String inputFile;

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	private static Double parse(String string) {
		return Double.parseDouble((string).substring(1).replace(",", ""));
	}

	private static void begin() throws IOException {
		ReadExcel test = new ReadExcel();
		test.setInputFile("medipath.xls");
		test.read();
	}

	public void read() throws IOException {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// getCell is (column,row)

			// loop over first 10 column and lines
			for (int j = 1; j < 65536; j++) {

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

		combine = new BSTHashServices(tree, table);
	}

	public static void main(String[] args) throws IOException {

		begin();

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
		 */

		/*
		 * Stack<Object> allkeys = new Stack<Object>(); allkeys = (Stack<Object>)
		 * combine.tree.keys(); while (!allkeys.isEmpty()) { Iterator<Object> m =
		 * (combine.table.get((Integer) allkeys.pop())).iterator(); 
		 * while (m.hasNext()){
		 * Object element = m.next(); 
		 * // System.out.println(element); } 
		 * }
		 */

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

		Iterator<Object> itrObjects = (Iterator<Object>) combine.getACCs(tree.keys()).iterator();
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
		System.out.println(combine.getCheapestObject(1040));
		System.out.println(combine.getObject(77504, 60643.68));
		System.out.println(combine.getObject(77504, 60643.68).getDRGDefNum());
	}
}
