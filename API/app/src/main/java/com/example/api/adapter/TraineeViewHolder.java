package com.example.api.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.R;
import com.example.api.model.Trainee;

import java.util.List;

public class TraineeViewHolder extends RecyclerView.ViewHolder{
    Context context;
    Trainee data;
    TextView tvName, tvPhone, tvEmail, tvGender;
    ImageView ivEdit, ivDelete;
    List<Trainee> list;
    public TraineeViewHolder(Context context, @NonNull View itemView, final TraineeAdapter.OnItemClickListener listener) {
        super(itemView);
        this.context = context;
        // Initialize your views
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvPhone = (TextView) itemView.findViewById(R.id.tvPhone);
        tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        tvGender = (TextView) itemView.findViewById(R.id.tvGender);
        ivEdit = (ImageView) itemView.findViewById(R.id.ivEdit);
        ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);

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
