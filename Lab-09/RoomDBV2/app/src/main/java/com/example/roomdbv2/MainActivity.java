package com.example.roomdbv2;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.roomdbv2.adapter.ModuleAdapter;
import com.example.roomdbv2.db.AppDatabase;
import com.example.roomdbv2.executors.AppExecutors;
import com.example.roomdbv2.model.Module;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    RecyclerView rvModules;
    List<Module> moduleList;
    ModuleAdapter adapter;
    EditText etTitle;
    EditText etLabel;
    EditText etDesc;
    EditText etAvt;
    ImageView ivPreAvt;
    Button btnAdd;
    Button btnUpdate;
    Button btnRemove;
    Button btnCancel;
    int currentPosition = -1;
    View currentView = null;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mapping
        rvModules = (RecyclerView) findViewById(R.id.rvModules);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etLabel = (EditText) findViewById(R.id.etLabel);
        etDesc = (EditText) findViewById(R.id.etDesc);
        etAvt = (EditText) findViewById(R.id.etAvt);
        ivPreAvt = (ImageView) findViewById(R.id.ivPreAvt);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnCancel = (Button) findViewById(R.id.btnCancel);



        // init data
        reset();
        init();
    }

    private void init() {
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "module-database").build();
        AppExecutors.getInstance().diskIO().execute(() -> {
            appDatabase.moduleDAO().insert(new Module("Listview trong Android",
                    "Listview trong Android là một thành phần dùng nhóm nhiều mục (item) với nhau Listview trong Android là một thành phần dùng nhóm nhiều mục (item) với nhau Listview trong Android là một thành phần dùng nhóm nhiều mục (item) với nhau",
                    "Android",
                    "https://play-lh.googleusercontent.com/y_-anVKl3ID25Je02J1dseqlAm41N8pwI-Gad7aDxPIPss3d7hUYF8c08SNCtwSPW5g"));
            appDatabase.moduleDAO().insert(new Module("Xử lý sự kiện trong IOS",
                    "Xử lý sự kiện trong IOS. Sau khi các bạn đã biết cách thiết kế giao diện cho các ứng dụng có tính năng nhất định Sau khi các bạn đã biết cách thiết kế giao diện cho các ứng dụng có tính năng nhất định ",
                    "IOS",
                    "https://genk.mediacdn.vn/139269124445442048/2024/4/3/apple-eu-ios-changes-17121325313631318436472.jpg"));
            moduleList = appDatabase.moduleDAO().getAll();
            runOnUiThread(() -> {
                adapter = new ModuleAdapter(moduleList);
                rvModules.setAdapter(adapter);
                rvModules.setLayoutManager (new LinearLayoutManager(MainActivity.this));
                initEventListeners();
            });
        });
    }

    private void initEventListeners() {
        // event handling
        adapter.setOnItemClickListener(new ModuleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Reset background color of previously selected item
                if (currentPosition >= 0 && currentPosition < moduleList.size()) {
                    Module previous = moduleList.get(currentPosition);
                    previous.setSelected(false);
                }
                if (position >= 0 && position < moduleList.size()) {
                    // Update background color of the clicked item
                    Module current = moduleList.get(position);
                    current.setSelected(true);
                    currentPosition = position;

                    // Notify adapter to update UI
                    adapter.notifyDataSetChanged();

                    // Update UI with clicked item's details
                    etTitle.setText(current.getTitle());
                    etLabel.setText(current.getLabel());
                    etDesc.setText(current.getDesc());
                    etAvt.setText(current.getImgSrc());

                    // Enable/disable buttons
                    btnAdd.setEnabled(false);
                    btnUpdate.setEnabled(true);
                    btnRemove.setEnabled(true);
                }
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
                        try {
                            Glide.with(MainActivity.this)
                                    .load(newText)
                                    .placeholder(R.drawable.no_img)
                                    .error(R.drawable.no_img)
                                    .into(ivPreAvt);
                        }
                        catch (Exception ex) {
                            ivPreAvt.setImageResource(R.drawable.no_img);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                }
        );

        btnAdd.setOnClickListener(view -> {
            if (checkInput() == false) {
                return;
            }

            String title = etLabel.getText().toString();
            String label = etLabel.getText().toString();
            String desc = etDesc.getText().toString();
            String avt = etAvt.getText().toString();

            AppExecutors.getInstance().diskIO().execute(() -> {
                appDatabase.moduleDAO().insert(new Module(title, desc, label, avt));
                loadModulesFromDatabase(); // Update UI after database operation
            });

            reset();
            Toast.makeText(MainActivity.this, "Added successfully!", Toast.LENGTH_SHORT).show();
        });
        btnUpdate.setOnClickListener(view -> {
            if (checkInput() == false) {
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

            AppExecutors.getInstance().diskIO().execute(() -> {
                appDatabase.moduleDAO().update(current);
                runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
                });
            });
        });
        btnRemove.setOnClickListener(view -> {
            if (currentPosition < 0 || currentPosition >= moduleList.size()) {
                Toast.makeText(MainActivity.this, "You haven't selected any item to remove!", Toast.LENGTH_SHORT).show();
                return;
            }
            Module current = moduleList.get(currentPosition);
            AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
            dialogXoa.setMessage("Bạn có muốn xóa " + current.getTitle()  + " không ? ");
            dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppExecutors.getInstance().diskIO().execute(() -> {
                        appDatabase.moduleDAO().delete(current);
                        runOnUiThread(() -> {
                            moduleList.remove(currentPosition);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Removed successfully!", Toast.LENGTH_SHORT).show();
                            reset();
                        });
                    });
                }
            });
            dialogXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dialogXoa.show();
        });
        btnCancel.setOnClickListener(view -> {
            reset();
        });
    }
    private boolean checkInput() {
        if (TextUtils.isEmpty(etTitle.getText().toString())) {
            etTitle.setError(REQUIRE);
            return  false;
        }
        if (TextUtils.isEmpty(etDesc.getText().toString())) {
            etDesc.setError(REQUIRE);
            return  false;
        }
        if (TextUtils.isEmpty(etLabel.getText().toString())) {
            etLabel.setError(REQUIRE);
            return  false;
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

    private void loadModulesFromDatabase() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Module> modules = appDatabase.moduleDAO().getAll();
            runOnUiThread(() -> {
                moduleList.clear();
                moduleList.addAll(modules);
                adapter.notifyDataSetChanged();
            });
        });
    }
}