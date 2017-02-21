package org.ct.example.todoman.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.ct.example.todoman.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView todoItemsRv;
    TodoItemListAdapter todoItemsAdapter;

    TextView listEmpty;
    ProgressBar progressBar;

    MainViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createToDoItemIntent = new Intent(MainActivity.this, CreateToDoItemActivity.class);
                startActivity(createToDoItemIntent);
            }
        });

        listEmpty = (TextView) findViewById(R.id.list_empty);

        todoItemsRv = (RecyclerView) findViewById(R.id.todoItems);
        todoItemsRv.setHasFixedSize(true);
        todoItemsRv.setLayoutManager(new LinearLayoutManager(this));
        todoItemsRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        progressBar = (ProgressBar) findViewById(R.id.progress);
        viewAdapter = new MainViewAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewAdapter.getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        todoItemsRv.setVisibility(View.GONE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        todoItemsRv.setVisibility(View.VISIBLE);
    }

    public void setListItems(List<TodoItemViewModel> items) {
        if ( items.size() > 0 ) {
            listEmpty.setVisibility(View.GONE);
            if (todoItemsAdapter != null) {
                todoItemsAdapter.changeList(items);
                todoItemsAdapter.notifyDataSetChanged();
            }
            else {
                todoItemsAdapter = new TodoItemListAdapter(items);
                todoItemsRv.setAdapter(todoItemsAdapter);
            }
        }
        else {
            listEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
