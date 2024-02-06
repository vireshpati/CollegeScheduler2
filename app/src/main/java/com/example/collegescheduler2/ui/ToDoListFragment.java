package com.example.collegescheduler2.ui;

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

import com.example.collegescheduler2.R;
import com.example.collegescheduler2.ToDoList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ToDoListFragment extends Fragment {

    private static ArrayList<ToDoList> toDoLists = new ArrayList<>();
    private ToDoListCustomAdapter<ToDoList> adapter;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new ToDoListCustomAdapter<>(toDoLists);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper1 = new ItemTouchHelper(swipeLeft());
        ItemTouchHelper helper2 = new ItemTouchHelper(swipeRight());
        helper1.attachToRecyclerView(recyclerView);
        helper2.attachToRecyclerView(recyclerView);

        Button button = (Button) view.findViewById(R.id.addCourseButton);
        button.setOnClickListener(v -> {
            toDoLists.add(new ToDoList(((EditText) view.findViewById(R.id.courseTitleField)).getText().toString(), new Date()));
            toDoLists.sort(Comparator.reverseOrder());
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
                toDoLists.remove(viewHolder.getAdapterPosition());
                toDoLists.sort(Comparator.reverseOrder());
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

                ToDoList oldToDoList = toDoLists.get(viewHolder.getAdapterPosition());
                String title = ((EditText) view.findViewById(R.id.courseTitleField)).getText().toString();

                if (title.length() == 0) {
                    title = oldToDoList.getName();
                }

                toDoLists.set(viewHolder.getAdapterPosition(), new ToDoList(title, new Date()));
                toDoLists.sort(Comparator.reverseOrder());
                adapter.notifyDataSetChanged();

            }
        };
    }


}