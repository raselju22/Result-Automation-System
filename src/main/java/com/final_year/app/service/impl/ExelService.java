package com.final_year.app.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.final_year.app.domain.Student;

public class ExelService {

	public static  List<Student> readXLSXFile(String fileName) {
		System.out.println("-------------------------------------------------");
		InputStream XlsxFileToRead = null;
		XSSFWorkbook workbook = null;
		ArrayList<Student> students = new ArrayList<>();
		try {
			XlsxFileToRead = new FileInputStream(fileName);

			// Getting the workbook instance for xlsx file
			workbook = new XSSFWorkbook(XlsxFileToRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// getting the first sheet from the workbook using sheet name.
		// We can also pass the index of the sheet which starts from '0'.
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		XSSFRow row;
		XSSFCell cell;

		// Iterating all the rows in the sheet
		Iterator rows = sheet.rowIterator();
		int serial=0;
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();

			// Iterating all the cells of the current row
			Iterator cells = row.cellIterator();

			
			int classRoll=0;
			int examRoll=0;
			BigDecimal ct1 = new BigDecimal(0);
			BigDecimal ct2=new BigDecimal(0);
			BigDecimal ct3=new BigDecimal(0);
			BigDecimal ct4=new BigDecimal(0);
			BigDecimal ct5=new BigDecimal(0);
			BigDecimal ct6=new BigDecimal(0);
			BigDecimal attendence=new BigDecimal(0);
			int i = 0;

			boolean hasStudent=false;
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();

				
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					System.out.print(cell.getStringCellValue() + " ::");
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					i++;
					hasStudent=true;
					switch (i) {
					case 1:
                           serial++;
						break;
					case 2:

						classRoll=(int) cell.getNumericCellValue();
						break;
					case 3:

						examRoll=(int) cell.getNumericCellValue();
						break;
					case 4:
                      ct1=new BigDecimal(cell.getNumericCellValue());
						break;
					case 5:
						ct2=new BigDecimal(cell.getNumericCellValue());
						break;
					case 6:
						ct3=new BigDecimal(cell.getNumericCellValue());
						break;
					case 7:
						ct4=new BigDecimal(cell.getNumericCellValue());
						break;
					case 8:
						ct5=new BigDecimal(cell.getNumericCellValue());
						break;

					case 9:
						ct6=new BigDecimal(cell.getNumericCellValue());
						break;
					case 10:
						attendence=new BigDecimal(cell.getNumericCellValue());
						break;
					default:
						break;
					}

					i = i % 10;

					// double cell.getNumericCellValue();
				}
			}
			
			
			if(hasStudent){
				if(classRoll==0){
					
					System.out.println("GGGGGGGGGGGGGGGGGGGG");
				}
				Student student=new Student(classRoll, examRoll, attendence, ct1, ct2, ct3, ct4, ct5, ct6);
				System.out.println(serial+":"+classRoll+","+examRoll+","+ct1+","+ct2+","+ct2+","+ct3+","+ct4+","+ct5+","+ct6+","+attendence);
				students.add(student);
			}
			
			
			try {
				XlsxFileToRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("SEEEE:"+students.size());
		System.out.println(students);
		return students;
	}
}
