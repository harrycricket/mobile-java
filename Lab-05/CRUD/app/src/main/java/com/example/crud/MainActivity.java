package com.example.crud;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvModules;
    List<Module> moduleList;
    ModuleAdapter adapter;
    EditText etTitle, etLabel, etDesc, etAvt;
    ImageView ivPreAvt;
    Button btnAdd, btnUpdate, btnRemove, btnCancel;

    int currentPosition = -1;
    View currentView = null;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rvModules = findViewById(R.id.rvModules);
        etTitle = findViewById(R.id.etTitle);
        etLabel = findViewById(R.id.etLabel);
        etDesc = findViewById(R.id.etDesc);
        etAvt = findViewById(R.id.etAvt);
        ivPreAvt = findViewById(R.id.ivPreAvt);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);

        reset();
        initData();

        adapter = new ModuleAdapter(moduleList);
        rvModules.setAdapter(adapter);
        rvModules.setLayoutManager (new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new ModuleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (currentView != null) {
                    currentView.setBackgroundColor(Color.TRANSPARENT);
                }
                Module current = moduleList.get(position);
                Toast.makeText(MainActivity.this, current.getLabel(), Toast.LENGTH_SHORT).show();
                etTitle.setText(current.getTitle());
                etLabel.setText(current.getLabel());
                etDesc.setText(current.getDesc());
                etAvt.setText(current.getImgSrc());
                currentPosition = position;
                currentView = rvModules.getChildAt(position);
                currentView.setBackgroundColor(Color.parseColor("#009688"));

                btnAdd.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnRemove.setEnabled(true);
            }
        });

        etAvt.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        String newText = charSequence.toString();
                        if (!TextUtils.isEmpty(newText)) {
                            Glide.with(MainActivity.this)
                                    .load(newText)
                                    .placeholder(R.drawable.no_img)
                                    .error(R.drawable.no_img)
                                    .into(ivPreAvt);
                        } else {
                            ivPreAvt.setImageResource(android.R.color.transparent);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                }
        );

        btnAdd.setOnClickListener(view -> {
            if (!checkInput()) {
                return;
            }

            String title = etLabel.getText().toString();
            String label = etLabel.getText().toString();
            String desc = etDesc.getText().toString();
            String avt = etAvt.getText().toString();

            moduleList.add(new Module(title, desc, label, avt));
            adapter.notifyDataSetChanged();
            reset();
            Toast.makeText(MainActivity.this, "Added successfully!", Toast.LENGTH_SHORT).show();
        });
        btnUpdate.setOnClickListener(view -> {
            if (!checkInput()) {
                return;
            }
            if (currentPosition < 0 || currentPosition >= moduleList.size()) {
                Toast.makeText(MainActivity.this, "You haven't selected any item to edit and update!", Toast.LENGTH_SHORT).show();
                return;
            }
            Module current = moduleList.get(currentPosition);
            current.setTitle(etTitle.getText().toString());
            current.setLabel(etLabel.getText().toString());
            current.setDesc(etDesc.getText().toString());
            current.setImgSrc(etAvt.getText().toString());

            adapter.notifyDataSetChanged();
            currentView.setBackgroundColor(Color.parseColor("#009688"));
            Toast.makeText(MainActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
            reset();
        });
        btnRemove.setOnClickListener(view -> {
            if (currentPosition < 0 || currentPosition >= moduleList.size()) {
                Toast.makeText(MainActivity.this, "You haven't selected any item to remove!", Toast.LENGTH_SHORT).show();
                return;
            }
            moduleList.remove(currentPosition);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Removed successfully!", Toast.LENGTH_SHORT).show();
            reset();
        });
        btnCancel.setOnClickListener(view -> {
            reset();
        });
    }

    private void initData() {
        moduleList = new ArrayList<>();
        moduleList.add(new Module("Listview trong Android",
                "Android",
                "Listview trong Android là một thành phần dùng nhóm nhiều mục (item) với nhau Listview trong Android là một thành phần dùng nhóm nhiều mục (item) với nhau Listview trong Android là một thành phần dùng nhóm nhiều mục (item) với nhau",
                "https://play-lh.googleusercontent.com/y_-anVKl3ID25Je02J1dseqlAm41N8pwI-Gad7aDxPIPss3d7hUYF8c08SNCtwSPW5g"));
        moduleList.add(new Module("Xử lý sự kiện trong IOS",
                "IOS",
                "Xử lý sự kiện trong IOS. Sau khi các bạn đã biết cách thiết kế giao diện cho các ứng dụng có tính năng nhất định Sau khi các bạn đã biết cách thiết kế giao diện cho các ứng dụng có tính năng nhất định ",
                "https://genk.mediacdn.vn/139269124445442048/2024/4/3/apple-eu-ios-changes-17121325313631318436472.jpg"));
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etTitle.getText().toString())) {
            etTitle.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etDesc.getText().toString())) {
            etDesc.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etLabel.getText().toString())) {
            etLabel.setError(REQUIRE);
            return false;
        }

        return true;
    }

    private void reset() {
        etTitle.setText("");
        etLabel.setText("");
        etDesc.setText("");
        etAvt.setText("");
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnRemove.setEnabled(false);
        if (currentView != null) {
            currentView.setBackgroundColor(Color.TRANSPARENT);
        }
        currentPosition = -1;
        currentView = null;
    }
}