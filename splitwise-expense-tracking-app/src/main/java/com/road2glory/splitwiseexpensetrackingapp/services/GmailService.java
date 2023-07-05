package com.road2glory.splitwiseexpensetrackingapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.road2glory.splitwiseexpensetrackingapp.constants.DateFormatPattern;
import com.road2glory.splitwiseexpensetrackingapp.exceptions.DateTimeConvertionException;
import com.road2glory.splitwiseexpensetrackingapp.models.GmailMessage;
import com.road2glory.splitwiseexpensetrackingapp.models.GmailMessageDetails;
import com.road2glory.splitwiseexpensetrackingapp.models.HeaderArrayModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class GmailService {

    private static Logger LOG = LogManager.getLogger(GmailService.class);



    public List<GmailMessage> formatMailsFromSource(String responseBody) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<GmailMessage> gmailMessageList = null;

        try {
            JsonNode responseNode = objectMapper.readTree(responseBody);
            if(responseNode.has("messages")){
                gmailMessageList  = objectMapper.readValue(responseNode.get("messages").traverse(),
                        new TypeReference<ArrayList<GmailMessage>>(){});

            }
        } catch (JsonProcessingException e) {
            LOG.error("Error occurred during processing of JSON: "+e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOG.error("Unexpected Error occurred during processing : "+e);
            throw new RuntimeException(e);
        }
        return gmailMessageList;
    }

    public GmailMessageDetails formatMailBodyFromResponse(String responseBody) {

        ObjectMapper objectMapper = new ObjectMapper();
        GmailMessageDetails gmailMessageDetails = new GmailMessageDetails();

        try {
            JsonNode responseNode = objectMapper.readTree(responseBody);

            String snippet = responseNode.has("snippet") ?
                                    responseNode.get("snippet").asText(): null;
            gmailMessageDetails.setSnippet(snippet);

            String id = responseNode.has("id") ?
                                    responseNode.get("id").asText(): null;
            gmailMessageDetails.setId(id);

            JsonNode headersNode = responseNode.get("payload").get("headers");


            List<HeaderArrayModel> headerArr = objectMapper.readValue(headersNode.traverse(),
                        new TypeReference<ArrayList<HeaderArrayModel>>(){});

            for(HeaderArrayModel header : headerArr){
                if(header.getName().equalsIgnoreCase("From")){
                    gmailMessageDetails.setFromAddress(header.getValue());
                }
                if(header.getName().equalsIgnoreCase("Subject")){
                    gmailMessageDetails.setSubject(header.getValue());
                }
                if(header.getName().equalsIgnoreCase("Date")){

//                    SimpleDateFormat format = new SimpleDateFormat(DateFormatPattern.DATE_FORMAT_WITH_TZ);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy HH:mm:ss Z (z)", Locale.ENGLISH);

                    String dateString = header.getValue();
                    try {
                        /*
                        LocalDate now = LocalDate.now().atStartOfDay().toLocalDate();
        System.out.println("The local date "+now);
        java.sql.Date date = java.sql.Date.valueOf(now);
        Date date1 = new Date(date.getTime());
        System.out.println("The java.util date "+date1); */
//                        LocalDate localDate = LocalDate.parse(dateString, formatter);
//                        Date date = new Date(localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth());
//                        gmailMessageDetails.setDateOfSend(localDate);
//                        gmailMessageDetails.setDateOfSend(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
//                        gmailMessageDetails.setDateOfSend(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    } catch (Exception e) {
                        LOG.error("The Date Time parsing error in Gmail Service is due to "+e.getMessage());
                        throw new DateTimeConvertionException("The Date Time parsing error is due to "+e.getMessage());
                        // write response entity logic here
                    }
                }
            }


        } catch (JsonProcessingException e) {
            LOG.error("Error occurred during processing of JSON: "+e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOG.error("Unexpected Error occurred during processing : "+e);
            throw new RuntimeException(e);
        }
        return gmailMessageDetails;
    }

    public void exportToExcel(List<GmailMessageDetails> gmailMessageDetailsList) {
        LOG.debug("Entering in the exportToExcel Method.... ");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        // Header Code

        LOG.debug("Header is getting created... ");

        XSSFRow headerRow = sheet.createRow(0);
        int colIndex = 0;
        while (colIndex < 6) {
            XSSFCell headerRowCell = headerRow.createCell(colIndex);
            if(colIndex == 0)
                headerRowCell.setCellValue("Source of Transaction");
            if(colIndex == 1)
                headerRowCell.setCellValue("Date of Transaction");
            else if(colIndex == 2)
                headerRowCell.setCellValue("Description");
            else if(colIndex == 3)
                headerRowCell.setCellValue("Category");
            else if(colIndex == 4)
                headerRowCell.setCellValue("Amount");
            else if(colIndex == 5)
                headerRowCell.setCellValue("Paid By Me");
            colIndex++;
        }
        LOG.debug("Header Creation is done... ");

        int rowNumber = 1; //0 is taken by the header

        for(GmailMessageDetails messageDetails : gmailMessageDetailsList){
            XSSFRow bodyRow = sheet.createRow(rowNumber);
            XSSFCell bodyRowCell = bodyRow.createCell(0);
            bodyRowCell.setCellValue(messageDetails.getDateOfSend());

        }

    }
}
