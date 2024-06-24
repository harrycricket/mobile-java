package com.example.roomdbv2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roomdbv2.R;
import com.example.roomdbv2.model.Module;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleViewHolder> {
    private OnItemClickListener listener;
    private List<Module> moduleList;
    public ModuleAdapter(List<Module> moduleList) {
        this.moduleList = moduleList;
        for (Module module : moduleList) {
            module.setSelected(false);
        }
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inlater = LayoutInflater.from(context);

        View view = inlater.inflate(R.layout.module_item_layout, parent, false);
        ModuleViewHolder viewHolder = new ModuleViewHolder(view, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        Module data = moduleList.get(position);

        // Bind data to views
        holder.txtTitle.setText(data.getTitle());
        holder.txtLabel.setText(data.getLabel());
        holder.txtDesc.setMaxLines(3);
        holder.txtDesc.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtDesc.setText(data.getDesc());
        try {
            Glide.with(holder.itemView.getContext())
                    .load(data.getImgSrc())
                    .placeholder(R.drawable.no_img)
                    .error(R.drawable.no_img)
                    .into(holder.ivAvatar);
        }
        catch (Exception ex) {
            holder.ivAvatar.setImageResource(R.drawable.no_img);
        }
        int backgroundColor = data.isSelected() ? Color.parseColor("#009688") : Color.TRANSPARENT;
        holder.itemView.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
