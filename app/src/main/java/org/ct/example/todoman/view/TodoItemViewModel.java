package org.ct.example.todoman.view;


import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;

import org.ct.example.todoman.hexagon.TodoItem;

@Mappable( with = TodoItem.class )
public class TodoItemViewModel {
    @Mapped
    private String title;
    @Mapped private String description;
    @Mapped private String dueDate;

    public TodoItemViewModel() {

    }

    public TodoItemViewModel(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean hasEmptyTitle() {
        return title == null || title.isEmpty();
    }

    public boolean hasDateNotSet() {
        return dueDate.equalsIgnoreCase("Date not set");
    }
}
