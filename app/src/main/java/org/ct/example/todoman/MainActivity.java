package org.ct.example.todoman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView todoItemsRv;
    TodoItemListAdapter todoItemsAdapter;
    TextView listEmpty;

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<TodoItem> todoItems = TodoItem.listAll(TodoItem.class);
        if ( todoItems.size() > 0 ) {
            listEmpty.setVisibility(View.GONE);
            if (todoItemsAdapter != null) {
                todoItemsAdapter.changeList(todoItems);
                todoItemsAdapter.notifyDataSetChanged();
            }
            else {
                todoItemsAdapter = new TodoItemListAdapter(todoItems);
                todoItemsRv.setAdapter(todoItemsAdapter);
            }
        }
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
}
