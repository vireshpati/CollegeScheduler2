package com.example.collegescheduler2.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationViewModel extends ViewModel {
    private MutableLiveData<String> notificationContent = new MutableLiveData<>();

    // Method to update notification content
    public void setNotificationContent(String content) {
        notificationContent.setValue(content);
    }

    // LiveData for observing notification content
    public LiveData<String> getNotificationContent() {
        return notificationContent;
    }
}
