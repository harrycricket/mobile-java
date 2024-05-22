package com.example.football;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView playerList;
    ArrayList<Player> playerArray;
    FootballAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        playerList = findViewById(R.id.playerList);
        playerArray = new ArrayList<>();
        playerArray.add(new Player(R.drawable.maradona, "Diego Armando Maradona", "October 30, 1960 (age 60)", R.drawable.argentina));
        playerArray.add(new Player(R.drawable.pele, "Pelé", "October 23, 1940 (age 82)", R.drawable.brazil));
        playerArray.add(new Player(R.drawable.messi, "Lionel Andrés Messi", "June 24, 1987 (age 36)", R.drawable.argentina));
        playerArray.add(new Player(R.drawable.ronaldinho, "Ronaldo de Assis Moreira", "March 21, 1980 (age 44)", R.drawable.brazil));
        playerArray.add(new Player(R.drawable.ronaldo, "Cristiano Ronaldo", "February 5, 1985 (age 39)", R.drawable.portugal));
        playerArray.add(new Player(R.drawable.lokamodric, "Luka Modrić", "September 9, 1985 (age 38)", R.drawable.croatia));
        playerArray.add(new Player(R.drawable.neuer, "Manuel Peter Neuer", "March 27, 1986 (age 38)", R.drawable.germany));
        playerArray.add(new Player(R.drawable.haaland, "Erling Braut Haaland", "July 21, 2000 (age 23)", R.drawable.norway));
        playerArray.add(new Player(R.drawable.ronaldodelima, "Ronaldo Luís Nazário de Lima", "September 18, 1976 (age 47)", R.drawable.brazil));
        playerArray.add(new Player(R.drawable.kane, "Harry Edward Kane", "July 28, 1993 (age 30)", R.drawable.england));
        playerArray.add(new Player(R.drawable.benzema, "Karim Mostafa Benzema", "December 19, 1987 (age 36)", R.drawable.france));
        playerArray.add(new Player(R.drawable.neymar, "Neymar da Silva Santos Júnior", "February 5, 1992 (age 32)", R.drawable.brazil));
        playerArray.add(new Player(R.drawable.lewandowski, "Robert Lewandowski", "August 21, 1988 (age 35)", R.drawable.poland));
        playerArray.add(new Player(R.drawable.mbappe, "Kylian Mbappé Lottin", "December 20, 1998 (age 25)", R.drawable.france));

        adapter = new FootballAdapter(getApplicationContext(), playerArray);
        playerList.setAdapter(adapter);
    }
}