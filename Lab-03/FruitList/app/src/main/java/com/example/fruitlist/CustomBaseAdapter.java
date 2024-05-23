package com.example.fruitlist;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<FruitItem> fruitList;

    public CustomBaseAdapter(Context context, int layout, List<FruitItem> fruitList) {
        this.context = context;
        this.layout = layout;
        this.fruitList = fruitList;
    }

    @Override
    public int getCount() {
        return fruitList.size();
    }

    @Override
    public Object getItem(int position) {
        return fruitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(layout, null);
        }

        TextView name = convertView.findViewById(R.id.fruitName);
        TextView description = convertView.findViewById(R.id.description);
        ImageView fruitImage = convertView.findViewById(R.id.image);

        FruitItem fruit = fruitList.get(position);
        name.setText(fruit.getName());
        description.setText(fruit.getDescription());
        Glide.with(context)
                .load(fruit.getFruitUrl())
                .apply(new RequestOptions().error(R.drawable.no_image))
                .into(fruitImage);
        return convertView;
    }
}
