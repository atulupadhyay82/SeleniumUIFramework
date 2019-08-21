package com.uiFramework.companyName.projectName.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.resource.ResourceHelper;

/**
 * to read the data from the excel file then supply to our test scripts there is
 * header link which have some generic keyword which will tell the script to
 * skip this row -like StartLoginTest maintain such keyword in every testdata.
 * 
 * @author atupadhy
 *
 */
public class ExceLHelper {

	private Logger log = LoggerHelper.getLogger(ExceLHelper.class);

	/**
	 * to get the data from the excel sheet
	 * 
	 * @param excelLocation -
	 * @param sheetName
	 * @return
	 */
	public Object[][] getExcelData(String excelLocation, String sheetName) {
		try {
			Object dataSets[][] = null; // data in excel can be of any type(String, Numeric, boolean)

			/*
			 * If we have to read the data from excel sheet, we need an object of
			 * FileInputStream class so that we can read the data as a stream of data in one
			 * go.If the named file does not exist, is a directory rather than a regular
			 * file, or for some other reason cannot be opened for reading then a
			 * FileNotFoundException is thrown FileNotFoundException - if the file does not
			 * exist, is a directory rather than a regular file, or for some other reason
			 * cannot be opened for reading. SecurityException - if a security manager
			 * exists and its checkRead method denies read access to the file.
			 */
			FileInputStream file = new FileInputStream(new File(excelLocation));

			// create workbook instance.Constructs a XSSFWorkbook object, by buffering the
			// whole stream into memory and then opening an OPCPackage object for it.
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// get the sheet name from the workbook. XSSFSheet with the name provided or
			// null if it does not exist
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// count no of active rows (having content) in the sheet. Gets the last row on
			// the sheet.
			int totalRows = sheet.getLastRowNum();

			// count the no of active columns in rows.Will Return the last logical cell in
			// the row PLUS ONE, or -1 if the row does not contain any cells.
			int totalCols = sheet.getRow(0).getLastCellNum();

			System.out.println("row :" + totalRows + " cols: " + totalCols);

			// will hold the excel cell data
			dataSets = new Object[totalRows][totalCols-1];

			// iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				// for every row, we need to iterate over the columns. Row- High level
				// representation of a row of a spreadsheet.
				Row row = rowIterator.next();

				// Cell iterator of the physically defined cells
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;

				while (cellIterator.hasNext()) {

					// High level representation of a cell in a row of a spreadsheet.
					Cell cell = cellIterator.next();

					// If it encounters a row which has string data starting with "Start" in its 1st
					// cell, so skip that row completely
					if (cell.getStringCellValue().contains("Start")) {
						i = 0;
						log.info("skipped");
						break;
					}

					// Cells can be numeric, formula-based or string-based (text).Need to write
					// switch block for this.
					System.out.println(i + "" + j);
					switch (cell.getCellType()) {

					/*
					 * Get the value of the cell as a string. For numeric cells we throw an
					 * exception. For blank cells we return an empty string. For formulaCells that
					 * are not string Formulas, we throw an exception.
					 */
					case STRING:
						dataSets[i - 1][j++] = cell.getStringCellValue();

						break;

					/*
					 * Get the value of the cell as a number. For strings we throw an exception. For
					 * blank cells we return a 0. For formulas or error cells we return the
					 * precalculated value
					 */
					case NUMERIC:
						dataSets[i - 1][j++] = cell.getNumericCellValue();

						break;

					/*
					 * Get the value of the cell as a boolean. For strings, numbers, and errors, we
					 * throw an exception. For blank cells we return a false.
					 */

					case BOOLEAN:
						dataSets[i - 1][j++] = cell.getBooleanCellValue();

						break;

					/*
					 * Return a formula for the cell, for example, SUM(C4:E4)
					 */
					case FORMULA:
						dataSets[i - 1][j++] = cell.getCellFormula();

						break;

					default:
						log.info("no matching data type found");
						break;

					}

				}

			}
			return dataSets;
		} catch (Exception e) {
			// if any exception come while reading the data from the excel will be catch
			// here.
			log.info("exception occured: " + e.getCause());
			return null;
		}

	}

	/**
	 * Will update the status column for each row based on testCaseName
	 * 
	 * @param excelLocation
	 * @param sheetName
	 * @param testCaseName
	 * @param status
	 */
	public void updateResult(String excelLocation, String sheetName, String testCaseName, String status) {
		try {
			FileInputStream file = new FileInputStream(new File(excelLocation));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int totalRows = sheet.getLastRowNum() + 1;

			// start from the 1st row in the sheet as there is no need to change in the
			// header(0th row)
			for (int i = 1; i < totalRows; i++) {
				// Returns the logical row ( 0-based).
				XSSFRow row = sheet.getRow(i);

				// Returns the cell at the given (0 based) index
				String testCaseNames = row.getCell(0).getStringCellValue();

				if (testCaseNames.contains(testCaseName)) {
					row.createCell(1).setCellValue(status);

					// Closes this file input stream and releases any system resources associated
					// with the stream.
					file.close();

					log.info("results updated..");
					/*
					 * Creates a file output stream to write to the file represented by the
					 * specified File object. A new FileDescriptor object is created to represent
					 * this file connection.
					 */
					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					break;
				}
			}

		} catch (Exception e) {
			// if any exception come while reading the data from the excel will be catch
			// here.

			log.info("exception occured: " + e.getCause());
		}

	}

//	// to test this code
//	public static void main(String a[]) {
//		ExceLHelper excelHelper = new ExceLHelper();
//		String excelLocation = ResourceHelper.getResourceHelper("src/main/resource/configFile/testData.xlsx");
//		Object[][] data = excelHelper.getExcelData(excelLocation, "loginData");
//
////		excelHelper.updateResult(excelLocation, "UpdateResult","Login","PASS");
////		excelHelper.updateResult(excelLocation, "UpdateResult","Registeration","Fail");
////		excelHelper.updateResult(excelLocation, "UpdateResult","Add to cart","PASS");
//	}

}
