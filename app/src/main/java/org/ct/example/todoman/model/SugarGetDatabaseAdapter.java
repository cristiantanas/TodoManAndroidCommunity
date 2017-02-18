package org.ct.example.todoman.model;

import android.os.Handler;

import com.mobandme.android.transformer.Transformer;

import org.ct.example.todoman.hexagon.GetDataModelPort;
import org.ct.example.todoman.hexagon.MainBusinessCase;
import org.ct.example.todoman.view.TodoItem;

import java.util.ArrayList;
import java.util.List;


public class SugarGetDatabaseAdapter implements GetDataModelPort {
    private MainBusinessCase mainBusinessCase;

    public SugarGetDatabaseAdapter(MainBusinessCase mainBusinessCase) {
        this.mainBusinessCase = mainBusinessCase;
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

    private void notifyOnDataLoaded(List<TodoItemRecord> records) {
        List<TodoItem> data = fromModelItems(records);
        mainBusinessCase.notifyOnDataLoaded(data);
    }

    private List<TodoItem> fromModelItems(List<TodoItemRecord> items) {
        Transformer todoItemTransformer = new Transformer.Builder()
                .build(TodoItem.class);
        List<TodoItem> viewItems = new ArrayList<>();
        for (TodoItemRecord modelItem : items) {
            viewItems.add( todoItemTransformer.transform(modelItem, TodoItem.class) );
        }

        return viewItems;
    }
}
