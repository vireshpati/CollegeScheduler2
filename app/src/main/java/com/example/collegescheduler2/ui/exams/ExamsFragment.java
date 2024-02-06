package com.example.collegescheduler2.ui.exams;

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


import com.example.collegescheduler2.Exam;
import com.example.collegescheduler2.R;
import com.example.collegescheduler2.ui.CustomAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ExamsFragment extends Fragment {

    private static ArrayList<Exam> exams = new ArrayList<>();
    private CustomAdapter<Exam> adapter;
    private View view;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_exams, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new CustomAdapter<>(exams);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper1 = new ItemTouchHelper(swipeLeft());
        ItemTouchHelper helper2 = new ItemTouchHelper(swipeRight());
        helper1.attachToRecyclerView(recyclerView);
        helper2.attachToRecyclerView(recyclerView);


        Button button = (Button) view.findViewById(R.id.addCourseButton);

        button.setOnClickListener(v -> {

            exams.add(newExamFromTextFields());
            exams.sort(Comparator.naturalOrder());
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

                exams.remove(viewHolder.getAdapterPosition());
                exams.sort(Comparator.naturalOrder());
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

                Exam oldExam = exams.get(viewHolder.getAdapterPosition());
                String location = ((EditText) view.findViewById(R.id.instructorNameField)).getText().toString();
                String date = ((EditText) view.findViewById(R.id.dateField)).getText().toString();
                String title = ((EditText) view.findViewById(R.id.courseTitleField)).getText().toString();
                if (location.length() == 0) {
                    location = oldExam.getLocation();
                }
                Date d;
                if (date.length() == 0) {
                    d = oldExam.getDate();
                } else {
                    try {
                        d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
                    } catch (ParseException e) {
                        d = new Date();

                    }
                }
                if (title.length() == 0) {
                    title = oldExam.getName();
                }

                exams.set(viewHolder.getAdapterPosition(), new Exam(title, d, location));
                exams.sort(comparator);
                adapter.notifyDataSetChanged();

            }
        };
    }

    private Exam newExamFromTextFields() {
        String title = ((EditText) view.findViewById(R.id.courseTitleField)).getText().toString();
        String name = ((EditText) view.findViewById(R.id.instructorNameField)).getText().toString();
        String date = ((EditText) view.findViewById(R.id.dateField)).getText().toString();
        try {
            return new Exam(title, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date), name);
        } catch (ParseException e) {
            return null;
        }
    }

    private Comparator<Exam> comparator = Comparator.naturalOrder();


}