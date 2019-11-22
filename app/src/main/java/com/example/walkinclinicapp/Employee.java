package com.example.walkinclinicapp;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    private String address;
    private String phonenum;
    private String clinicname;
    private String insurancetype;
    private Boolean credit;
    private Boolean debit;
    private Boolean cash;
    private Boolean Check;
    private List<String> Services;
    private List<String> hours;

    public List<String> getServices() {
        return Services;
    }

    public void setServices(List<String> services) {
        Services = services;
    }

    public Employee(String address, String phonenum, String clinicname, String insurancetype, Boolean credit, Boolean debit, Boolean cash, Boolean Check , String Username, String password, String role, List<String> services,List<String> hours){
        this.address=address;
        this.phonenum = phonenum;
        this.clinicname = clinicname;
        this.insurancetype = insurancetype;
        this.credit = credit;
        this.debit=debit;
        this.cash = cash;
        this.Check=Check;
        this.setUsername(Username);
        this.setPassword(password);
        this.setRole(role);
        this.Services = services;
        this.hours = hours;
    }
    public Employee(){

    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public Boolean getCredit() {
        return credit;
    }

    public void setCredit(Boolean credit) {
        this.credit = credit;
    }

    public Boolean getDebit() {
        return debit;
    }

    public void setDebit(Boolean debit) {
        this.debit = debit;
    }

    public Boolean getCash() {
        return cash;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }

    public Boolean getCheck() {
        return Check;
    }

    public void setCheck(Boolean check) {
        Check = check;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getClinicname() {
        return clinicname;
    }

    public void setClinicname(String clinicname) {
        this.clinicname = clinicname;
    }

    public String getInsurancetype() {
        return insurancetype;
    }

    public void setInsurancetype(String insurancetype) {
        this.insurancetype = insurancetype;
    }


}
