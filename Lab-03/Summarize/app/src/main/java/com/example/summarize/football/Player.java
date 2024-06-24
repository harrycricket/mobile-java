package com.example.summarize.football;

public class Player {
    private String avatarUrl;
    private String name;
    private String description;
    private String flagUrl;

    public Player(String avatarUrl, String name, String description, String flagUrl) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.description = description;
        this.flagUrl = flagUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
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

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}
