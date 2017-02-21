package org.ct.example.todoman.hexagon;


public interface DataModelPort {
    void setBusinessCase(MainBusinessCase businessCase);
    void setBusinessCase(CreateBusinessCase businessCase);
    void getData();
    void createItem(TodoItem item);
}
