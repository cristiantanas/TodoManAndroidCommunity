package org.ct.example.todoman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class TodoItemListAdapter extends RecyclerView.Adapter<TodoItemListAdapter.ViewHolder> {

    private List<TodoItem> todoItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView todoTitle;
        public TextView todoDescription;
        public TextView todoDueDate;

        public ViewHolder(View itemView) {
            super(itemView);
            todoTitle = (TextView) itemView.findViewById(R.id.title);
            todoDescription = (TextView) itemView.findViewById(R.id.description);
            todoDueDate = (TextView) itemView.findViewById(R.id.dueDate);
        }
    }

    public TodoItemListAdapter(List<TodoItem> items) {
        this.todoItems = items;
    }

    public void changeList(List<TodoItem> items) {
        this.todoItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(todoItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodoItem todoItem = todoItems.get(position);
        holder.todoTitle.setText(todoItem.getTitle());
        holder.todoDescription.setText(todoItem.getDescription());
        holder.todoDueDate.setText(todoItem.getDueDate());
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }
}
