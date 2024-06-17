package com.example.sqlite;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database db;
    ListView jobList;
    ImageView addJob;
    ArrayList<Job> jobArrayList;
    JobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        jobList = findViewById(R.id.jobList);
        addJob = (ImageView) findViewById(R.id.imageviewAdd);
        jobArrayList = new ArrayList<>();
        adapter = new JobAdapter(this, R.layout.activity_job, jobArrayList);
        jobList.setAdapter(adapter);

        db = new Database(this, "PRMLab.sqlite", null, 1);
        db.QueryData("Create table if not exists Job(id Integer Primary Key Autoincrement," +
                "name nvarchar(200))");
        // Insert data
        db.QueryData("Insert into Job values(null, 'Build UI for shop owner')");
        db.QueryData("Insert into Job values(null, 'Create dashboard')");
        GetDataJob();

        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddNewJob();
            }
        });
    }

    private void GetDataJob() {
        Cursor dataJob = db.GetData("Select * from Job");
        jobArrayList.clear();
        while (dataJob.moveToNext()) {
            String ten = dataJob.getString(1);
            int id = dataJob.getInt(0);
            jobArrayList.add(new Job(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogAddNewJob() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_add);

        EditText editName = dialog.findViewById(R.id.edtName);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobName = editName.getText().toString();

                if (jobName.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc !", Toast.LENGTH_SHORT).show();
                } else {
                    db.QueryData("Insert into Job values(null, '" + jobName + "')");
                    Toast.makeText(MainActivity.this, "Thêm công việc mới thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataJob();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogUpdateJob(String name, int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_edit);
        EditText editName = dialog.findViewById(R.id.edtName);
        Button btnEdit = dialog.findViewById(R.id.btnEdit);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        editName.setText(name);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                String newName = editName.getText().toString().trim();
                if (newName.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.QueryData("UPDATE Job SET name = '" + newName + "' WHERE id = '" + id + "'");
                    Toast.makeText(MainActivity.this, "Cập nhật công việc thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataJob();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DialogDeleteJob(String name, int id) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Bạn có muốn xóa công việc '" + name + "' không ?");
        dialogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.QueryData("DELETE FROM Job WHERE id = '" + id + "' ");
                Toast.makeText(MainActivity.this, "Xóa công việc thành công " + name + "!", Toast.LENGTH_SHORT).show();
                GetDataJob();
            }
        });
        dialogDelete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialogDelete.show();
    }

}