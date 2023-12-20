package com.utilities;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import testData.Constants;

/**
 * The ReadWriteTestCases class is responsible for reading from and writing to an Excel file.
 * It is typically used to manage test case data, including storing and retrieving test results.
 */
public class ReadWriteTestCases {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static int testCaseCounter = 1; // Counter for test case ID

    /**
     * Sets the Excel file and sheet to be used for reading/writing test case data.
     * If the file doesn't exist, it creates a new file and writes headers to it.
     *
     * @param Path The path to the Excel file.
     * @param SheetName The name of the Excel sheet.
     * @throws Exception If there is an issue accessing or creating the Excel file.
     */
    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            File file = new File(Path);
            if (file.exists()) {
                FileInputStream ExcelFile = new FileInputStream(file);
                ExcelWBook = new XSSFWorkbook(ExcelFile);
            } else {
                ExcelWBook = new XSSFWorkbook();
                ExcelWSheet = ExcelWBook.createSheet(SheetName);
                writeHeaders(); // Optional: Write headers if creating a new file
            }

            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            if (ExcelWSheet == null) {
                ExcelWSheet = ExcelWBook.createSheet(SheetName);
            }
        } catch (IOException e) {
            throw new Exception("Error in setting up the Excel file: " + e.getMessage());
        }
    }

    /**
     * Writes column headers to the Excel sheet. This is used when creating a new Excel file.
     *
     * @throws Exception If there is an issue writing to the Excel file.
     */
    public static void writeHeaders() throws Exception {
        XSSFRow row = ExcelWSheet.getRow(0);
        if (row == null) {
            row = ExcelWSheet.createRow(0);
        }
        String[] headers = {"Test Case ID", "Test Case Description", "Result"};
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }
        saveExcel();
    }

    /**
     * Finds the next empty row in the Excel sheet.
     *
     * @return The row number of the next empty row.
     */
    public static int getNextEmptyRow() {
        int rowNum = 1;
        while (ExcelWSheet.getRow(rowNum) != null) {
            rowNum++;
        }
        return rowNum;
    }
    
    /**
     * Writes the test case result to the Excel file. If the test case is new, it creates a new row.
     *
     * @param testCaseId The ID of the test case.
     * @param description The description of the test case.
     * @param result The result of the test case.
     * @throws Exception If there is an issue writing to the Excel file.
     */

    public static void writeTestResult(String testCaseId, String description, String result) throws Exception {
        int rowNum = findRowByDescription(description);
        if (rowNum == -1) {  // Test case not found, create a new row
            rowNum = getNextEmptyRow();
            XSSFRow row = ExcelWSheet.createRow(rowNum);
            row.createCell(0).setCellValue(testCaseId);
            row.createCell(1).setCellValue(description);
        }

        XSSFRow row = ExcelWSheet.getRow(rowNum);
        XSSFCell resultCell = row.createCell(2); // Assuming result is always in column 2
        resultCell.setCellValue(result);
        saveExcel();
    }

    /**
     * Generates a unique test case ID using a counter.
     *
     * @return A unique test case ID.
     */
    public static String generateTestCaseId() {
        return "TC-" + (testCaseCounter++);
    }
    
    /**
     * Saves the changes made to the Excel file.
     *
     * @throws Exception If there is an issue saving the Excel file.
     */

    private static void saveExcel() throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData + Constants.File_TestData)) {
            ExcelWBook.write(fileOut);
        } catch (IOException e) {
            throw new Exception("Error while saving the Excel file: " + e.getMessage(), e);
        }
    }
    /**
     * Finds a row in the Excel sheet by test case description.
     *
     * @param description The description of the test case to find.
     * @return The row number where the test case is found, or -1 if not found.
     */
    private static int findRowByDescription(String description) {
        for (int i = 1; i <= ExcelWSheet.getLastRowNum(); i++) { // Start from 1 to skip header row
            XSSFRow row = ExcelWSheet.getRow(i);
            if (row != null) {
                XSSFCell descriptionCell = row.getCell(1); // Assuming description is in column 1
                if (descriptionCell != null && description.equals(descriptionCell.getStringCellValue())) {
                    return i;
                }
            }
        }
        return -1; // Not found
    }
}
