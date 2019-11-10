package com.example.walkinclinicapp;

public class Service {
    private String Type;
    private String Description;
    private String role;

    public Service() {

    }
    public Service(String type, String description,String role){
        this.Type= type;
        this.Description = description;
        this.role = role;
    }

    public String getType() {
        return Type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
