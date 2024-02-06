package com.example.collegescheduler2;

import androidx.annotation.NonNull;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.ScheduleItem;

import java.util.Date;

public class Assignment extends ScheduleItem {

    private String associatedCourse;

    public Assignment(String name, Date date) {
        super(name, date);
    }

    public Assignment(String name, Date date, String associatedCourse) {
        this(name, date);
        if (associatedCourse == null) {
            throw new IllegalArgumentException("Assignment must be associated with a course");
        }
        this.associatedCourse = associatedCourse;
    }

    public String getAssociatedCourse() {
        return associatedCourse;
    }

    public void setAssociatedCourse(String associatedCourse) {
        if (associatedCourse == null) {
            throw new IllegalArgumentException("Assignment must be associated with a course");
        }
        this.associatedCourse = associatedCourse;
    }

    @NonNull
    @Override
    public String toString() {
        return "Assignment: " + getName() +
                "\nAssociated Class: " + associatedCourse +
                "\nDue Date: " + getDate().toGMTString() +"\n";
     }

}
