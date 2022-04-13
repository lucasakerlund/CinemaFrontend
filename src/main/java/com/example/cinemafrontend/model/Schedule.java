package com.example.cinemafrontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Schedule {

    private int scheduleId;
    private String date;
    private String time;
    private int movieId;
    private int salon;

    public Schedule(@JsonProperty("schedule_id") int scheduleId,
                    @JsonProperty("date") String date,
                    @JsonProperty("time") String time,
                    @JsonProperty("movie_id") int movieId,
                    @JsonProperty("salon_id") int salon) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.time = time;
        this.movieId = movieId;
        this.salon = salon;
    }

    public int getScheduleId() {return scheduleId;}

    public void setScheduleId(int scheduleId) {this.scheduleId = scheduleId;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getTime() {return time;}

    public void setTime(String time) {this.time = time;}

    public int getMovieId() {return movieId;}

    public void setMovieId(int movieId) {this.movieId = movieId;}

    public int getSalon() {return salon;}

    public void setSalon(int salon) {this.salon = salon;}

}
