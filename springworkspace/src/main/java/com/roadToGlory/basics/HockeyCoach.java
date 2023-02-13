package com.roadToGlory.basics;

public class HockeyCoach implements Coach{

    private FortuneService fortuneService;

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public FortuneService getFortuneService() {
        return fortuneService;
    }

    public void setFortuneService(FortuneService fortuneService) {
        fortuneService.setFortune("Today is hockey coach's lucky day!");
        this.fortuneService = fortuneService;
    }

    @Override
    public String getMyInstruction() {
        return "Practise penalty corners for an hour!";
    }
}
