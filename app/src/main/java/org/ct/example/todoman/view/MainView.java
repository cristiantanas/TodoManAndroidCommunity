package org.ct.example.todoman.view;


import java.util.List;

public interface MainView {
    void showProgress();
    void hideProgress();
    void updateView(List<TodoItem> items);
}
