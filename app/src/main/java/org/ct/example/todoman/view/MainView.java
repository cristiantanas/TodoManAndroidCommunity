package org.ct.example.todoman.view;


import org.ct.example.todoman.TodoItem;

import java.util.List;

public interface MainView {
    void showProgress();
    void hideProgress();
    void updateView(List<TodoItem> items);
}
