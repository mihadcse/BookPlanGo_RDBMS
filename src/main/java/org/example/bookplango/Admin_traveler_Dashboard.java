package org.example.bookplango;

public class Admin_traveler_Dashboard {
    Integer NID;
    String Username,Contact;

    public Admin_traveler_Dashboard(Integer NID, String username, String contact) {
        this.NID = NID;
        Username = username;
        Contact = contact;
    }

    public Integer getNID() {
        return NID;
    }

    public void setNID(Integer NID) {
        this.NID = NID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
