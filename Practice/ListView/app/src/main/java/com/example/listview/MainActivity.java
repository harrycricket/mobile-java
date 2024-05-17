package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listCourse;
    ArrayList<String> arrayListCourse;
    Button addBtn;
    TextInputEditText courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listCourse = findViewById(R.id.listCourse);
        arrayListCourse = new ArrayList<>();
        arrayListCourse.add("NextJS");
        arrayListCourse.add("NestJS");
        arrayListCourse.add("NuxtJS");
        arrayListCourse.add("NodeJS");
        arrayListCourse.add("ReactJS");
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this, android.R.layout.simple_list_item_1,
                arrayListCourse
        );
        listCourse.setAdapter(adapter);

        listCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arrayListCourse.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        listCourse.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayListCourse.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        addBtn = findViewById(R.id.add);
        courseName = findViewById(R.id.courseName);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = courseName.getText().toString();
                arrayListCourse.add(name);
                listCourse.setAdapter(adapter);
            }
        });

    }
}