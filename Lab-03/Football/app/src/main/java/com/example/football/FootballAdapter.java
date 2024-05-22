package com.example.football;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FootballAdapter extends BaseAdapter {
    private Context context;
    private List<Player> playerList;
    LayoutInflater inflater;

    public FootballAdapter(Context context, List<Player> playerList) {
        this.context = context;
        this.playerList = playerList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return playerList.size();
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
        convertView = inflater.inflate(R.layout.player, null);
        TextView name = convertView.findViewById(R.id.name);
        TextView description = convertView.findViewById(R.id.description);
        ImageView avatar = convertView.findViewById(R.id.avatar);
        ImageView flag = convertView.findViewById(R.id.flag);

        Player player = playerList.get(position);
        name.setText(player.getName());
        description.setText(player.getDescription());
        avatar.setImageResource(player.getAvatarUrl());
        flag.setImageResource(player.getFlagUrl());
        return convertView;
    }
}
