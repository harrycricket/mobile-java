package com.example.api.service;

import com.example.api.model.Trainee;

import retrofit2.Call;
import retrofit2.Callback;

public class TraineeAPIService {
    private static ITraineeAPIService apiService;

    public TraineeAPIService() {
        apiService = APIClient.getClient().create(ITraineeAPIService.class);
    }

    public void getAll(Callback<Trainee[]> callback) {
        Call<Trainee[]> call = apiService.getAll();
        call.enqueue(callback);
    }

    public void get(long id, Callback<Trainee> callback) {
        Call<Trainee> call = apiService.get(id);
        call.enqueue(callback);
    }

    public void create(Trainee trainee, Callback<Trainee> callback) {
        Call<Trainee> call = apiService.create(trainee);
        call.enqueue(callback);
    }

    public void update(long id, Trainee trainee, Callback<Trainee> callback) {
        Call<Trainee> call = apiService.update(id, trainee);
        call.enqueue(callback);
    }

    public void delete(long id, Callback<Trainee> callback) {
        Call<Trainee> call = apiService.delete(id);
        call.enqueue(callback);
    }
}
