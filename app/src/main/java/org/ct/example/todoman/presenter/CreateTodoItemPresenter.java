package org.ct.example.todoman.presenter;


public interface CreateTodoItemPresenter {
    void onDestroy();
    void validateTodoItemProperties(String title, String description, String dueDate);
}
