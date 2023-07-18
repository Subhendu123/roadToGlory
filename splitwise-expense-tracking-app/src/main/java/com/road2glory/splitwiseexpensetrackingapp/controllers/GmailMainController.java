package com.road2glory.splitwiseexpensetrackingapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.road2glory.splitwiseexpensetrackingapp.constants.GmailApiConstants;
import com.road2glory.splitwiseexpensetrackingapp.models.GmailMessage;
import com.road2glory.splitwiseexpensetrackingapp.models.GmailMessageDetails;
import com.road2glory.splitwiseexpensetrackingapp.models.ResponseMessage;
import com.road2glory.splitwiseexpensetrackingapp.models.User;
import com.road2glory.splitwiseexpensetrackingapp.services.GmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GmailMainController {

    private static Logger LOG = LogManager.getLogger(GmailMainController.class);
    @Autowired
    private GmailService gmailService;

    @Autowired
    private GmailAPIManagerController gmailAPIManagerController;


    @Autowired
    private  SplitwiseMainController splitwiseMainController;



    @RequestMapping(value = "/hello-gmail", method = RequestMethod.GET)
    public ResponseEntity getHello() {
        return ResponseEntity.ok("Hello world");
    }

    @RequestMapping(value = "/export-data", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> exportDataFromSplitwiseAndMail(
            @RequestParam("user_id") String emailId,
            @RequestParam("filter_criteria") String criteria,
            @RequestParam(value = "maxResults", required = false, defaultValue = "100") int maxResults,
            @RequestParam("group_id") int groupId,
            @RequestParam("dated_after") String datedAfter,
            @RequestParam("dated_before") String datedBefore,
            @RequestHeader(value = "Authorization" , required = true) String authToken
    ) {
        boolean isError = false;
        List<GmailMessageDetails> gmailMessageDetailsList = null;
        try {
            ResponseEntity<List<GmailMessageDetails>> responseEntity = getEmailsForUser(emailId, criteria, maxResults, authToken);
            gmailMessageDetailsList = responseEntity.getBody();
        }
        catch (RuntimeException re){
            isError = true;
            LOG.error("The Error occurred during processing of the EMail Service "+re.getMessage());
            re.printStackTrace();
        }


        // call splitwise ctrl for collecting splitwise data
        ResponseEntity<List<User>> responseEntityUser = null;
        try {
            responseEntityUser = splitwiseMainController.getExpensesForCurrentUser(groupId,datedAfter,datedBefore,
                    100);
        } catch (JSONException e) {
            if(isError){
                return ResponseEntity.badRequest().body(new ResponseMessage("Error while fetching data from Splitwise or Gmail API. Contact System Administrator.", HttpStatus.BAD_REQUEST.value()));
            }
            LOG.error("The exception is occurred due to "+e);
        }
        List<User> userDetailsList = responseEntityUser.getBody();

        try {
            gmailService.exportToExcel(gmailMessageDetailsList, userDetailsList);
        } catch (IOException e) {
            LOG.error("The export to excel is failed with error "+e);
            e.printStackTrace();
           return ResponseEntity.internalServerError().body(new ResponseMessage("Error occured during process of Export to Excel.", HttpStatus.FORBIDDEN.value()));
            //row new RuntimeException(e);
        }
        return ResponseEntity.accepted().body(new ResponseMessage("Successfully Written", HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getMails", method = RequestMethod.GET)
    public ResponseEntity<List<GmailMessageDetails>> getEmailsForUser(
            @RequestParam("user_id") String emailId,
            @RequestParam("filter_criteria") String criteria,
            @RequestParam(value = "maxResults", required = false, defaultValue = "100") int maxResults,
           @RequestHeader(value = "Authorization") String authToken
            ) {


        HttpHeaders headers=new HttpHeaders();
        headers.setBearerAuth(authToken.split(" ")[1]);

        HttpEntity<String> entity=new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = null;
        String responseBody = null;
        try{
            // validate the email id before passing (using spring)
            String finalURL = GmailApiConstants.GMAIL_URI
                                            .concat(emailId)
                                            .concat("/messages")
                    .concat("?q="+criteria)
                    .concat("&maxResults="+maxResults);
            responseBody = restTemplate.exchange(finalURL, HttpMethod.GET,entity,String.class).getBody();

        }catch (HttpClientErrorException ie){
            System.out.println("THE RESPONSE IS "+ie);
            result = new ResponseEntity(ie.getMessage(), ie.getStatusCode());
            return result;
        }

        List<GmailMessage> gmailMessageList  = gmailService.formatMailsFromSource(responseBody);
        List<GmailMessageDetails> gmailMessageDetailsList = new ArrayList<>();
        for(GmailMessage gmailMessage : gmailMessageList){
            GmailMessageDetails gmailMessageDetails =
                    gmailAPIManagerController.getMailDetails(gmailMessage.getId(),authToken, emailId);
            gmailMessageDetailsList.add(gmailMessageDetails);
        }
        return ResponseEntity.ok(gmailMessageDetailsList);
    }


}





