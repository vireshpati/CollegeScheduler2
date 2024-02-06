package com.example.collegescheduler2;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ToDoList extends ScheduleItem {

    private ArrayList<ScheduleItem> toDoList;

    public ToDoList(String name, Date date) {
        super(name, new Date());
        toDoList = new ArrayList<>();
    }

    public void markAsDone(ScheduleItem s) {
        if (s == null) {
            throw new IllegalArgumentException("Cannot mark null as done");
        }
        toDoList.remove(s);
    }

    public void addItem(ScheduleItem s) {
        if (s == null) {
            throw new IllegalArgumentException("Cannot add null to do");
        }
        toDoList.add(s);
    }

    public ArrayList<ScheduleItem> getToDoList() {
        return toDoList;
    }

    public void setToDoList(ArrayList<ScheduleItem> toDoList) {
        this.toDoList = toDoList;
    }

    public void insert(ScheduleItem s, int index) {
        toDoList.add(index, s);
    }

    public ScheduleItem getAtIndex(int index) {
        if (index < 0 || index >= toDoList.size()) {
            throw new IndexOutOfBoundsException("Index + " + index + " out of bounds for length " + toDoList.size());
        }
        return toDoList.get(index);
    }

    public void setAtIndex(ScheduleItem s, int index) {
        if (index < 0 || index >= toDoList.size()) {
            throw new IndexOutOfBoundsException("Index + " + index + " out of bounds for length " + toDoList.size());
        }

        toDoList.set(index, s);
    }



    @NonNull
    @Override
    public String toString() {
        return getName();
    }

    public void sort() {
        toDoList.sort(Comparator.naturalOrder());

    }

    public ScheduleItem removeItem(int index) {
       return toDoList.remove(index);
    }
}
