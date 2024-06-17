package com.example.roomdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb.EditActivity;
import com.example.roomdb.R;
import com.example.roomdb.data.GlobalData;
import com.example.roomdb.model.Person;

public class PersonViewHolder extends RecyclerView.ViewHolder{
    Context context;
    TextView txtFirstName, txtLastName;
    ImageView ivEdit;
    Person person;

    public PersonViewHolder(Context context, @NonNull View itemView, final PersonAdapter.OnItemClickListener listener) {
        super(itemView);
        this.context = context;
        // Initialize your views
        txtFirstName = (TextView) itemView.findViewById(R.id.txtFirstName);
        txtLastName = (TextView) itemView.findViewById(R.id.txtLastName);
        ivEdit = (ImageView) itemView.findViewById(R.id.ivEdit);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
        ivEdit.setOnClickListener(v -> {
            GlobalData.getInstance().setCurrentPerson(person);
            Intent intent = new Intent(this.context, EditActivity.class);
            this.context.startActivity(intent);
        });
    }
}
