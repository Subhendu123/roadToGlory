package com.road2glory.splitwiseexpensetrackingapp.controllers;

import com.road2glory.splitwiseexpensetrackingapp.constants.GmailApiConstants;
import com.road2glory.splitwiseexpensetrackingapp.models.GmailMessageDetails;
import com.road2glory.splitwiseexpensetrackingapp.services.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class GmailAPIManagerController {

    @Autowired
    private GmailService gmailService;

    public GmailMessageDetails getMailDetails(String id, String authToken, String emailId) {

        HttpHeaders headers=new HttpHeaders();
        headers.setBearerAuth(authToken.split(" ")[1]);

        HttpEntity<String> entity=new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String responseBody = null;

        try{
            // validate the email id before passing (using spring)
            String finalURL = GmailApiConstants.GMAIL_URI
                    .concat(emailId)
                    .concat("/messages/")
                    .concat(id);
            responseBody = restTemplate.exchange(finalURL, HttpMethod.GET,entity,String.class).getBody();

        }catch (HttpClientErrorException ie){
            System.out.println("Unexpected error occurred while reading the message "+ie);
            throw new HttpClientErrorException(ie.getStatusCode(),"Unexpected error occurred while reading the message!");
        }
        return gmailService.formatMailBodyFromResponse(responseBody);

    }
}
