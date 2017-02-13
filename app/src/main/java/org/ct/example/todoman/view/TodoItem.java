package org.ct.example.todoman.view;

import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;

import org.ct.example.todoman.model.TodoItemRecord;

@Mappable( with = TodoItemRecord.class )
public class TodoItem {
    @Mapped private String title;
    @Mapped private String description;
    @Mapped private String dueDate;

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
}
