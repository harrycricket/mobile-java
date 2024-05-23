package com.example.fruitlist;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<FruitItem> fruitList;
    private ListView listViewFruits;
    private CustomBaseAdapter adapter;
    private EditText name, description, url;
    private ImageView fruitUrl;
    private Button btnAdd, btnUpdate, btnDelete;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initialView();
        initialData();

        adapter = new CustomBaseAdapter(this, R.layout.activity_fruit_item, fruitList);
        listViewFruits.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> addFruit());
        btnUpdate.setOnClickListener(v -> updateFruit());
        btnDelete.setOnClickListener(v -> deleteFruit());

        listViewFruits.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            FruitItem selectedFruit = fruitList.get(position);
            name.setText(selectedFruit.getName());
            description.setText(selectedFruit.getDescription());
            url.setText(selectedFruit.getFruitUrl());
            Glide.with(this)
                    .load(selectedFruit.getFruitUrl())
                    .apply(new RequestOptions().error(R.drawable.no_image))
                    .into(fruitUrl);
        });

        url.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String imageUrl = s.toString();
                if (!imageUrl.isEmpty()) {
                    Glide.with(MainActivity.this)
                            .load(imageUrl)
                            .apply(new RequestOptions().error(R.drawable.no_image)) // Set error placeholder
                            .into(fruitUrl);
                } else {
                    fruitUrl.setImageResource(R.drawable.no_image); // Set a default or error image
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initialView() {
        listViewFruits = findViewById(R.id.listViewFruits);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        url = findViewById(R.id.url);
        fruitUrl = findViewById(R.id.imageFruit);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
    }


    private void initialData() {
        fruitList = new ArrayList<>();
        fruitList.add(new FruitItem("Apple", "A sweet and crunchy fruit, rich in vitamins and fiber.",
                "https://pngfre.com/wp-content/uploads/apple-poster.png"));
        fruitList.add(new FruitItem("Banana", "A creamy and potassium-packed fruit, perfect for on-the-go energy.",
                "https://i.pinimg.com/originals/b8/39/3c/b8393ccd0f271772cc7d4796857637a9.jpg"));
        fruitList.add(new FruitItem("Blueberry", "Tiny, tart berries loaded with antioxidants.",
                "https://w7.pngwing.com/pngs/395/812/png-transparent-blueberry-blueberry-fruit-food.png"));
        fruitList.add(new FruitItem("Corn", "A versatile vegetable with a sweet, starchy flavor.",
                "https://png.pngtree.com/png-vector/20210518/ourmid/pngtree-corn-fresh-fruit-snacks-png-image_3302930.jpg"));
        fruitList.add(new FruitItem("Grapes", "Juicy clusters of sweetness, available in various colors and flavors.",
                "https://w7.pngwing.com/pngs/44/84/png-transparent-purple-grape-purple-grape-grape-leaves.png"));
        fruitList.add(new FruitItem("Kiwi", "A bright green fruit with a tangy taste and black seeds.",
                "https://images.rawpixel.com/image_png_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L21vdGFybzdfM2RfaWxsdXN0cmF0aW9uX2tpd2lzX2lzb2xhdGVkX29uX3doaXRlX2RjMzlmZDJjLTU0MTEtNDNlNi05MzcwLTE1MTZmMzFlNmMwNy5wbmc.png"));
        fruitList.add(new FruitItem("Strawberry", "Bright red berries with a sweet and juicy flavor.",
                "https://w7.pngwing.com/pngs/983/762/png-transparent-strawberry-fruit-strawberry-strawberry-clipart-thumbnail.png"));
        fruitList.add(new FruitItem("Mango", "A tropical fruit with a sweet, juicy flesh and a vibrant orange color.",
                "https://w7.pngwing.com/pngs/384/888/png-transparent-mango-mango-natural-foods-food-fruit.png"));
        fruitList.add(new FruitItem("Guava", "A tropical fruit with a sweet and tangy flavor and edible seeds.",
                "https://pngimg.com/d/guava_PNG15.png"));
        fruitList.add(new FruitItem("Peach", "A fuzzy-skinned fruit with a sweet, juicy flesh.",
                "https://w7.pngwing.com/pngs/1/890/png-transparent-peach-fruit-peach-miscellaneous-natural-foods-food-thumbnail.png"));
    }

    private void addFruit() {
        String fruitName = name.getText().toString();
        String fruitDes = description.getText().toString();
        String fruitUrl = url.getText().toString();

        if (fruitName.isEmpty() || fruitDes.isEmpty() || fruitUrl.isEmpty()) {
            Toast.makeText(this, "Please input all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedIndex != -1) {
            FruitItem selectedFruit = fruitList.get(selectedIndex);
            if (fruitName.equals(selectedFruit.getName()) &&
                    fruitDes.equals(selectedFruit.getDescription()) &&
                    fruitUrl.equals(selectedFruit.getFruitUrl())) {
                Toast.makeText(this, "Duplicate fruit!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        FruitItem newFruit = new FruitItem(fruitName, fruitDes, fruitUrl);
        fruitList.add(0, newFruit);
        adapter.notifyDataSetChanged();
        clearFields();
    }

    private void updateFruit() {
        if (selectedIndex == -1) {
            Toast.makeText(this, "Please select a fruit to update", Toast.LENGTH_SHORT).show();
            return;
        }

        String fruitName = name.getText().toString();
        String fruitDes = description.getText().toString();
        String fruitUrl = url.getText().toString();

        if (fruitName.isEmpty() || fruitDes.isEmpty() || fruitUrl.isEmpty()) {
            Toast.makeText(this, "Please input all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        FruitItem updateFruit = fruitList.get(selectedIndex);
        updateFruit.setName(fruitName);
        updateFruit.setDescription(fruitDes);
        updateFruit.setFruitUrl(fruitUrl);

        adapter.notifyDataSetChanged();
        clearFields();
        selectedIndex = -1;
    }

    private void deleteFruit() {
        if (selectedIndex == -1) {
            Toast.makeText(this, "Please select a fruit before delete", Toast.LENGTH_SHORT).show();
            return;
        }

        fruitList.remove(selectedIndex);
        adapter.notifyDataSetChanged();
        clearFields();
        selectedIndex = -1;
    }

    private void clearFields() {
        name.setText("");
        description.setText("");
        url.setText("");
    }

}