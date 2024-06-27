package com.example.api.service;

import com.example.api.model.Trainee;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ITraineeAPIService {
    String TRAINEES = "Trainees";
    @GET(TRAINEES)
    Call<Trainee[]> getAll();
    @GET(TRAINEES + "/{id}")
    Call<Trainee> get(@Path("id") Object id);
    @POST(TRAINEES)
    Call<Trainee> create(@Body Trainee trainee);
    @PUT(TRAINEES + "/{id}")
    Call<Trainee> update(@Path("id") Object id, @Body Trainee trainee);
    @DELETE(TRAINEES + "/{id}")
    Call<Trainee> delete(@Path("id") Object id);
}
