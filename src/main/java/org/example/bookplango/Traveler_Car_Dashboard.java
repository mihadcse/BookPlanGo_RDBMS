package org.example.bookplango;

import java.util.Date;

public class Traveler_Car_Dashboard {
    Integer travelID;  // Use travelID instead of carid
    String car_lisence, start_d, end_d;
    Date date;

    // Constructor now includes travelID
    public Traveler_Car_Dashboard(Integer travelID, String car_lisence, String start_d, String end_d, Date date) {
        this.travelID = travelID;
        this.car_lisence = car_lisence;
        this.start_d = start_d;
        this.end_d = end_d;
        this.date = date;
    }

    // Getters and Setters for travelID, car_lisence, start_d, end_d, date
    public Integer getTravelID() {
        return travelID;
    }

    public void setTravelID(Integer travelID) {
        this.travelID = travelID;
    }

    public String getCar_lisence() {
        return car_lisence;
    }

    public void setCar_lisence(String car_lisence) {
        this.car_lisence = car_lisence;
    }

    public String getStart_d() {
        return start_d;
    }

    public void setStart_d(String start_d) {
        this.start_d = start_d;
    }

    public String getEnd_d() {
        return end_d;
    }

    public void setEnd_d(String end_d) {
        this.end_d = end_d;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Traveler_Car_Dashboard{" +
                "travelID=" + travelID +
                ", car_lisence='" + car_lisence + '\'' +
                ", start_d='" + start_d + '\'' +
                ", end_d='" + end_d + '\'' +
                ", date=" + date +
                '}';
    }
}
