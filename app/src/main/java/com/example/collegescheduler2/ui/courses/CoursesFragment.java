package com.example.collegescheduler2.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.R;
import com.example.collegescheduler2.ui.CustomAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class CoursesFragment extends Fragment {

    private static ArrayList<Course> courses = new ArrayList<>();
    private CustomAdapter<Course> adapter;
    private View view;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_courses, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new CustomAdapter<>(courses);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper1 = new ItemTouchHelper(swipeLeft());
        ItemTouchHelper helper2 = new ItemTouchHelper(swipeRight());
        helper1.attachToRecyclerView(recyclerView);
        helper2.attachToRecyclerView(recyclerView);


        Button button = (Button) view.findViewById(R.id.addCourseButton);

        button.setOnClickListener(v -> {

                courses.add(newCourseFromTextFields());
                courses.sort(comparator);
                adapter.notifyDataSetChanged();
        });


        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private ItemTouchHelper.SimpleCallback swipeLeft() {
        return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                courses.remove(viewHolder.getAdapterPosition());
                courses.sort(comparator);
                adapter.notifyDataSetChanged();
            }
        };
    }

    private ItemTouchHelper.SimpleCallback swipeRight() {
        return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Course oldCourse = courses.get(viewHolder.getAdapterPosition());
                String name = ((EditText) view.findViewById(R.id.instructorNameField)).getText().toString();
                String date = ((EditText) view.findViewById(R.id.dateField)).getText().toString();
                String title = ((EditText) view.findViewById(R.id.courseTitleField)).getText().toString();
                if(name.length() == 0) {
                    name = oldCourse.getInstructor();
                }
                Date d;
                if(date.length() == 0) {
                    d = oldCourse.getDate();
                } else {
                    try {
                        d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
                    } catch (ParseException e) {
                        d = new Date();

                    }
                }
                if(title.length() == 0) {
                    title = oldCourse.getName();
                }

                courses.set(viewHolder.getAdapterPosition(), new Course(title, d, name));
                courses.sort(comparator);
                adapter.notifyDataSetChanged();

            }
        };
    }

    private Course newCourseFromTextFields() {
        String title = ((EditText) view.findViewById(R.id.courseTitleField)).getText().toString();
        String name = ((EditText) view.findViewById(R.id.instructorNameField)).getText().toString();
        String date = ((EditText) view.findViewById(R.id.dateField)).getText().toString();
        try {
            return new Course(title, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date), name);
        }
        catch (ParseException e) {
            return null;
        }
    }

    private Comparator<Course> comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());


}