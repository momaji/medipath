package medipaths;

// https://www.youtube.com/watch?v=65igZdK9Vd4 (code is in description)
// 

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	
	//static ProviderDataObject[] data = new ProviderDataObject[65545];
	static RedBlackBST tree = new RedBlackBST();
	
	private String inputFile;
 
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
 
	public void read() throws IOException{
		File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			//getCell is (column,row)
			
			
			//loop over first 10 column and lines
			for(int j = 1; j<65536; j++){
				
				Cell cell0 = sheet.getCell(0,j);
				//Cell cell1 = sheet.getCell(1,j);
				Cell cell2 = sheet.getCell(2,j);
				Cell cell3 = sheet.getCell(3,j);
				Cell cell4 = sheet.getCell(4,j);
				Cell cell5 = sheet.getCell(5,j);
				Cell cell6 = sheet.getCell(6,j);
				Cell cell7 = sheet.getCell(7,j);
				Cell cell8 = sheet.getCell(8,j);
				Cell cell9 = sheet.getCell(9,j);
				Cell cell10 = sheet.getCell(10,j);
				Cell cell11 = sheet.getCell(11,j);
				
				//getting rid of commas and dollar sign for 9,10,11 and converting to type int
				double cell9x = Double.parseDouble((cell9.getContents()).substring(1).replace(",", ""));
				double cell10x = Double.parseDouble((cell10.getContents()).substring(1).replace(",", ""));;
				double cell11x = Double.parseDouble((cell11.getContents()).substring(1).replace(",", ""));;
				
				ProviderDataObject a = new ProviderDataObject(cell0.getContents(),cell2.getContents(),cell3.getContents(),cell4.getContents(),cell5.getContents(),Integer.parseInt(cell6.getContents()),cell7.getContents(),Integer.parseInt(cell8.getContents()),cell9x,cell10x,cell11x); 
				
				tree.put(a.getProviderZip(),a);
				
			}
		}
		catch(BiffException e) {
			return;
		}
	}
	
	public static void main(String[] args) throws IOException {
		ReadExcel test = new ReadExcel();
		test.setInputFile("medipath/medipath.xls");
		test.read();
		//System.out.println(tree.get(key)data[5].getACC());
		
		System.out.println(tree.get(72160));
		tree.print(tree.getRoot());
	}
	
}
