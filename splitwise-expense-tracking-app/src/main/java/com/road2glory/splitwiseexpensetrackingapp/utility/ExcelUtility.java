package com.road2glory.splitwiseexpensetrackingapp.utility;

import com.road2glory.splitwiseexpensetrackingapp.constants.ExcelHeader;
import com.road2glory.splitwiseexpensetrackingapp.models.ExpenseDetails;
import com.road2glory.splitwiseexpensetrackingapp.models.GmailMessageDetails;
import com.road2glory.splitwiseexpensetrackingapp.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExcelUtility {
    private static Logger LOG = LogManager.getLogger(ExcelUtility.class);


    public static void createAndFetchHeader(XSSFSheet sheet) {
        LOG.debug("Header is getting created... ");

        XSSFRow headerRow = sheet.createRow(0);
        int colIndex = 0;
        while (colIndex < 6) {
            XSSFCell headerRowCell = headerRow.createCell(colIndex);
            if(colIndex == 0)
                headerRowCell.setCellValue(ExcelHeader.SOURCE);
            if(colIndex == 1)
                headerRowCell.setCellValue(ExcelHeader.RECORD_DATE);
            else if(colIndex == 2)
                headerRowCell.setCellValue(ExcelHeader.DESC);
            else if(colIndex == 3)
                headerRowCell.setCellValue(ExcelHeader.CATEGORY);
            else if(colIndex == 4)
                headerRowCell.setCellValue(ExcelHeader.AMOUNT);
            else if(colIndex == 5)
                headerRowCell.setCellValue(ExcelHeader.SELF_PAYMENT);
            colIndex++;
        }
        LOG.debug("Header Creation is done... ");
    }

    public static void convertEMailToExcel(List<GmailMessageDetails> gmailMessageDetailsList, XSSFSheet sheet) {
        int rowNumber = 1; //0 is taken by the header

        for(GmailMessageDetails messageDetails : gmailMessageDetailsList){
            int columnIndex = 0;
            XSSFRow bodyRow = sheet.createRow(rowNumber);

            bodyRow.createCell(columnIndex).setCellValue("Bank Txn");
            columnIndex++;

            XSSFCell dateCell = bodyRow.createCell(columnIndex);
            dateCell.setCellType(CellType.STRING);
            LOG.debug("The txn date thro Gmail "+messageDetails.getDateOfSend());

            dateCell.setCellValue(messageDetails.getDateOfSend().toString());
            columnIndex++;

            bodyRow.createCell(columnIndex).setCellValue(messageDetails.getSnippet());
            columnIndex++;

            // To-do: TBI
            bodyRow.createCell(columnIndex).setCellValue("NA / TBD");
            columnIndex++;


            String str = messageDetails.getSnippet();
            String newStr = str.split("Rs.")[1];
            String amount = newStr.split(" ")[0];
            LOG.info("The amount collected is "+amount);
            bodyRow.createCell(columnIndex).setCellValue(Double.valueOf(amount));
            columnIndex++;

            bodyRow.createCell(columnIndex).setCellValue("Yes");
            rowNumber++;
        }
    }

    public static void convertSplitwiseToExcel(User userToCheck, XSSFWorkbook workbook) {
        LOG.debug("Splitwise Data is getting written here....");
        XSSFSheet splitwiseSheet = workbook.createSheet("splitwise_expenses");

        ExcelUtility.createAndFetchHeader(splitwiseSheet);

        // create row
        int splitwiseRowNumber = 1; //0 is taken by the header

        LOG.info("The user is checked without any issues!");
        if(userToCheck.getAllExpenseDetails().size() > 0){

            LOG.info("The user: "+userToCheck.getName()+" is valid and has expenses!");
            for (ExpenseDetails expenseDetails : userToCheck.getAllExpenseDetails()){
                int columnIndex = 0;
                XSSFRow bodyRow = splitwiseSheet.createRow(splitwiseRowNumber);

                bodyRow.createCell(columnIndex).setCellValue("Splitwise Txn");
                columnIndex++;

                XSSFCell dateCell = bodyRow.createCell(columnIndex);
                dateCell.setCellType(CellType.STRING);
                LOG.debug("The record date thro splitwise txn "+expenseDetails.getRecordDate());
                dateCell.setCellValue(expenseDetails.getRecordDate().toString());
                columnIndex++;

                bodyRow.createCell(columnIndex).setCellValue(expenseDetails.getDescription());
                columnIndex++;

                bodyRow.createCell(columnIndex).setCellValue(expenseDetails.getCategory());
                columnIndex++;

                bodyRow.createCell(columnIndex).setCellValue(expenseDetails.getAmount());
                columnIndex++;

                bodyRow.createCell(columnIndex).setCellValue(expenseDetails.isPaidbyme() ? "Yes" : "No");

                splitwiseRowNumber++;
            }
        }
        LOG.debug("Splitwise Data written here. Operation ends!!!");
    }


}