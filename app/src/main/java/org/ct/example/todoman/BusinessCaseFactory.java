package org.ct.example.todoman;


import org.ct.example.todoman.hexagon.CreateBusinessCase;
import org.ct.example.todoman.hexagon.CreateViewPort;
import org.ct.example.todoman.hexagon.DataModelPort;
import org.ct.example.todoman.hexagon.MainBusinessCase;
import org.ct.example.todoman.hexagon.MainViewPort;
import org.ct.example.todoman.model.LocalDatabaseAdapter;

public class BusinessCaseFactory {
    public static MainBusinessCase getMainBusinessCase(MainViewPort viewPort) {
        DataModelPort modelPort = new LocalDatabaseAdapter();
        return new MainBusinessCase(viewPort, modelPort);
    }

    public static CreateBusinessCase getCreateBusinessCase(CreateViewPort viewPort) {
        DataModelPort modelPort = new LocalDatabaseAdapter();
        return new CreateBusinessCase(viewPort, modelPort);
    }
}
