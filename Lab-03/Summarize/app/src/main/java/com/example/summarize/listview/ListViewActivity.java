package com.example.summarize.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.summarize.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class ListViewActivity extends AppCompatActivity {

    private int selectedCourseIndex = -1;
    ListView listCourse;
    ArrayList<String> arrayListCourse;
    Button addBtn;
    Button updateBtn;
    TextInputEditText courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_activity);

        listCourse = findViewById(R.id.listCourse);
        addBtn = findViewById(R.id.add);
        updateBtn = findViewById(R.id.update);
        courseName = findViewById(R.id.courseName);

        arrayListCourse = new ArrayList<>();
        arrayListCourse.add("NextJS");
        arrayListCourse.add("NestJS");
        arrayListCourse.add("NuxtJS");
        arrayListCourse.add("NodeJS");
        arrayListCourse.add("ReactJS");
        ArrayAdapter adapter = new ArrayAdapter(
                ListViewActivity.this, android.R.layout.simple_list_item_1,
                arrayListCourse
        );
        listCourse.setAdapter(adapter);

        listCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCourseIndex = position;
                courseName.setText(arrayListCourse.get(position));
            }
        });

        listCourse.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String courseNameRemoved = arrayListCourse.get(position);
                arrayListCourse.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(ListViewActivity.this, "Remove " + courseNameRemoved + " successfully", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Objects.requireNonNull(courseName.getText()).toString();
                if (name.isEmpty()) {
                    Toast.makeText(ListViewActivity.this, "Please input course name", Toast.LENGTH_SHORT).show();
                } else {
                    arrayListCourse.add(name);
                    listCourse.setAdapter(adapter);
                    courseName.setText("");
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedName = Objects.requireNonNull(courseName.getText()).toString();
                // Check if a course is selected (based on your selectedCourseIndex approach)
                if (selectedCourseIndex != -1) {
                    arrayListCourse.set(selectedCourseIndex, updatedName);
                    adapter.notifyDataSetChanged();
                    courseName.setText("");
                    Toast.makeText(ListViewActivity.this, "Course updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListViewActivity.this, "Please select a course to update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}