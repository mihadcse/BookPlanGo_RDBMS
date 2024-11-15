package org.example.bookplango;

public class Traveler_Message {
    String name,message;

    public Traveler_Message(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    Integer id;

    public Traveler_Message(String message, Integer id) {
        this.message = message;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Traveler_Message(String name, String message, Integer id) {
        this.name = name;
        this.message = message;
        this.id = id;
    }

}
