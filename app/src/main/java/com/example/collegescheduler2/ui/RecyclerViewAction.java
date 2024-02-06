package com.example.collegescheduler2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.R;

import java.util.ArrayList;
import java.util.Date;


public class RecyclerViewAction extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<Course> courses;
    // Need to implement add from button;

    public RecyclerViewAction() {
        courses = new ArrayList<>();
        courses.add(new Course("Math", new Date(2000,1,1), "John"));
        courses.add(new Course("English", new Date(2010,1,1), "John"));


    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.frame_textview;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.getView().setText(courses.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
