package com.example.collegescheduler2.ui;

//
//public class NotificationFragment extends Fragment {
//
//    private static final String CHANNEL_ID = "my_channel_id";
//    private static final CharSequence CHANNEL_NAME = "My Channel";
//    private static final String CHANNEL_DESCRIPTION = "This is my notification channel";
//
//    private FragmentNotificationBinding binding;
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//
//        binding = FragmentNotificationBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        // Create and show the notification
//        showNotification();
//
//        return root;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//
//    private void showNotification() {
//        // Create the NotificationManager
//        NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Create the notification channel (for Android 8.0 and higher)
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setDescription(CHANNEL_DESCRIPTION);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        // Build the notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
//                .setSmallIcon(R.drawable.notification_icon)
//                .setContentTitle("My Notification")
//                .setContentText("This is a simple notification.")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        // Display the notification
//        int notificationId = 1;
//        notificationManager.notify(notificationId, builder.build());
//    }
//}


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
import com.example.collegescheduler2.ScheduleItem;
import com.example.collegescheduler2.ToDoList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class NotificationFragment extends Fragment {

    private static ArrayList<ScheduleItem> reminders = new ArrayList<>();
    private ToDoListCustomAdapter<ScheduleItem> adapter;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notification, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new ToDoListCustomAdapter<>(reminders);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper1 = new ItemTouchHelper(swipeLeft());
        ItemTouchHelper helper2 = new ItemTouchHelper(swipeRight());
        helper1.attachToRecyclerView(recyclerView);
        helper2.attachToRecyclerView(recyclerView);

        Button button = (Button) view.findViewById(R.id.addCourseButton);
        button.setOnClickListener(v -> {
            reminders.add(new ToDoList(((EditText) view.findViewById(R.id.courseTitleField)).getText().toString(), new Date()));
            reminders.sort(Comparator.reverseOrder());
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
                reminders.remove(viewHolder.getAdapterPosition());
                reminders.sort(Comparator.reverseOrder());
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

                ScheduleItem scheduleItem = reminders.get(viewHolder.getAdapterPosition());
                String title = ((EditText) view.findViewById(R.id.courseTitleField)).getText().toString();

                if (title.length() == 0) {
                    title = scheduleItem.getName();
                }

                reminders.set(viewHolder.getAdapterPosition(), new ToDoList(title, new Date()));
                reminders.sort(Comparator.reverseOrder());
                adapter.notifyDataSetChanged();

            }
        };
    }


}