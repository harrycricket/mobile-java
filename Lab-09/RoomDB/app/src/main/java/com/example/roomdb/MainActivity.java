package com.example.roomdb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.roomdb.adapter.PersonAdapter;
import com.example.roomdb.data.GlobalData;
import com.example.roomdb.db.AppDatabase;
import com.example.roomdb.executors.AppExecutors;
import com.example.roomdb.model.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    private PersonAdapter adapter;
    private RecyclerView rvPersons;
    private List<Person> list;
    private Button btnNew;
    private final String REQUIRE = "Require";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPersons = (RecyclerView) findViewById(R.id.rvPersons);
        btnNew = (Button) findViewById(R.id.btnNew);

        // init data
        init();
    }

    private void init() {
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "person-database").build();
        GlobalData.getInstance().setAppDatabase(appDatabase);

        AppExecutors.getInstance().diskIO().execute(() -> {
            list = appDatabase.personDAO().getAll();
            runOnUiThread(() -> {
                adapter = new PersonAdapter(MainActivity.this, list);
                rvPersons.setAdapter(adapter);
                rvPersons.setLayoutManager (new LinearLayoutManager(MainActivity.this));
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(MainActivity.this, adapter, appDatabase, list));
                itemTouchHelper.attachToRecyclerView(rvPersons);
                initEventListeners();
            });
        });
    }

    private void initEventListeners() {
        // event handling
        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SaveActivity.class);
            startActivity(intent);
        });

        adapter.setOnItemClickListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                View currentView = rvPersons.getChildAt(position);
                currentView.setBackgroundColor(Color.parseColor("#CD8484"));
            }
        });
    }
}