package com.example.todo_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText taskName;
    Button addTask;
    Adapter adapter;
    RecyclerView recyclerView;
    List<DataModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskName = findViewById(R.id.task_name);
        addTask = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recycler_view);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        list = databaseHelper.getAlldata();

        adapter = new Adapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        addTask.setOnClickListener(view -> {
            String name = taskName.getText().toString();
            DataModel dataModel = new DataModel(name, false);
            databaseHelper.addOne(dataModel);
            List<DataModel> everyone = databaseHelper.getAlldata();
//            list = everyone;
            adapter.list = everyone;
            adapter.notifyDataSetChanged();
        });

    }
}