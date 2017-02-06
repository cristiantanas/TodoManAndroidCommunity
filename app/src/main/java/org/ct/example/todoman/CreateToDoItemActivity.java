package org.ct.example.todoman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateToDoItemActivity extends AppCompatActivity
        implements DatePicker.OnUserSetDate {

    EditText todoTitle;
    EditText todoDescription;
    TextView dueDate;

    Button setDueDate;

    Button save;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do_item);

        todoTitle = (EditText) findViewById(R.id.todoTitle);
        todoDescription = (EditText) findViewById(R.id.todoDescription);
        dueDate = (TextView) findViewById(R.id.todoDate);

        setDueDate = (Button) findViewById(R.id.todoSetDate);

        save = (Button) findViewById(R.id.todoSave);
        cancel = (Button) findViewById(R.id.todoCancel);

        setDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = new DatePicker();
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoItem todoItem = new TodoItem(
                        todoTitle.getText().toString(),
                        todoDescription.getText().toString(),
                        dueDate.getText().toString()
                );
                todoItem.save();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onDateSet(int year, int month, int dayOfMonth) {
        dueDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year );
    }
}
