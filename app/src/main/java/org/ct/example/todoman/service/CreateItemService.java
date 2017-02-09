package org.ct.example.todoman.service;

import android.os.Handler;

import org.ct.example.todoman.TodoItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateItemService {
    public interface OnFinishedListener {
        void onTitleRequiredError();
        void onInvalidDueDateError();
        void onSuccess();
        void onError();
    }

    public void createItem(String title, String description, String dueDate,
                           final OnFinishedListener listener) {
        boolean error = false;
        try {
            if ( title == null || title.isEmpty() ) {
                listener.onTitleRequiredError();
                error = true;
            }

            if ( !dueDate.equalsIgnoreCase("Date not set") ) {
                SimpleDateFormat formatter = new SimpleDateFormat("d/MM/yyyy");
                Date dueDateParsed = formatter.parse(dueDate);
                if ( dueDateParsed.before(new Date()) ) {
                    listener.onInvalidDueDateError();
                    error = true;
                }
            }

            if ( !error ) {
                TodoItem todoItem = new TodoItem(title, description, dueDate);
                todoItem.save();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess();
                    }
                }, 3000);
            }

        } catch (Exception e) {
            listener.onError();
        }

    }
}
