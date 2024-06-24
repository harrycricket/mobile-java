package com.example.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);

        RecyclerView rvUser = findViewById(R.id.rvUser);

        // init data
        userList = new ArrayList<>();
        userList.add(new User("PhuotHVSE160205", "Huynh Van Phuot", "phuothvse160205@fpt.edu.vn"));
        userList.add(new User("ThongNV",  "Nguyen Van Thong", "thongnv@gmail.com"));
        userList.add(new User("TienPH","Pham Hoang Tien", "tienph@gmail.com"));
        userList.add(new User("ThienCN", "Cao Nhat Thien", "thiencn@gmail.com"));
        userList.add(new User("NguyenTT","Tran Thanh Nguyen","nguyentt@gmail.com"));

        UserAdapter adapter = new UserAdapter(userList);
        rvUser.setAdapter (adapter);
        rvUser.setLayoutManager (new LinearLayoutManager(this));
    }
}