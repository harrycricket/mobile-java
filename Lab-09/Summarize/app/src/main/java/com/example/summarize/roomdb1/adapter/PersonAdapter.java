package com.example.summarize.roomdb1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.summarize.R;
import com.example.summarize.roomdb1.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {
    private Context context;
    private OnItemClickListener listener;
    private List<Person> personList;
    public PersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inlater = LayoutInflater.from(context);

        View view = inlater.inflate(R.layout.person_item_layout, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(context, view, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person data = personList.get(position);

        // Bind data to views
        holder.txtFirstName.setText(data.getFirstName());
        holder.txtLastName.setText(data.getLastName());
        holder.person = data;
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
