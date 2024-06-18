package com.example.summarize.foodanddrink;

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

import com.example.summarize.R;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private Button btnSelectDone;
    private ListView lvFoods;
    private List<FoodDrinkItem> itemList;
    private FoodDrinkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food);

        lvFoods = findViewById(R.id.lvFoods);
        btnSelectDone = findViewById(R.id.btnSelectDone);

        initData();

        adapter = new FoodDrinkAdapter(this, R.layout.activity_item, itemList);
        lvFoods.setAdapter(adapter);

        lvFoods.setOnItemClickListener((parent, view, position, id) -> {
            FoodDrinkItem item = itemList.get(position);
            TextView txtLabel = view.findViewById(R.id.txtLabel);
            if (!Data.getInstance().isSelectedItem(item)) {
                Data.getInstance().getSelectedFoods().add(item);
                view.setBackgroundColor(Color.parseColor("#bae6fd"));
                txtLabel.setText("Đã chọn");
                txtLabel.setBackgroundColor(Color.parseColor("#4ade80"));
                Toast.makeText(this, "Đã thêm " + item.getName() + " vào danh sách!", Toast.LENGTH_SHORT).show();
            } else {
                Data.getInstance().getSelectedFoods().remove(item);
                view.setBackgroundColor(Color.parseColor("#f1f5f9"));
                txtLabel.setText("Chưa chọn");
                txtLabel.setBackgroundColor(Color.parseColor("#94a3b8"));
                Toast.makeText(this, "Đã bỏ " + item.getName() + " ra khỏi danh sách!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSelectDone.setOnClickListener(v -> {
            Intent intent = new Intent(FoodActivity.this, FoodAndDrinkActivity.class);
            startActivity(intent);
        });
    }

    private void initData() {
        itemList = new ArrayList<>();
        itemList.add(new FoodDrinkItem("Phở Hà Nội", "Món ăn nổi tiếng của vùng đất Hà thành", 40000, R.drawable.pho));
        itemList.add(new FoodDrinkItem("Bún bò Huế", "Đặc sản xứ cố đô", 35000, R.drawable.bun));
        itemList.add(new FoodDrinkItem("Mỳ Quảng", "Đặc sản xuất xứ từ Quảng Nam", 20000, R.drawable.my));
        itemList.add(new FoodDrinkItem("Hủ tiếu Sài Gòn", "Món ăn ruột của dân Sài thành", 25000, R.drawable.hutieu));
    }
}