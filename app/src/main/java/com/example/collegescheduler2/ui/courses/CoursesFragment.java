package com.example.collegescheduler2.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.collegescheduler2.R;
import com.example.collegescheduler2.databinding.FragmentCoursesBinding;


public class CoursesFragment extends Fragment {

    private FragmentCoursesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;

//        View root = inflater.inflate(R.layout.fragment_courses,container,false);
//        final TextView textView = root.findViewById(R.id.text_home);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}