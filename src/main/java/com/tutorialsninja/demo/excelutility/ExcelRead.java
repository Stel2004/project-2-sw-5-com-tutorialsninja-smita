package com.tutorialsninja.demo.excelutility;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelRead {
    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");

        XSSFWorkbook excelWBook;
        XSSFSheet excelWSheet;
        XSSFCell Cell;

        // Location of the Excel file
        String path = projectPath + "\\src\\test\\java\\resources\\testdata\\ExcelRead.xlsx";
        String sheetName = "Sheet1";

        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);

            Cell = excelWSheet.getRow(1).getCell(2);
            String cellData = Cell.getStringCellValue();
            System.out.println("Cell Data: " + cellData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
