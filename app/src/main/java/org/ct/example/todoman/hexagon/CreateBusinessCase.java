package org.ct.example.todoman.hexagon;


public class CreateBusinessCase {
    private CreateViewPort createViewPort;
    private DataModelPort dataModelPort;

    public CreateBusinessCase(CreateViewPort viewPort, DataModelPort modelPort) {
        this.createViewPort = viewPort;
        this.dataModelPort = modelPort;
        this.dataModelPort.setBusinessCase(this);
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
