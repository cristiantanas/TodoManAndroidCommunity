package org.ct.example.todoman.view;

import com.mobandme.android.transformer.Transformer;

import org.ct.example.todoman.BusinessCaseFactory;
import org.ct.example.todoman.hexagon.CreateBusinessCase;
import org.ct.example.todoman.hexagon.MainBusinessCase;
import org.ct.example.todoman.hexagon.MainViewPort;
import org.ct.example.todoman.hexagon.TodoItem;

import java.util.ArrayList;
import java.util.List;


public class MainViewAdapter implements MainViewPort {
    private MainActivity mainView;
    private MainBusinessCase mainBusinessCase;

    public MainViewAdapter(MainActivity view) {
        this.mainView = view;
        this.mainBusinessCase = BusinessCaseFactory.getMainBusinessCase(this);
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
        mainView.setListItems(transform(items));
    }

    private List<TodoItemViewModel> transform(List<TodoItem> items) {
        Transformer todoItemTransformer = new Transformer.Builder()
                .build(TodoItemViewModel.class);
        List<TodoItemViewModel> viewItems = new ArrayList<>();
        for (TodoItem modelItem : items) {
            viewItems.add( todoItemTransformer.transform(modelItem, TodoItemViewModel.class) );
        }

        return viewItems;
    }
}
