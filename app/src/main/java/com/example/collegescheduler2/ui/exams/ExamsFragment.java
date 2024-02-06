package com.example.collegescheduler2.ui.exams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.collegescheduler2.Course;
import com.example.collegescheduler2.R;
import com.example.collegescheduler2.databinding.FragmentExamsBinding;

import java.util.ArrayList;
import java.util.Date;


public class ExamsFragment extends Fragment {
    private ArrayList<Course> Exams;

    private FragmentExamsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExamsViewModel examsViewModel = new ViewModelProvider(this).get(ExamsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_exams, container, false);

        Exams = new ArrayList<>();
        Exams.add(new Exam("English", new Date(0, 1, 1), "John"));

        binding = FragmentExamsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        examsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}