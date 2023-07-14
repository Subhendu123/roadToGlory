package com.road2glory.splitwiseexpensetrackingapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.road2glory.splitwiseexpensetrackingapp.constants.DateFormatPattern;
import com.road2glory.splitwiseexpensetrackingapp.exceptions.DateTimeConvertionException;
import com.road2glory.splitwiseexpensetrackingapp.models.*;
import com.road2glory.splitwiseexpensetrackingapp.utility.ExcelUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

//import static com.road2glory.splitwiseexpensetrackingapp.utility.ExcelUtility.createAndFetchHeader;

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
                    DateTimeFormatter formatter_WO_IST = DateTimeFormatter.ofPattern(DateFormatPattern.DATE_FORMAT_WITHOUT_TZ, Locale.ENGLISH);
                    DateTimeFormatter formatter_IST = DateTimeFormatter.ofPattern(DateFormatPattern.DATE_FORMAT_WITH_TZ, Locale.ENGLISH);

                    String dateString = header.getValue();

                    LOG.info("The Date String is "+dateString);
                    try {
                        /*
                        LocalDate now = LocalDate.now().atStartOfDay().toLocalDate();
        System.out.println("The local date "+now);
        java.sql.Date date = java.sql.Date.valueOf(now);
        Date date1 = new Date(date.getTime());
        System.out.println("The java.util date "+date1); */

                        DateTimeFormatter formatter = dateString.indexOf("(IST)") > 0 ? formatter_IST : formatter_WO_IST;

                        LocalDate localDate = LocalDate.parse(dateString, formatter);
                        LOG.info("The local date is "+localDate);
                        java.sql.Date javaSQLDate = java.sql.Date.valueOf(localDate);
                        LOG.info("The javaSQLDate is "+javaSQLDate);
                        Date javaDate = new Date(javaSQLDate.getTime());
                        LOG.info("The javaDate is "+javaDate);
                        gmailMessageDetails.setDateOfSend(javaDate);


                    } catch (Exception e) {
                        LOG.error("The Date Time parsing error in Gmail Service is due to "+e);
                        e.printStackTrace();
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

    public void exportToExcel(List<GmailMessageDetails> gmailMessageDetailsList,
                              List<User> userDetailsList) throws IOException {
        LOG.debug("Entering in the exportToExcel Method.... ");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet gmailSheet = workbook.createSheet("expense_tracker");

        // Header Code

        ExcelUtility.createAndFetchHeader(gmailSheet);

        ExcelUtility.convertEMailToExcel(gmailMessageDetailsList,gmailSheet);

        User userToCheck = userDetailsList.stream().filter(user -> user.getId() == 6646855L).findFirst().get();
        // format splitwise data here
        ExcelUtility.convertSplitwiseToExcel(userToCheck, workbook);

        OutputStream fileOut = new FileOutputStream("BankStatement_"+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+".xlsx");
        workbook.write(fileOut);

    }




}
