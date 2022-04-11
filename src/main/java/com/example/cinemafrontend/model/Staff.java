package com.example.cinemafrontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Staff {

    private int staffId = -1;
    private String name;
    private String securityNumber;
    private String mail;
    private String address;
    private int salary;
    private String position;

    public Staff(@JsonProperty("staff_id") int staffId,
                 @JsonProperty("name") String name,
                 @JsonProperty("security_number") String securityNumber,
                 @JsonProperty("mail") String mail,
                 @JsonProperty("address") String address,
                 @JsonProperty("salary") int salary,
                 @JsonProperty("position") String position) {
        this.staffId = staffId;
        this.name = name;
        this.securityNumber = securityNumber;
        this.mail = mail;
        this.address = address;
        this.salary = salary;
        this.position = position;
    }

    public int getStaffId() {return staffId;}

    public void setStaffId(int staffId) {this.staffId = staffId;}

    public String getName() {return name;}

    public void setName (String name) {this.name = name;}

    public String getSecurityNumber() {return securityNumber;}

    public void setSecurityNumber(String SecurityNumber) {this.securityNumber = SecurityNumber;}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {return address;}

    public void setAddress(String Address) {this.address = address;}

    public int getSalary() {return salary;}

    public void setSalary(int Salary) {this.salary = salary;}

    public String getPosition() {return position;}

    public void setPosition(String Position) {this.position = position;}

}
