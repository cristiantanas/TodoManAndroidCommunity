package org.ct.example.todoman.presenter;


import com.mobandme.android.transformer.Transformer;

import org.ct.example.todoman.view.MainView;
import org.ct.example.todoman.model.TodoItemRecord;
import org.ct.example.todoman.service.GetItemsService;
import org.ct.example.todoman.view.TodoItem;

import java.util.ArrayList;
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
    public void onFinished(List<TodoItemRecord> items) {
        if (mainView != null) {
            mainView.updateView( fromModelItems(items) );
            mainView.hideProgress();
        }
    }

    private List<TodoItem> fromModelItems(List<TodoItemRecord> items) {
        Transformer todoItemTransformer = new Transformer.Builder()
                .build(TodoItem.class);
        List<TodoItem> viewItems = new ArrayList<>();
        for (TodoItemRecord modelItem : items) {
            viewItems.add( todoItemTransformer.transform(modelItem, TodoItem.class) );
        }

        return viewItems;
    }
}
