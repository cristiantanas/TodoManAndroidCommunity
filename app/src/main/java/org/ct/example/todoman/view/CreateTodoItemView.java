package org.ct.example.todoman.view;


public interface CreateTodoItemView {
    void showProgress();
    void hideProgress();
    void displayRequiredTitleError();
    void displayInvalidDateError();
    void onItemCreated();
    void onUnexpectedError();
}
