package com.example.summarize.football;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.summarize.R;

import java.util.ArrayList;
import java.util.List;

public class FootballActivity extends AppCompatActivity {

    private List<Player> playerList;
    private ListView listViewPlayers;
    private FootballAdapter adapter;
    private EditText name, description, avatar, flag;
    private ImageView avatarUrl, flagUrl;
    private Button btnAdd, btnUpdate, btnDelete;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_football);

        initialView();
        initialData();

        adapter = new FootballAdapter(this, R.layout.player, playerList);
        listViewPlayers.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> addPlayer());
        btnUpdate.setOnClickListener(v -> updatePlayer());
        btnDelete.setOnClickListener(v -> deletePlayer());

        listViewPlayers.setOnItemClickListener(((parent, view, position, id) -> {
            selectedIndex = position;
            Player selectedPlayer = playerList.get(position);
            name.setText(selectedPlayer.getName());
            description.setText(selectedPlayer.getDescription());
            avatar.setText(selectedPlayer.getAvatarUrl());
            flag.setText(selectedPlayer.getFlagUrl());
            Glide.with(this)
                    .load(selectedPlayer.getAvatarUrl())
                    .apply(new RequestOptions().error(R.drawable.no_avatar))
                    .into(avatarUrl);
            Glide.with(this)
                    .load(selectedPlayer.getFlagUrl())
                    .apply(new RequestOptions().error(R.drawable.no_avatar))
                    .into(flagUrl);
        }));

        avatar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String imageUrl = s.toString();
                if (!imageUrl.isEmpty()) {
                    Glide.with(FootballActivity.this)
                            .load(imageUrl)
                            .apply(new RequestOptions().error(R.drawable.no_avatar)) // Set error placeholder
                            .into(avatarUrl);
                } else {
                    avatarUrl.setImageResource(R.drawable.no_avatar); // Set a default or error image
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        flag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String imageUrl = s.toString();
                if (!imageUrl.isEmpty()) {
                    Glide.with(FootballActivity.this)
                            .load(imageUrl)
                            .apply(new RequestOptions().error(R.drawable.no_avatar))
                            .into(flagUrl);
                } else {
                    flagUrl.setImageResource(R.drawable.no_avatar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initialData() {
        playerList = new ArrayList<>();
        playerList.add(new Player("https://www.guinnessworldrecords.com/Images/Diego-Maradona-playing-football-1982_tcm25-640178.jpg", "Diego Armando Maradona", "October 30, 1960 (age 60)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2560px-Flag_of_Argentina.svg.png"));
        playerList.add(new Player("https://cafebiz.cafebizcdn.vn/162123310254002176/2022/12/5/photo-1-16702077650772133442778-1670210463499-1670210463633545287280-1670215699095-1670215699308874127438.jpg", "Pelé", "October 23, 1940 (age 82)",
                "https://upload.wikimedia.org/wikipedia/en/thumb/0/05/Flag_of_Brazil.svg/1200px-Flag_of_Brazil.svg.png"));
        playerList.add(new Player("https://images.news18.com/ibnlive/uploads/2022/12/collage-maker-19-dec-2022-03.45-pm-167145567216x9.jpg?impolicy=website&width=1200&height=675", "Lionel Andrés Messi", "June 24, 1987 (age 36)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2560px-Flag_of_Argentina.svg.png"));
        playerList.add(new Player("https://www.fcbarcelona.com/fcbarcelona/photo/2018/03/19/3e6646b4-f420-4c29-817e-f8172d484a20/4664952.jpg", "Ronaldo de Assis Moreira", "March 21, 1980 (age 44)",
                "https://upload.wikimedia.org/wikipedia/en/thumb/0/05/Flag_of_Brazil.svg/1200px-Flag_of_Brazil.svg.png"));
        playerList.add(new Player("https://www.sportico.com/wp-content/uploads/2023/02/GettyImages-1395023758-e1677009593623.jpg?w=1280&h=720&crop=1", "Cristiano Ronaldo", "February 5, 1985 (age 39)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Portugal.svg/2560px-Flag_of_Portugal.svg.png"));
        playerList.add(new Player("https://3news.com/wp-content/uploads/2023/06/Modric.jpg", "Luka Modrić", "September 9, 1985 (age 38)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Flag_of_Croatia.svg/2000px-Flag_of_Croatia.svg.png"));
        playerList.add(new Player("https://cdn.britannica.com/82/257582-050-9E5F80C8/Germany-goalkeeper-Manuel-Neuer-stops-the-ball-during-a-soccer-game.jpg", "Manuel Peter Neuer", "March 27, 1986 (age 38)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Flag_of_Germany.svg/2560px-Flag_of_Germany.svg.png"));
        playerList.add(new Player("https://footballtoday.com/wp-content/uploads/2023/06/manchester-city-v-manchester-united-emirates-fa-cup-final-2-1000x600.jpg", "Erling Braut Haaland", "July 21, 2000 (age 23)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Norway.svg/1200px-Flag_of_Norway.svg.png"));
        playerList.add(new Player("https://images.kataeb.org/new-website/Sports/Football/RonaldoNazarioo.jpg", "Ronaldo Luís Nazário de Lima", "September 18, 1976 (age 47)",
                "https://upload.wikimedia.org/wikipedia/en/thumb/0/05/Flag_of_Brazil.svg/1200px-Flag_of_Brazil.svg.png"));
        playerList.add(new Player("https://prod-media.beinsports.com/image/1695506402725_3c4c5807-4ec8-47c6-9e75-082a337121c5.jpg", "Harry Edward Kane", "July 28, 1993 (age 30)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Flag_of_the_United_Kingdom_%281-2%29.svg/1200px-Flag_of_the_United_Kingdom_%281-2%29.svg.png"));
        playerList.add(new Player("https://www.thetimes.co.uk/imageserver/image/%2Fmethode%2Ftimes%2Fprod%2Fweb%2Fbin%2Fa696f77a-d71e-11ea-8f95-6d813022b2d7.jpg?crop=4500%2C2531%2C0%2C459&resize=1200", "Karim Mostafa Benzema", "December 19, 1987 (age 36)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Flag_of_France.svg/2560px-Flag_of_France.svg.png"));
        playerList.add(new Player("https://nbcsports.brightspotcdn.com/dims4/default/1862a36/2147483647/strip/true/crop/2975x1673+0+219/resize/1440x810!/quality/90/?url=https%3A%2F%2Fnbc-sports-production-nbc-sports.s3.us-east-1.amazonaws.com%2Fbrightspot%2F39%2F04%2F09fec22920d8ca49521cb1e89cc5%2Fcd0ymzcznguwzdbhnduynddiytjhm2yyzthlmtjjotqwyyznpwq1nmi4ngu2ntywnda2n2u2ntq3yjjkotm2mjg3ytdh.jpeg", "Neymar da Silva Santos Júnior", "February 5, 1992 (age 32)",
                "https://upload.wikimedia.org/wikipedia/en/thumb/0/05/Flag_of_Brazil.svg/1200px-Flag_of_Brazil.svg.png"));
        playerList.add(new Player("https://imgresizer.eurosport.com/unsafe/1200x0/filters:format(jpeg)/origin-imgresizer.eurosport.com/2022/07/17/3412096-69722088-2560-1440.jpg", "Robert Lewandowski", "August 21, 1988 (age 35)",
                "https://upload.wikimedia.org/wikipedia/en/thumb/1/12/Flag_of_Poland.svg/1200px-Flag_of_Poland.svg.png"));
        playerList.add(new Player("https://tmssl.akamaized.net/images/foto/galerie/kylian-mbappe-psg-2023-1677479173-102635.jpg?lm=1677479183", "Kylian Mbappé Lottin", "December 20, 1998 (age 25)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Flag_of_France.svg/2560px-Flag_of_France.svg.png"));
    }

    private void initialView() {
        listViewPlayers = findViewById(R.id.listViewPlayers);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        avatar = findViewById(R.id.avatar);
        avatarUrl = findViewById(R.id.imagePlayer);
        flagUrl = findViewById(R.id.imageFlag);
        flag = findViewById(R.id.flag);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void addPlayer() {
        String playerName = name.getText().toString();
        String playerDes = description.getText().toString();
        String playerAvatarUrl = avatar.getText().toString();
        String flagUrl = flag.getText().toString();

        if (playerName.isEmpty() || playerDes.isEmpty() || playerAvatarUrl.isEmpty() || flagUrl.isEmpty()) {
            Toast.makeText(this, "Please input all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedIndex != -1) {
            Player selectedPlayer = playerList.get(selectedIndex);
            if (playerName.equals(selectedPlayer.getName()) &&
                    playerDes.equals(selectedPlayer.getDescription()) &&
                    playerAvatarUrl.equals(selectedPlayer.getAvatarUrl()) &&
                    flagUrl.equals(selectedPlayer.getFlagUrl())) {
                Toast.makeText(this, "Duplicate player!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Player newPlayer = new Player(playerAvatarUrl, playerName, playerDes, flagUrl);
        playerList.add(0, newPlayer);
        adapter.notifyDataSetChanged();
        clearFields();
    }

    private void updatePlayer() {
        if (selectedIndex == -1) {
            Toast.makeText(this, "Please select a player to update", Toast.LENGTH_SHORT).show();
            return;
        }

        String playerName = name.getText().toString();
        String playerDes = description.getText().toString();
        String playerAvatarUrl = avatar.getText().toString();
        String flagUrl = flag.getText().toString();

        if (playerName.isEmpty() || playerDes.isEmpty() || playerAvatarUrl.isEmpty() || flagUrl.isEmpty()) {
            Toast.makeText(this, "Please input all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Player updatePlayer = playerList.get(selectedIndex);
        updatePlayer.setName(playerName);
        updatePlayer.setDescription(playerDes);
        updatePlayer.setAvatarUrl(playerAvatarUrl);
        updatePlayer.setFlagUrl(flagUrl);

        adapter.notifyDataSetChanged();
        clearFields();
        selectedIndex = -1;
    }

    private void deletePlayer() {
        if (selectedIndex == -1) {
            Toast.makeText(this, "Please select a player before delete", Toast.LENGTH_SHORT).show();
            return;
        }

        playerList.remove(selectedIndex);
        adapter.notifyDataSetChanged();
        clearFields();
        selectedIndex = -1;
    }

    private void clearFields() {
        name.setText("");
        description.setText("");
        avatar.setText("");
        flag.setText("");
    }
}