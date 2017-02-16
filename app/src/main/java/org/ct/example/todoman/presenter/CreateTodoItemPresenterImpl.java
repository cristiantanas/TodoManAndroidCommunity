package org.ct.example.todoman.presenter;


import org.ct.example.todoman.service.CreateItemService;
import org.ct.example.todoman.hexagon.CreateViewPort;

public class CreateTodoItemPresenterImpl implements CreateTodoItemPresenter,
        CreateItemService.OnFinishedListener {
    private CreateViewPort createView;
    private CreateItemService createService;

    public CreateTodoItemPresenterImpl(CreateViewPort view, CreateItemService service) {
        this.createView = view;
        this.createService = service;
    }

    @Override
    public void onDestroy() {
        createView = null;
    }

    @Override
    public void validateTodoItemProperties(String title, String description, String dueDate) {
        if ( createView != null ) {
            createView.showProgress();
            createService.createItem(title, description, dueDate, this);
        }
    }

    @Override
    public void onTitleRequiredError() {
        if ( createView != null ) {
            createView.displayRequiredTitleError();
            createView.hideProgress();
        }
    }

    @Override
    public void onInvalidDueDateError() {
        if ( createView != null ) {
            createView.displayInvalidDateError();
            createView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if ( createView != null ) {
            createView.onItemCreated();
            createView.hideProgress();
        }
    }

    @Override
    public void onError() {
        if ( createView != null ) {
            createView.onUnexpectedError();
            createView.hideProgress();
        }
    }
}
