package com.example.football;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Player {
    private int avatarUrl;
    private String name;
    private String description;
    private int flagUrl;

    public Player(int avatarUrl, String name, String description, int flagUrl) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.description = description;
        this.flagUrl = flagUrl;
    }

    public int getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(int avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(int flagUrl) {
        this.flagUrl = flagUrl;
    }
}
