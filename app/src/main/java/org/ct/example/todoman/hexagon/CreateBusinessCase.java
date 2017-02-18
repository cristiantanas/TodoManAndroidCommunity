package org.ct.example.todoman.hexagon;


import org.ct.example.todoman.model.SugarCreateDatabaseAdapter;
import org.ct.example.todoman.view.TodoItem;

public class CreateBusinessCase {
    private CreateViewPort createViewPort;
    private CreateDataModelPort dataModelPort;

    public CreateBusinessCase(CreateViewPort viewPort) {
        this.createViewPort = viewPort;
        this.dataModelPort = new SugarCreateDatabaseAdapter(this);
    }

    public void createItem(TodoItem item) {
        this.createViewPort.showProgress();
        this.dataModelPort.createItem(item);
    }

    public void onTitleRequired() {
        this.createViewPort.hideProgress();
        this.createViewPort.displayRequiredTitleError();
    }

    public void onInvalidDueDate() {
        this.createViewPort.hideProgress();
        this.createViewPort.displayInvalidDateError();
    }

    public void onUnexpectedError() {
        this.createViewPort.hideProgress();
        this.createViewPort.onUnexpectedError();
    }

    public void onSuccess() {
        this.createViewPort.hideProgress();
        this.createViewPort.onItemCreated();
    }
}
