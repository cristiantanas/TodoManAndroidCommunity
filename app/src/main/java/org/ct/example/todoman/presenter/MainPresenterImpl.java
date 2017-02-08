package org.ct.example.todoman.presenter;


import org.ct.example.todoman.view.MainView;
import org.ct.example.todoman.TodoItem;
import org.ct.example.todoman.service.GetItemsService;

import java.util.List;

public class MainPresenterImpl implements MainPresenter, GetItemsService.OnFinishedListener {
    private MainView mainView;
    private GetItemsService itemsService;

    public MainPresenterImpl(MainView view, GetItemsService service) {
        this.mainView = view;
        this.itemsService = service;
    }

    @Override
    public void onResume() {
        if ( mainView != null ) {
            mainView.showProgress();
            itemsService.getItems(this);
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(List<TodoItem> items) {
        if (mainView != null) {
            mainView.updateView(items);
            mainView.hideProgress();
        }
    }
}
