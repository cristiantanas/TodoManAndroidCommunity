package org.ct.example.todoman.service;


import android.os.Handler;

import org.ct.example.todoman.TodoItem;

import java.util.List;

public class GetItemsService {
    public interface OnFinishedListener {
        void onFinished(List<TodoItem> items);
    }

    public void getItems(final OnFinishedListener listener) {
        final List<TodoItem> todoItems = TodoItem.listAll(TodoItem.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(todoItems);
            }
        }, 3000);

    }
}
