package org.ct.example.todoman.model;

import com.orm.SugarRecord;

public class TodoItemRecord extends SugarRecord {
    private String      title;
    private String      description;

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

    private String        dueDate;

    public TodoItemRecord() { }

    public TodoItemRecord(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}
