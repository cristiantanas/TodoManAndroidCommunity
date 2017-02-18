package org.ct.example.todoman.view;

import org.ct.example.todoman.hexagon.CreateBusinessCase;
import org.ct.example.todoman.hexagon.CreateViewPort;

public class CreateViewAdapter implements CreateViewPort {
    private CreateToDoItemActivity createToDoItemView;
    private CreateBusinessCase createBusinessCase;

    public CreateViewAdapter(CreateToDoItemActivity view) {
        this.createToDoItemView = view;
        this.createBusinessCase = new CreateBusinessCase(this);
    }

    public void createItem(String title, String description, String dueDate) {
        TodoItem item = new TodoItem(title, description, dueDate);
        this.createBusinessCase.createItem(item);
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
