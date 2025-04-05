package org.example.bookplango;

public class Traveler_Planning {
    String place_name, place_district, place_division;

    public Traveler_Planning(String place_name, String place_district, String place_division) {
        this.place_name = place_name;
        this.place_district = place_district;
        this.place_division = place_division;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_district() {
        return place_district;
    }

    public void setPlace_district(String place_district) {
        this.place_district = place_district;
    }

    public String getPlace_division() {
        return place_division;
    }

    public void setPlace_division(String place_division) {
        this.place_division = place_division;
    }
}
