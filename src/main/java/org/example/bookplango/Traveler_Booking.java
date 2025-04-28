package org.example.bookplango;

public class Traveler_Booking {

    String Name,Address,Contact,rating;

    public Traveler_Booking(String name, String address, String contact, String rating) {
        Name = name;
        Address = address;
        Contact = contact;
        this.rating=rating;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating= rating;
    }
}
