package com.example.collegescheduler2;

import java.util.Date;

public class Course extends ScheduleItem {

    private String instructor;

    public Course(String name, Date date) {
        super(name, date);
    }

    public Course(String name, Date date, String instructor) {
        this(name, date);
        if(instructor == null) {
            throw new IllegalArgumentException("Course must have an instructor");
        }
        this.instructor = instructor;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        if (instructor == null) {
            throw new IllegalArgumentException("Course must have an instructor");
        }
        this.instructor = instructor;
    }

    @Override
    public int compareTo(ScheduleItem o) {
        if(o == null) {
            throw new IllegalArgumentException("Cannot compare course to null");
        }
        if(! (o instanceof Course)) {
            throw new IllegalArgumentException("Cannot compare course to non-course ScheduleItem");
        }
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Course: " + getName() +
                "\nInstructor: " + instructor  +
                "\nDate: " +getDate().toGMTString() + '\n';
    }

    public String genericString() {
        return "Title: " + getName() +
                "\nNotes: " + instructor +
                "\nDate: " + getDate().toGMTString() + '\n';
    }

}
