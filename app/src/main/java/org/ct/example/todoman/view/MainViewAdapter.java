package org.ct.example.todoman.view;

import org.ct.example.todoman.hexagon.MainBusinessCase;
import org.ct.example.todoman.hexagon.MainViewPort;

import java.util.List;


public class MainViewAdapter implements MainViewPort {
    private MainActivity mainView;
    private MainBusinessCase mainBusinessCase;

    public MainViewAdapter(MainActivity view) {
        this.mainView = view;
        this.mainBusinessCase = new MainBusinessCase(this);
    }

    public void getData() {
        mainBusinessCase.getData();
    }

    @Override
    public void showProgress() {
        mainView.showProgress();
    }

    @Override
    public void hideProgress() {
        mainView.hideProgress();
    }

    @Override
    public void updateView(List<TodoItem> items) {
        mainView.setListItems(items);
    }
}
