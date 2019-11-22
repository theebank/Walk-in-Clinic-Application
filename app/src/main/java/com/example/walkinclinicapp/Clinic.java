package com.example.walkinclinicapp;

import java.util.List;

public class Clinic {
    private String name;
    private List<String> hours;


    public Clinic() {

    }
    public Clinic(String name,List<String> hours){
        this.name= name;this.hours = hours;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    }

