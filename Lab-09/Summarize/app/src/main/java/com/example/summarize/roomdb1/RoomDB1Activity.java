package com.example.summarize.roomdb1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.summarize.R;
import com.example.summarize.roomdb1.adapter.PersonAdapter;
import com.example.summarize.roomdb1.data.GlobalData;
import com.example.summarize.roomdb1.db.AppDatabase;
import com.example.summarize.roomdb1.executors.AppExecutors;
import com.example.summarize.roomdb1.model.Person;

import java.util.List;

public class RoomDB1Activity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private PersonAdapter adapter;
    private RecyclerView rvPersons;
    private List<Person> list;
    private Button btnNew;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_db1);

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
                adapter = new PersonAdapter(RoomDB1Activity.this, list);
                rvPersons.setAdapter(adapter);
                rvPersons.setLayoutManager (new LinearLayoutManager(RoomDB1Activity.this));
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(RoomDB1Activity.this, adapter, appDatabase, list));
                itemTouchHelper.attachToRecyclerView(rvPersons);
                initEventListeners();
            });
        });
    }

    private void initEventListeners() {
        // event handling
        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(RoomDB1Activity.this, SaveActivity.class);
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