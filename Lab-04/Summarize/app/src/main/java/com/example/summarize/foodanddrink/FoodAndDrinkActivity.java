package com.example.summarize.foodanddrink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.summarize.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAndDrinkActivity extends AppCompatActivity {

    private Button btnSelectFood;
    private Button btnSelectDrink;
    private Button btnCancel;
    private TextView txtSum;
    private TextView txtInform;
    private ListView lvSelectedItems;
    private List<FoodDrinkItem> selectedItems;
    private SelectedItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_and_drink);

        btnSelectFood = findViewById(R.id.btnSelectFood);
        btnSelectDrink = findViewById(R.id.btnSelectDrink);
        btnCancel = findViewById(R.id.btnCancel);
        lvSelectedItems = findViewById(R.id.lvSelectedItems);
        txtSum = findViewById(R.id.txtSum);
        txtInform = findViewById(R.id.txtInform);

        initData();

        adapter = new SelectedItemAdapter(this, R.layout.activity_selected_item, selectedItems);
        lvSelectedItems.setAdapter(adapter);
        int sum = 0;
        for (FoodDrinkItem item : selectedItems) {
            sum += item.getPrice();
        }
        txtSum.setText("Tổng: " + (sum > 0 ? sum/1000 + "." + String.format("%03d", sum%1000) + "đ" : "0đ"));

        txtInform.setText(!selectedItems.isEmpty() ? "Đã chọn " + selectedItems.size() + " thức ăn và đồ uống" : "Chưa có đồ ăn/thức uống nào được chọn");

        // event handling
        btnSelectFood.setOnClickListener(v -> {
            Intent intent = new Intent(FoodAndDrinkActivity.this, FoodActivity.class);
            startActivity(intent);
        });

        btnSelectDrink.setOnClickListener(v -> {
            Intent intent = new Intent(FoodAndDrinkActivity.this, DrinkActivity.class);
            startActivity(intent);
        });

        btnCancel.setOnClickListener(v -> {
            Data.getInstance().clear();
            selectedItems.clear();
            txtSum.setText("Tổng: 0đ");
            txtInform.setText("Chưa có thức ăn/đồ uống nào được chọn");
            adapter.notifyDataSetChanged();
        });
    }

    private void initData() {
        selectedItems = new ArrayList<>();
        selectedItems.addAll(Data.getInstance().getSelectedFoods());
        selectedItems.addAll(Data.getInstance().getSelectedDrinks());
    }
}