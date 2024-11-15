package org.example.bookplango;

public class SeeBookingDetailsCar {
    private String licsence,date,username,userContact;
    SeeBookingDetailsCar(String l,String d,String un,String uc){
        licsence=l;
        date=d;
        username=un;
        userContact=uc;
    }

    public String getLicsence() {
        return licsence;
    }

    public void setLicsence(String licsence) {
        this.licsence = licsence;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }
}
