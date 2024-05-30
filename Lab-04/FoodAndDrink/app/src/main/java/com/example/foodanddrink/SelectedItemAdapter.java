package com.example.foodanddrink;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SelectedItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<FoodDrinkItem> selectedItemList;

    public SelectedItemAdapter(Context context, int layout, List<FoodDrinkItem> selectedItemList) {
        this.context = context;
        this.layout = layout;
        this.selectedItemList = selectedItemList;
    }

    @Override
    public int getCount() {
        return selectedItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return selectedItemList.get(position);
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

        FoodDrinkItem item = selectedItemList.get(index);
        txtName.setText(item.getName());
        txtDesc.setText(item.getDesc());
        double priceInDollars = item.getPrice() / 1000.0;
        String formattedPrice = String.format("%.3f", priceInDollars);
        txtPrice.setText(formattedPrice + "đ");
        ivAvatar.setImageResource(item.getAvtImage());

        return view;
    }
}
