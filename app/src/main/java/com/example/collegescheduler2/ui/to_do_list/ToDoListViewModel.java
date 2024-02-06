package com.example.collegescheduler2.ui.to_do_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToDoListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ToDoListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is to do list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}