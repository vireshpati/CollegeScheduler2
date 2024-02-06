package com.example.collegescheduler2.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.R;
import com.example.collegescheduler2.ui.CustomAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CoursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Course> courses;

    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        courses = new ArrayList<>();

        courses.add(new Course("English", new Date(0, 1, 1), "John"));

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CustomAdapter adapter = new CustomAdapter(courses);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(swipeToDelete(adapter));
        helper.attachToRecyclerView(recyclerView);

        button = view.findViewById(R.id.addCourseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private ItemTouchHelper.SimpleCallback swipeToDelete(CustomAdapter adapter) {
        return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                courses.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };
    }


}