package com.example.cinemafrontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StaffSchedule {

    private int staffId;
    private String date;
    private String time;
    private String task;

    public StaffSchedule(@JsonProperty("staff_id") int staffId,
                         @JsonProperty("date") String date,
                         @JsonProperty("time") String time,
                         @JsonProperty("task") String task) {
        this.staffId = staffId;
        this.date = date;
        this.time = time;
        this.task = task;
    }

    public int getStaffId() {return staffId;}

    public void setStaffId(int StaffId) {this.staffId = staffId;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {return time;}

    public void setTime(String time) {this.time = time;}

    public String getTask() {return task;}

    public void setTask(String Task) {this.task = task;}

}
