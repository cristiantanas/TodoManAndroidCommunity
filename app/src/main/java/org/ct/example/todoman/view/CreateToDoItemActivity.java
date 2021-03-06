package org.ct.example.todoman.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.ct.example.todoman.DatePicker;
import org.ct.example.todoman.R;

public class CreateToDoItemActivity extends AppCompatActivity
        implements DatePicker.OnUserSetDate {

    ProgressBar progressBar;
    EditText todoTitle;
    EditText todoDescription;
    TextView dueDate;

    Button setDueDate;

    Button save;
    Button cancel;

    CreateViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do_item);

        viewAdapter = new CreateViewAdapter(this);

        progressBar = (ProgressBar) findViewById(R.id.progress);

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
                viewAdapter.createItem(
                        todoTitle.getText().toString(),
                        todoDescription.getText().toString(),
                        dueDate.getText().toString()
                );
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDateSet(int year, int month, int dayOfMonth) {
        dueDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year );
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void displayRequiredTitleError() {
        Toast.makeText(this, "Item title is required!", Toast.LENGTH_LONG)
                .show();
    }

    public void displayInvalidDateError() {
        Toast.makeText(this, "Due date is before today!", Toast.LENGTH_LONG)
                .show();
    }

    public void onItemCreated() {
        finish();
    }

    public void onUnexpectedError() {
        Toast.makeText(this, "An unexpected error has occurred!", Toast.LENGTH_LONG)
                .show();
    }
}
