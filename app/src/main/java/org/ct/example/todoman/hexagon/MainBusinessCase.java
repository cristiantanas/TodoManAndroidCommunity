package org.ct.example.todoman.hexagon;


import java.util.List;

public class MainBusinessCase {
    private MainViewPort mainViewPort;
    private DataModelPort dataModelPort;

    public MainBusinessCase(MainViewPort viewPort, DataModelPort modelPort) {
        this.mainViewPort = viewPort;
        this.dataModelPort = modelPort;
        this.dataModelPort.setBusinessCase(this);
    }

    public void getData() {
        mainViewPort.showProgress();
        dataModelPort.getData();
    }

    public void notifyOnDataLoaded(List<TodoItem> items) {
        mainViewPort.hideProgress();
        mainViewPort.updateView(items);
    }
}
