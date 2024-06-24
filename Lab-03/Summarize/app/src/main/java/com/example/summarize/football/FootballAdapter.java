package com.example.summarize.football;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.summarize.R;

import java.util.List;

public class FootballAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Player> playerList;

    public FootballAdapter(Context context, int layout, List<Player> playerList) {
        this.context = context;
        this.layout = layout;
        this.playerList = playerList;
    }

    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public Object getItem(int position) {
        return playerList.get(position);
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

        TextView name = convertView.findViewById(R.id.name);
        TextView description = convertView.findViewById(R.id.description);
        ImageView avatar = convertView.findViewById(R.id.avatar);
        ImageView flag = convertView.findViewById(R.id.flag);

        Player player = playerList.get(position);
        name.setText(player.getName());
        description.setText(player.getDescription());
        Glide.with(context)
                .load(player.getAvatarUrl())
                .apply(new RequestOptions().error(R.drawable.no_avatar))
                .into(avatar);
        Glide.with(context)
                .load(player.getFlagUrl())
                .apply(new RequestOptions().error(R.drawable.no_avatar))
                .into(flag);
        return convertView;
    }
}
