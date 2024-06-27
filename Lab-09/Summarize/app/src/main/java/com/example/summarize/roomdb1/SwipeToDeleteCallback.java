package com.example.summarize.roomdb1;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.summarize.roomdb1.db.AppDatabase;
import com.example.summarize.roomdb1.executors.AppExecutors;
import com.example.summarize.roomdb1.model.Person;

import java.util.List;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private Context context;
    private RecyclerView.Adapter mAdapter;
    private AppDatabase appDatabase;
    private List<Person> mList;

    public SwipeToDeleteCallback(Context context, RecyclerView.Adapter adapter, AppDatabase database, List<Person> list) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.context = context;
        mAdapter = adapter;
        appDatabase = database;
        mList = list;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        Person personToDelete = mList.get(position);
        AppExecutors.getInstance().diskIO().execute(() -> {
            appDatabase.personDAO().delete(personToDelete);
        });
        mList.remove(position);
        mAdapter.notifyItemRemoved(position);

        Toast.makeText(this.context, "Removed successfully!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#CD8484")); // Màu nền của item khi vuốt
        c.drawRect(new Rect(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom()), paint);
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}