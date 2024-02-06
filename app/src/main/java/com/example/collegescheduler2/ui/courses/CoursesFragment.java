package com.example.collegescheduler2.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.R;
import com.example.collegescheduler2.ui.RecyclerViewAction;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    private ArrayList<Course> courses;
    private RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_courses,container,false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RecyclerViewAction());

        return view;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }












}