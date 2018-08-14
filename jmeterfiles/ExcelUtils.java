package common.maven.appiumproject.myrenaultproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	//This method is to set the file path and to open the Excel file, pass Excel Path and Sheet name as Arguments to this method
	public static void setExcelFile(String Path,String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} 
		catch (Exception e)
		{
			throw (e);
		}
	}
	public static Object[][] getTableArray(String FilePath, String SheetName)  throws Exception
	{   
		String[][] tabArray = null;
		try{
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			//System.out.println("totalRows" + totalRows);
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();
			//System.out.println("totalCols" + totalCols);
			tabArray=new String[totalRows][totalCols]; 
			ci=0;
			for (int i=1;i<=totalRows;i++, ci++) 
			{           	   
				cj=0;
				for (int j=0;j< totalCols;j++, cj++)
				{
					tabArray[ci][cj]=getCellData(i,j);
					//System.out.println(tabArray[ci][cj]);  
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(tabArray);
	}
	// TODO: This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum) throws Exception{

		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			Cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
			String CellData = Cell.getStringCellValue();
			return CellData;
		}
		catch (Exception e){
			return"";
		}
	} 
	
/*	public  static String getProValue(String proname)
	{
	
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("D://Public//z015055//AWS//AppiumAWSProject//appiumsampleproject//locators.properties");
		} catch (FileNotFoundException e) {
			
			System.out.println("file is not found");
		}
	
		   Properties p=new Properties();
		   
		   try {
			p.load(fis);
		} catch (IOException e) {
			System.out.println("file format should be .properties");
		}
		
		
		return  p.getProperty(proname);
		
		
	}*/

}


