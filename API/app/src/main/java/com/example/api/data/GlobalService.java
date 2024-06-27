package com.example.api.data;

import com.example.api.model.Trainee;

public class GlobalService {
    private static GlobalService instance;

    private Trainee currentTrainee;

    private GlobalService() {}

    public static synchronized GlobalService getInstance() {
        if (instance == null) {
            instance = new GlobalService();
        }
        return instance;
    }

    public Trainee getCurrentTrainee() {
        return currentTrainee;
    }

    public void setCurrentTrainee(Trainee currentTrainee) {
        this.currentTrainee = currentTrainee;
    }
}
