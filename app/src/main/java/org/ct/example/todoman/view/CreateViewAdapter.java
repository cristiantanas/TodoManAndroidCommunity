package org.ct.example.todoman.view;

import com.mobandme.android.transformer.Transformer;

import org.ct.example.todoman.BusinessCaseFactory;
import org.ct.example.todoman.hexagon.CreateBusinessCase;
import org.ct.example.todoman.hexagon.CreateViewPort;
import org.ct.example.todoman.hexagon.TodoItem;
import org.ct.example.todoman.model.TodoItemRecord;

import java.util.ArrayList;
import java.util.List;

public class CreateViewAdapter implements CreateViewPort {
    private CreateToDoItemActivity createToDoItemView;
    private CreateBusinessCase createBusinessCase;

    public CreateViewAdapter(CreateToDoItemActivity view) {
        this.createToDoItemView = view;
        this.createBusinessCase = BusinessCaseFactory.getCreateBusinessCase(this);
    }

    public void createItem(String title, String description, String dueDate) {
        TodoItemViewModel item = new TodoItemViewModel(title, description, dueDate);
        this.createBusinessCase.createItem(transform(item));
    }

    private TodoItem transform(TodoItemViewModel item) {
        Transformer todoItemTransformer = new Transformer.Builder()
                .build(TodoItemViewModel.class);
        return todoItemTransformer.transform(item, TodoItem.class);
    }

    @Override
    public void showProgress() {
        this.createToDoItemView.showProgress();
    }

    @Override
    public void hideProgress() {
        this.createToDoItemView.hideProgress();
    }

    @Override
    public void displayRequiredTitleError() {
        this.createToDoItemView.displayRequiredTitleError();
    }

    @Override
    public void displayInvalidDateError() {
        this.createToDoItemView.displayInvalidDateError();
    }

    @Override
    public void onItemCreated() {
        this.createToDoItemView.onItemCreated();
    }

    @Override
    public void onUnexpectedError() {
        this.createToDoItemView.onUnexpectedError();
    }
}
