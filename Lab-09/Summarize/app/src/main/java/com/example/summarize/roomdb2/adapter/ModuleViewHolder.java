package com.example.summarize.roomdb2.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.summarize.R;


public class ModuleViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle, txtLabel, txtDesc;
    ImageView ivAvatar;

    public ModuleViewHolder(@NonNull View itemView, final ModuleAdapter.OnItemClickListener listener) {
        super(itemView);

        // Initialize your views
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtLabel = (TextView) itemView.findViewById(R.id.txtLabel);
        txtDesc = (TextView) itemView.findViewById(R.id.txtDesc);
        ivAvatar = (ImageView)  itemView.findViewById(R.id.ivAvatar);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }
}
