package com.example.collegescheduler2;

import androidx.annotation.NonNull;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.ScheduleItem;

import java.util.Date;

public class Assignment extends ScheduleItem {

    private Course associatedCourse;

    public Assignment(String name, Date date) {
        super(name, date);
    }

    public Assignment(String name, Date date, Course associatedCourse) {
        this(name, date);
        if (associatedCourse == null) {
            throw new IllegalArgumentException("Assignment must be associated with a course");
        }
        this.associatedCourse = associatedCourse;
    }

    public Course getAssociatedCourse() {
        return associatedCourse;
    }

    public void setAssociatedCourse(Course associatedCourse) {
        if (associatedCourse == null) {
            throw new IllegalArgumentException("Assignment must be associated with a course");
        }
        this.associatedCourse = associatedCourse;
    }

    @NonNull
    @Override
    public String toString() {
        return "Assignment{" +
                "name=" + getName() +
                ", date=" + getDate() +
                "associatedCourse=" + associatedCourse +
                '}';
    }

}
