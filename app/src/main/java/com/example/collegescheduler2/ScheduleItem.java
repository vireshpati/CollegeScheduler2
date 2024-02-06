package com.example.collegescheduler2;

import java.util.Date;

public abstract class ScheduleItem implements Comparable<ScheduleItem> {

    private String name;
    private Date date;


    public ScheduleItem(String name, Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.date = date;
    }

    @Override
    public int compareTo(ScheduleItem o) {
        return this.date.compareTo(o.date);
    }

}
