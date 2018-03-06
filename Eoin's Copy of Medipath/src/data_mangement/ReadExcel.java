package data_mangement;

// https://www.youtube.com/watch?v=65igZdK9Vd4 (code is in description)

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	static final RedBlackBST<Integer, ProviderDataObject> tree = new RedBlackBST<Integer, ProviderDataObject>();
	static final SeperateChainingHash<Integer, ProviderDataObject> table = new SeperateChainingHash<Integer, ProviderDataObject>();
	static BSTHashServices combine = new BSTHashServices();

	private String inputFile;

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	private static Double parse(String string) {
		return Double.parseDouble((string).substring(1).replace(",", ""));
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
				// Cell cell1 = sheet.getCell(1,j);
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

				ProviderDataObject a = new ProviderDataObject(cell0.getContents(), cell2.getContents(),
						cell3.getContents(), cell4.getContents(), cell5.getContents(),
						Integer.parseInt(cell6.getContents()), cell7.getContents(),
						Integer.parseInt(cell8.getContents()), cell9x, cell10x, cell11x);

				tree.put(a.getProviderZip(), a);
				table.put(a.getProviderZip(), a);
			}
		} catch (BiffException e) {
			return;
		}

		combine = new BSTHashServices(tree, table);
	}

	/*public static void main(String[] args) throws IOException {
		long startTime1, startTime2;
		long endTime1, endTime2;
		long duration1, duration2;
		boolean keysFaster;

		int lo = 1040;
		int hi = lo+120;

		ReadExcel test = new ReadExcel();
		test.setInputFile("medipath.xls");
		test.read();

		startTime1 = System.nanoTime();
		Stack<Object> marm = (Stack<Object>) combine.getZips(tree.keys(lo, hi)); // best to use when range is larger
																					// than ~120
		endTime1 = System.nanoTime();

		startTime2 = System.nanoTime();
		Stack<Object> alade = (Stack<Object>) combine.getZips(lo, hi); // use in small ranges
		endTime2 = System.nanoTime();

		duration1 = (endTime1 - startTime1);
		duration2 = (endTime2 - startTime2);

		keysFaster = (duration1 < duration2);
		System.out.println(keysFaster);
		
		// tree.printVal(tree.getRoot()); //prints tree in order
		// combine.printSeperate(1040,9999);

		// while (!marm.isEmpty())
		// System.out.println(table.get((Integer) marm.pop()));
	}*/
}
