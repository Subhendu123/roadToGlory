package com.roadtoglory.excelwithpoi;

import com.roadtoglory.excelwithpoi.models.ExcelSheetModel;
import com.roadtoglory.excelwithpoi.models.Person;
import com.roadtoglory.excelwithpoi.utilities.ExcelUtility;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("\n \n \n");

        String location = "C:\\OneDrive - Cloud Storage\\OneDrive\\Documents\\Study Documents\\splitwise daily exp.xlsx";
        FileInputStream inputStream = new FileInputStream(location);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        ExcelUtility.init(sheet);
        Map<String, Integer> excelHeaders = ExcelUtility.getHeaders(sheet);
        Iterator<Row> rowIterator = sheet.rowIterator();
        List<ExcelSheetModel> excelSheetModelList = new ArrayList<>();
        double totalExpenses = 0;
        double borrowedExpenses = 0;
        double myExpenses = 0;
        rowIterator.next();
        System.out.println("************************************");
        while(rowIterator.hasNext()){
            XSSFRow row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            double totalCost = 0;
            boolean isColValid = true;
            ExcelSheetModel output = null;

            while (cellIterator.hasNext()){
                // Retrieve Column
                XSSFCell retrievedColumn = (XSSFCell) cellIterator.next();
                Date date = null;

                if(retrievedColumn.getColumnIndex() == excelHeaders.get("Description")
                        && (retrievedColumn.getStringCellValue().equalsIgnoreCase(Placeholders.SETTLE_ALL_BAL)
                        || retrievedColumn.getStringCellValue().equalsIgnoreCase(Placeholders.TOTAL_BAL))){
                    isColValid = false;
                }
                if(retrievedColumn.getColumnIndex() == excelHeaders.get("Category")
                        && retrievedColumn.getStringCellValue().equalsIgnoreCase(Placeholders.PAYMENT_CAT)){
                    isColValid = false;
                }

                if(retrievedColumn.getColumnIndex() == excelHeaders.get("Date") && isColValid){
                    DataFormatter formatter = new DataFormatter(Locale.US);
                    String dateInStrFormat = formatter.formatCellValue(retrievedColumn);
                    date = DateUtil.parseYYYYMMDDDate(dateInStrFormat);
//                    System.out.println("AFTER IS "+DateUtil.parseYYYYMMDDDate("2023-06-01"));
//                    System.out.println("BEFORE IS "+DateUtil.parseYYYYMMDDDate("2023-06-02"));
                    isColValid = date.after(DateUtil.parseYYYYMMDDDate("2023-05-31")) &&
                            date.before(DateUtil.parseYYYYMMDDDate("2023-06-02"));
                }

                if(isColValid) {
                    if(output == null)
                        output = new ExcelSheetModel();

                    output.setTransactionDate(date);
                    if(retrievedColumn.getCellType().equals(CellType.STRING)){
//                        System.out.print(retrievedColumn.getStringCellValue());
                        if(retrievedColumn.getColumnIndex() == excelHeaders.get("Description"))
                            output.setDescription(retrievedColumn.getStringCellValue());
                        if(retrievedColumn.getColumnIndex() == excelHeaders.get("Category"))
                            output.setCategory(retrievedColumn.getStringCellValue());
                        output.setPerson(Person.Subhendu);
                    }

                    if (retrievedColumn.getCellType().equals(CellType.NUMERIC)) {
                        double amountTBC = retrievedColumn.getNumericCellValue();
                        if(retrievedColumn.getColumnIndex() == excelHeaders.get("Cost")){
                            // total cost
                            totalCost = amountTBC;
                        }
                        if(retrievedColumn.getColumnIndex()
                                == excelHeaders.get("Subhendu Das")){
                            if(amountTBC < 0) {
                                // if the cell has - sign -> i have borrowed (did not pay) the amount
                                totalExpenses = totalExpenses + amountTBC * -1;
                                borrowedExpenses = borrowedExpenses + amountTBC * -1;
                                output.setAmount(amountTBC * -1);
                            }
                            else {
                                // Cost is discarded
                                totalExpenses = totalExpenses + (totalCost - amountTBC);
                                myExpenses = myExpenses + (totalCost - amountTBC);
                                output.setAmount(totalCost - amountTBC);
                            }
                        }
                        System.out.println("------------------------------------");
                        System.out.println("Total "+totalExpenses);
                        System.out.println("My exp "+myExpenses);
                        System.out.println("borrowed "+borrowedExpenses);
                        System.out.println("------------------------------------");
                        System.out.println("\n \n");
                    }



                }

//                System.out.print("  ");
            }
            if(output != null)
             excelSheetModelList.add(output);
//            System.out.println();

        }

        // print out all the expenses
        System.out.println("**************************************************");
        System.out.println("Total Expenses: "+totalExpenses);
        System.out.println("Borrowed Expenses: "+borrowedExpenses);
        System.out.println("My Expenses: "+myExpenses);
        System.out.println("\n \n \n");

        System.out.println("total excelSheetModelList "+excelSheetModelList.size());

        for(ExcelSheetModel ex : excelSheetModelList){
            System.out.print(ex.getTransactionDate()+ " "+ex.getDescription()+ " "+ex.getCategory()+" "+ex.getPerson());
            System.out.println();
        }




    }
}