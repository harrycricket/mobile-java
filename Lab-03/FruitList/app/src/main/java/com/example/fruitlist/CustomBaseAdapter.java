package com.example.fruitlist;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String fruitList[];
    String fruitDescriptionList[];
    int fruitImageList[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context context, String [] fruitList, String [] fruitDescriptionList, int [] fruitImageList) {
        this.context = context;
        this.fruitList = fruitList;
        this.fruitDescriptionList = fruitDescriptionList;
        this.fruitImageList = fruitImageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return fruitList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_fruit_item, null);
        TextView title = (TextView) convertView.findViewById(R.id.fruitName);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        ImageView fruitImage = convertView.findViewById(R.id.image);
        title.setText(fruitList[position]);
        description.setText(fruitDescriptionList[position]);
        fruitImage.setImageResource(fruitImageList[position]);
        return convertView;
    }
}
