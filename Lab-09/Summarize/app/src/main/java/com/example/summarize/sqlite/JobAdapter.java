package com.example.summarize.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.summarize.R;

import java.util.List;

public class JobAdapter extends BaseAdapter {
    private SQLiteActivity context;
    private int layout;
    private List<Job> jobList;

    public JobAdapter(SQLiteActivity context, int layout, List<Job> jobList) {
        this.context = context;
        this.layout = layout;
        this.jobList = jobList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(SQLiteActivity context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    @Override
    public int getCount() {
        return jobList.size();
    }

    @Override
    public Object getItem(int position) {
        return jobList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jobList.get(position).getId();
    }

    public class ViewHolder {
        TextView txtName;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.imgDelete = view.findViewById(R.id.imgDelete);
            holder.imgEdit = view.findViewById(R.id.imgEdit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Job job = jobList.get(position);
        holder.txtName.setText(job.getName());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogUpdateJob(job.getName(),job.getId());
            }
        });

        //Bat su kién XOa
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                context.DialogDeleteJob(job.getName(),job.getId());
            }
        });

        return view;
    }
}

