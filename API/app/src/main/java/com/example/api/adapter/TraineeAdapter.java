package com.example.api.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.EditActivity;
import com.example.api.R;
import com.example.api.data.GlobalService;
import com.example.api.model.Trainee;
import com.example.api.service.TraineeAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraineeAdapter extends RecyclerView.Adapter<TraineeViewHolder> {
    private Context context;
    private OnItemClickListener listener;
    private List<Trainee> list;
    public TraineeAdapter(Context context, List<Trainee> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TraineeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inlater = LayoutInflater.from(context);

        View view = inlater.inflate(R.layout.trainee_item_layout, parent, false);
        TraineeViewHolder viewHolder = new TraineeViewHolder(context, view, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TraineeViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        Trainee data = list.get(pos);

        // Bind data to views
        holder.data = data;
        holder.tvName.setText(data.getName());
        holder.tvPhone.setText(data.getPhone());
        holder.tvEmail.setText(data.getEmail());
        holder.tvGender.setText(data.getGender());

        holder.ivEdit.setOnClickListener(v -> {
            GlobalService.getInstance().setCurrentTrainee(data);
            Intent intent = new Intent(this.context, EditActivity.class);
            this.context.startActivity(intent);
        });
        holder.ivDelete.setOnClickListener(v -> {
            TraineeAPIService traineeAPIService = new TraineeAPIService();

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("Are you sure to delete this item?");
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    traineeAPIService.delete(data.getId(), new Callback<Trainee>() {
                        @Override
                        public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                            if (response.isSuccessful()) {
                                list.remove(pos);
                                TraineeAdapter.this.notifyItemRemoved(pos);
                                Toast.makeText(context, "Removed successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Something wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Trainee> call, Throwable t) {
                            Toast.makeText(context, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
