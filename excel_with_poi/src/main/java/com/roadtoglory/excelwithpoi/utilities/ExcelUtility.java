package com.roadtoglory.excelwithpoi.utilities;

import com.roadtoglory.excelwithpoi.models.ExcelSheetModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelUtility {

    private static Map<String, Integer> excelHeaders = new HashMap<String, Integer>();

    public void readAndPrintExcel(){

    }

    public static Map<String, Integer> getHeaders(XSSFSheet sheet){

        if(excelHeaders == null)
            init(sheet);
        return excelHeaders;
    }

    public static void init(XSSFSheet sheet){
       XSSFRow row = sheet.getRow(0);
       Iterator<Cell> cellIterator = row.cellIterator();
       while (cellIterator.hasNext()){
           XSSFCell cell = (XSSFCell) cellIterator.next();
           excelHeaders.put(cell.getStringCellValue(),cell.getColumnIndex());
       }
    }

}
