package org.ct.example.todoman.hexagon;


import org.ct.example.todoman.model.SugarDatabaseAdapter;
import org.ct.example.todoman.view.TodoItem;

import java.util.List;

public class MainBusinessCase {
    private MainViewPort mainViewPort;
    private DataModelPort dataModelPort;

    public MainBusinessCase(MainViewPort viewPort) {
        this.mainViewPort = viewPort;
        this.dataModelPort = new SugarDatabaseAdapter(this);
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
