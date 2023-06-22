package com.r2g.splitwiseexpensetrackingapp.controllers;


import org.apache.coyote.Response;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

@RestController
public class SplitwiseControllers {

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ResponseEntity getHello() {
        return ResponseEntity.ok("Hello world");
    }
    @RequestMapping(value = "/get_current_user", method = RequestMethod.GET)
    public ResponseEntity getDetails(@RequestHeader("user_token") String userToken){
        String url = "https://secure.splitwise.com/api/v3.0/get_current_user";
        HttpHeaders headers=new HttpHeaders();
//        headers.setBearerAuth("El7bFftFdov3YE0LJg74ImY1iyK5YOubYuFiiAV4");
        headers.setBearerAuth(userToken);
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = null;
        String responseBody = null;
        try{
            responseBody = restTemplate.exchange(url,HttpMethod.GET,entity,String.class).getBody();
            result = ResponseEntity.ok(responseBody);
        }catch (HttpClientErrorException ie){
            System.out.println("THE RESPONSE IS "+ie);
            result = new ResponseEntity(ie.getMessage(), ie.getStatusCode());
        }
        finally {
            return result;
        }


    }
}
