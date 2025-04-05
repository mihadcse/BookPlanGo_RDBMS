package org.example.bookplango;

public class Statistics_Table {
    String name;
    Integer count;
    Integer ID;

    public Statistics_Table(Integer ID, Integer count) {
        this.ID = ID;
        this.count = count;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Statistics_Table(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
