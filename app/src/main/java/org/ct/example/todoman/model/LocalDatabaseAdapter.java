package org.ct.example.todoman.model;

import android.os.Handler;

import com.mobandme.android.transformer.Transformer;

import org.ct.example.todoman.hexagon.CreateBusinessCase;
import org.ct.example.todoman.hexagon.DataModelPort;
import org.ct.example.todoman.hexagon.MainBusinessCase;
import org.ct.example.todoman.hexagon.TodoItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LocalDatabaseAdapter implements DataModelPort {
    private MainBusinessCase mainBusinessCase;
    private CreateBusinessCase createBusinessCase;

    public LocalDatabaseAdapter() {
    }

    @Override
    public void setBusinessCase(MainBusinessCase businessCase) {
        this.mainBusinessCase = businessCase;
    }

    @Override
    public void setBusinessCase(CreateBusinessCase businessCase) {
        this.createBusinessCase = businessCase;
    }

    @Override
    public void getData() {
        final List<TodoItemRecord> todoItemRecords = TodoItemRecord.listAll(TodoItemRecord.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyOnDataLoaded(todoItemRecords);
            }
        }, 3000);
    }

    @Override
    public void createItem(TodoItem item) {
        boolean error = false;
        try {
            if ( item.hasEmptyTitle() ) {
                notifyTitleRequired();
                error = true;
            }

            if ( !item.hasDateNotSet() ) {
                SimpleDateFormat formatter = new SimpleDateFormat("d/MM/yyyy");
                Date dueDateParsed = formatter.parse(item.getDueDate());
                if ( dueDateParsed.before(new Date()) ) {
                    notifyInvalidDueDate();
                    error = true;
                }
            }

            if ( !error ) {
                TodoItemRecord todoItemRecord = transform(item);
                todoItemRecord.save();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notifyOnSuccess();
                    }
                }, 3000);
            }

        } catch (Exception e) {
            notifyOnUnexpectedError();
        }
    }

    private void notifyOnDataLoaded(List<TodoItemRecord> records) {
        List<TodoItem> data = transform(records);
        if ( this.mainBusinessCase != null ) {
            mainBusinessCase.notifyOnDataLoaded(data);
        }
    }

    private void notifyOnSuccess() {
        if ( this.createBusinessCase != null ) {
            this.createBusinessCase.onSuccess();
        }
    }

    private void notifyInvalidDueDate() {
        if ( this.createBusinessCase != null ) {
            this.createBusinessCase.onInvalidDueDate();
        }
    }

    private void notifyTitleRequired() {
        if ( this.createBusinessCase != null ) {
            this.createBusinessCase.onTitleRequired();
        }
    }

    private void notifyOnUnexpectedError() {
        if ( this.createBusinessCase != null ) {
            this.createBusinessCase.onUnexpectedError();
        }
    }

    private TodoItemRecord transform(TodoItem item) {
        Transformer todoItemTransformer = new Transformer.Builder()
                .build(TodoItem.class);
        return todoItemTransformer.transform(item, TodoItemRecord.class);
    }

    private List<TodoItem> transform(List<TodoItemRecord> items) {
        Transformer todoItemTransformer = new Transformer.Builder()
                .build(TodoItem.class);
        List<TodoItem> viewItems = new ArrayList<>();
        for (TodoItemRecord modelItem : items) {
            viewItems.add( todoItemTransformer.transform(modelItem, TodoItem.class) );
        }

        return viewItems;
    }
}
