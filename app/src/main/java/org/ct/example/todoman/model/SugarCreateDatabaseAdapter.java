package org.ct.example.todoman.model;

import android.os.Handler;

import com.mobandme.android.transformer.Transformer;

import org.ct.example.todoman.hexagon.CreateBusinessCase;
import org.ct.example.todoman.hexagon.CreateDataModelPort;
import org.ct.example.todoman.view.TodoItem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SugarCreateDatabaseAdapter implements CreateDataModelPort {
    private CreateBusinessCase createBusinessCase;

    public SugarCreateDatabaseAdapter(CreateBusinessCase businessCase) {
        this.createBusinessCase = businessCase;
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

    private void notifyOnSuccess() {
        this.createBusinessCase.onSuccess();
    }

    private void notifyInvalidDueDate() {
        this.createBusinessCase.onInvalidDueDate();
    }

    private void notifyTitleRequired() {
        this.createBusinessCase.onTitleRequired();
    }

    private void notifyOnUnexpectedError() {
        this.createBusinessCase.onUnexpectedError();
    }

    private TodoItemRecord transform(TodoItem item) {
        Transformer todoItemTransformer = new Transformer.Builder()
                .build(TodoItem.class);
        return todoItemTransformer.transform(item, TodoItemRecord.class);
    }
}
