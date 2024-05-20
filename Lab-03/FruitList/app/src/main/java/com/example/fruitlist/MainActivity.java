package com.example.fruitlist;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String fruitList[] = {"Apple", "Banana", "Blueberry", "Corn", "Grapes", "Kiwi", "Strawberry", "Mango", "Guava", "Peach"};
    String fruitDescriptionList[] = {
            "A sweet and crunchy fruit, rich in vitamins and fiber.",
            "A creamy and potassium-packed fruit, perfect for on-the-go energy.",
            "Tiny, tart berries loaded with antioxidants.",
            "A versatile vegetable with a sweet, starchy flavor.",
            "Juicy clusters of sweetness, available in various colors and flavors.",
            "A bright green fruit with a tangy taste and black seeds.",
            "Bright red berries with a sweet and juicy flavor.",
            "A tropical fruit with a sweet, juicy flesh and a vibrant orange color.",
            "A tropical fruit with a sweet and tangy flavor and edible seeds.",
            "A fuzzy-skinned fruit with a sweet, juicy flesh."
    };
    int fruitImageList[] = {R.drawable.apple, R.drawable.banana, R.drawable.blueberry, R.drawable.corn, R.drawable.grapes, R.drawable.kiwi, R.drawable.strawberry, R.drawable.mango, R.drawable.guava, R.drawable.peach};

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listFruit);

        CustomBaseAdapter adapter = new CustomBaseAdapter(getApplicationContext(), fruitList, fruitDescriptionList, fruitImageList);
        listView.setAdapter(adapter);
    }
}