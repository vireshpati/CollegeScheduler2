package com.example.collegescheduler2.ui.exams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.R;
import com.example.collegescheduler2.databinding.FragmentExamsBinding;
import com.example.collegescheduler2.ui.CustomAdapter;

import java.util.ArrayList;
import java.util.Date;


public class ExamsFragment extends Fragment {
    private ArrayList<Exam> Exams;

    private FragmentExamsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExamsViewModel examsViewModel = new ViewModelProvider(this).get(ExamsViewModel.class);

        View view = inflater.inflate(R.layout.fragment_exams, container, false);
        Exams = new ArrayList<Exam>();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CustomAdapter<Course> adapter = new CustomAdapter<Course>(courses);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(swipeToDelete(adapter));
        helper.attachToRecyclerView(recyclerView);

        Button button = view.findViewById(R.id.addCourseButton);
        button.setOnClickListener(v -> {
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}