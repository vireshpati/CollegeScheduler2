package com.example.collegescheduler2.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public NotificationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notification fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}