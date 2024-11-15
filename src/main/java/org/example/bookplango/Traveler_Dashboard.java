package org.example.bookplango;

import java.util.Date;

public class Traveler_Dashboard {
    Integer traveler_ID,Total_Expenses;
    String destination,hotel_name;
    Date StartDate,EndDate;

    public Traveler_Dashboard(Integer total_Expenses, String destination, String hotel_name, Date startDate, Date endDate) {
        this.traveler_ID = traveler_ID;
        Total_Expenses = total_Expenses;
        this.destination = destination;
        this.hotel_name = hotel_name;
        StartDate = startDate;
        EndDate = endDate;
    }

    public Integer getTraveler_ID() {
        return traveler_ID;
    }

    public void setTraveler_ID(Integer traveler_ID) {
        this.traveler_ID = traveler_ID;
    }

    public Integer getTotal_Expenses() {
        return Total_Expenses;
    }

    public void setTotal_Expenses(Integer total_Expenses) {
        Total_Expenses = total_Expenses;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
}
