package org.ct.example.todoman.hexagon;


import org.ct.example.todoman.model.SugarGetDatabaseAdapter;
import org.ct.example.todoman.view.TodoItem;

import java.util.List;

public class MainBusinessCase {
    private MainViewPort mainViewPort;
    private GetDataModelPort dataModelPort;

    public MainBusinessCase(MainViewPort viewPort) {
        this.mainViewPort = viewPort;
        this.dataModelPort = new SugarGetDatabaseAdapter(this);
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
