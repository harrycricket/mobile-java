package com.example.summarize.roomdb1.data;

import com.example.summarize.roomdb1.db.AppDatabase;
import com.example.summarize.roomdb1.executors.AppExecutors;
import com.example.summarize.roomdb1.model.Person;

public class GlobalData {
    private static GlobalData instance;

    private Person currentPerson;
    private AppDatabase appDatabase;

    private GlobalData() {}

    public static synchronized GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public void setAppDatabase(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void save(Person person) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            appDatabase.personDAO().insert(person);
        });
    }

    public void update(Person person) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            appDatabase.personDAO().update(person);
        });
    }
}
