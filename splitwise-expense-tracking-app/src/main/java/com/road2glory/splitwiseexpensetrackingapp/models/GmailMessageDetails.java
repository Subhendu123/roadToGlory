package com.road2glory.splitwiseexpensetrackingapp.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
//@Scope("prototype")
public class GmailMessageDetails {

    private String id;
    private String snippet;
    private String subject;
    private LocalDate dateOfSend;
    private String fromAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDateOfSend() {
        return dateOfSend;
    }

    public void setDateOfSend(LocalDate dateOfSend) {
        this.dateOfSend = dateOfSend;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
}
