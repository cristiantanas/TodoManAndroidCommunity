package org.ct.example.todoman.hexagon;


import org.ct.example.todoman.view.TodoItem;

public interface CreateDataModelPort {
    void createItem(TodoItem item);
}
