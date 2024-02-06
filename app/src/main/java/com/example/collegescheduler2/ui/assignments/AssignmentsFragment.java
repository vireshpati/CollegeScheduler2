package com.example.collegescheduler2.ui.assignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler2.Assignment;
import com.example.collegescheduler2.R;
import com.example.collegescheduler2.ui.CustomAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class AssignmentsFragment extends Fragment {

    private static ArrayList<Assignment> assignments = new ArrayList<>();
    private CustomAdapter<Assignment> adapter;
    private View view;
    private static boolean sortByClass = true;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_assignments, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new CustomAdapter<>(assignments);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper1 = new ItemTouchHelper(swipeLeft());
        ItemTouchHelper helper2 = new ItemTouchHelper(swipeRight());
        helper1.attachToRecyclerView(recyclerView);
        helper2.attachToRecyclerView(recyclerView);

        Switch sortSwitch = (Switch) view.findViewById(R.id.switch1);
        sortSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sortByClass = !sortByClass;
                assignments.sort(getComparator());
                adapter.notifyDataSetChanged();
            }
        });

        Button button = (Button) view.findViewById(R.id.addCourseButton);
        button.setOnClickListener(v -> {

            assignments.add(newAssignmentFromTextFields());
            assignments.sort(getComparator());
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

                assignments.remove(viewHolder.getAdapterPosition());
                assignments.sort(getComparator());
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

                Assignment oldAssignment = assignments.get(viewHolder.getAdapterPosition());
                String name = ((EditText) view.findViewById(R.id.instructorNameField)).getText().toString();
                String date = ((EditText) view.findViewById(R.id.dateField)).getText().toString();
                String title = ((EditText) view.findViewById(R.id.courseTitleField)).getText().toString();
                if (name.length() == 0) {
                    name = oldAssignment.getAssociatedCourse();
                }
                Date d;
                if (date.length() == 0) {
                    d = oldAssignment.getDate();
                } else {
                    try {
                        d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
                    } catch (ParseException e) {
                        d = new Date();

                    }
                }
                if (title.length() == 0) {
                    title = oldAssignment.getName();
                }

                assignments.set(viewHolder.getAdapterPosition(), new Assignment(title, d, name));
                assignments.sort(getComparator());
                adapter.notifyDataSetChanged();

            }
        };
    }

    private Assignment newAssignmentFromTextFields() {

        String title = ((EditText) view.findViewById(R.id.courseTitleField)).getText().toString();

        String associatedClass = ((EditText) view.findViewById(R.id.instructorNameField)).getText().toString();

        String date = ((EditText) view.findViewById(R.id.dateField)).getText().toString();
        try {
            return new Assignment(title, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date), associatedClass);
        } catch (ParseException e) {
            return null;
        }
    }

    private Comparator<Assignment> getComparator() {
        if (sortByClass) {
            return (a1, a2) -> a1.getName().compareTo(a2.getName());
        }
        return Comparator.naturalOrder();
    }


}