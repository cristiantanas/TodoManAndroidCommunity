package org.ct.example.todoman.service;


import android.os.Handler;

import org.ct.example.todoman.model.TodoItemRecord;

import java.util.List;

public class GetItemsService {
    public interface OnFinishedListener {
        void onFinished(List<TodoItemRecord> items);
    }

    public void getItems(final OnFinishedListener listener) {
        final List<TodoItemRecord> todoItemRecords = TodoItemRecord.listAll(TodoItemRecord.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(todoItemRecords);
            }
        }, 3000);

    }
}
