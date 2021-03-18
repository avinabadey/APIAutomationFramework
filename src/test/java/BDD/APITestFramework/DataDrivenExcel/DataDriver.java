package BDD.APITestFramework.DataDrivenExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriver {

	public List<String> getData(String testCaseName) {
		
		List<String> al = new ArrayList<String>();
		FileInputStream fis = null;
		XSSFWorkbook workbook =null;
		try {
			fis = new FileInputStream("E:\\Knowledge\\Testing\\Selenium\\Data\\test cases.xlsx");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int sheetCount = workbook.getNumberOfSheets();
		for(int i = 0; i < sheetCount; i++){
			if(workbook.getSheetName(i).equalsIgnoreCase("test cases")){
				XSSFSheet sheet  = workbook.getSheetAt(i);
				
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();
				
				int column = 0, k = 0;
				while(cell.hasNext()){
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("name")){
						column = k;
					}
					k++;
				}
//				System.out.println(column);
				
				while(rows.hasNext()){
					Row row = rows.next();
					if(row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)){
						Iterator<Cell> cv = row.cellIterator();
						while(cv.hasNext()){
							Cell c = cv.next();
							if(c.getCellType()==CellType.STRING){
								al.add(c.getStringCellValue());
							}
							else{
								al.add(String.valueOf(c.getNumericCellValue()));
							}
//							System.out.println(cv.next().getStringCellValue());
						}
					}
				}
//				System.out.println(al);
				
			}
				
		}
		return al;

	}

}
