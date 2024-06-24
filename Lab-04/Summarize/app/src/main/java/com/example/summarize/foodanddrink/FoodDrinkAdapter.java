package com.example.summarize.foodanddrink;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.summarize.R;

import java.util.List;

public class FoodDrinkAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<FoodDrinkItem> itemList;

    public FoodDrinkAdapter(Context context, int layout, List<FoodDrinkItem> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtDesc = view.findViewById(R.id.txtDesc);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        ImageView ivAvatar = view.findViewById(R.id.ivAvatar);
        TextView txtLabel = view.findViewById(R.id.txtLabel);

        FoodDrinkItem item = itemList.get(index);
        txtName.setText(item.getName());
        txtDesc.setText(item.getDesc());
        txtPrice.setText("" + item.getPrice()/1000 + "." +  String.format("%03d", item.getPrice()%1000) + "đ");
        ivAvatar.setImageResource(item.getAvtImage());

        if (Data.getInstance().isSelectedItem(item)) {
            view.setBackgroundColor(Color.parseColor("#bae6fd"));
            txtLabel.setText("Đã chọn");
            txtLabel.setBackgroundColor(Color.parseColor("#4ade80"));
        }
        else {
            view.setBackgroundColor(Color.parseColor("#f1f5f9"));
            txtLabel.setText("Chưa chọn");
            txtLabel.setBackgroundColor(Color.parseColor("#94a3b8"));
        }

        return view;
    }
}
