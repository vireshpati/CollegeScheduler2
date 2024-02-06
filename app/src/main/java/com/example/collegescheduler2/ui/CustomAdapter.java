package com.example.collegescheduler2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Course> courses;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {

            super(view);
            textView = view.findViewById(R.id.randomText);

        }

        public TextView getTextView() {
            return textView;
        }
    }

    public CustomAdapter(ArrayList<Course> courses) {

        this.courses = courses;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frame_textview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.getTextView().setText(courses.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return courses.size();
    }





}
