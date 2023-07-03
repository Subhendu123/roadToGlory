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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

                    SimpleDateFormat format = new SimpleDateFormat(DateFormatPattern.DATE_FORMAT_WITH_ZZZZ);
                    String dateString = header.getValue();
                    try {
                        gmailMessageDetails.setDateOfSend(format.parse(dateString));
                    } catch (ParseException e) {
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
}
