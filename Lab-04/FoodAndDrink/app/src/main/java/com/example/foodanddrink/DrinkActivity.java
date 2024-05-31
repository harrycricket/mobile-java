package com.example.foodanddrink;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {

    private Button btnSelectDone;
    private ListView lvDrinks;
    private List<FoodDrinkItem> itemList;
    private FoodDrinkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drink);

        lvDrinks = findViewById(R.id.lvDrinks);
        btnSelectDone = findViewById(R.id.btnSelectDone);

        initData();

        adapter = new FoodDrinkAdapter(this, R.layout.activity_item, itemList);
        lvDrinks.setAdapter(adapter);

        lvDrinks.setOnItemClickListener((parent, view, position, id) -> {
            FoodDrinkItem item = itemList.get(position);
            TextView txtLabel = (TextView) view.findViewById(R.id.txtLabel);
            if (!Data.getInstance().isSelectedItem(item)) {
                Data.getInstance().getSelectedDrinks().add(item);
                view.setBackgroundColor(Color.parseColor("#bae6fd"));
                txtLabel.setText("Đã chọn");
                txtLabel.setBackgroundColor(Color.parseColor("#4ade80"));
                Toast.makeText(this, "Đã thêm " + item.getName() + " vào danh sách!", Toast.LENGTH_SHORT).show();
            } else {
                Data.getInstance().removeItem(item);
                view.setBackgroundColor(Color.parseColor("#f1f5f9"));
                txtLabel.setText("Chưa chọn");
                txtLabel.setBackgroundColor(Color.parseColor("#94a3b8"));
                Toast.makeText(this, "Đã xóa " + item.getName() + " ra khỏi danh sách!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSelectDone.setOnClickListener(v -> {
            Intent intent = new Intent(DrinkActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initData() {
        itemList = new ArrayList<>();
        itemList.add(new FoodDrinkItem("Pepsi", "Pepsi một đồ uống giải khát có gas", 12000, R.drawable.pepsi));
        itemList.add(new FoodDrinkItem("Heineken", "Độ cồn 5%", 25000, R.drawable.heineken));
        itemList.add(new FoodDrinkItem("Tiger", "Độ cồn 4.6%", 20000, R.drawable.tiger));
        itemList.add(new FoodDrinkItem("Sài Gòn đỏ", "Sự pha trộn tinh tế giữa hương vị độc đáo và nồng độ cân đối", 15000, R.drawable.bia));
    }
}