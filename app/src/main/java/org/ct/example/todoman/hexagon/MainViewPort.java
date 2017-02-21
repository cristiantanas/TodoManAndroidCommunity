package org.ct.example.todoman.hexagon;


import java.util.List;

public interface MainViewPort {
    void showProgress();
    void hideProgress();
    void updateView(List<TodoItem> items);
}
