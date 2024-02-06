package com.example.collegescheduler2;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.ScheduleItem;

import java.util.Date;

public class Exam extends ScheduleItem {
    private String location;

    public Exam(String name, Date date) {
        super(name, date);
    }

    public Exam(String name, Date date, String location) {
        this(name, date);
        if (location == null) {
            throw new IllegalArgumentException("Exam must be associated with a location");
        }
        this.location = location;
    }

    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        if (location == null) {
            throw new IllegalArgumentException("Exam must be associated with a location");
        }
        this.location = location;
    }


    @Override
    public String toString() {
        return "Exam: " + getName() +
                "\nLocation: " + location +
                "\nDate: " + getDate().toGMTString() + '\n';
    }
}
