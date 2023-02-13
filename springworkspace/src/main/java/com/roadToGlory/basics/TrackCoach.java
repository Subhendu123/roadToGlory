package com.roadToGlory.basics;

import org.springframework.beans.factory.DisposableBean;

public class TrackCoach implements Coach, DisposableBean {
    @Override
    public String getMyInstruction() {
        return "Run Hard for 5K!";
    }

    private void doInit(){
        System.out.println("doInit method in TrackCoach.class");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Inside the destroy method");
    }
}
