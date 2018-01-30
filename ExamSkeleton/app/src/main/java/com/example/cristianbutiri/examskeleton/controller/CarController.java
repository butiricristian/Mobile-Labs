package com.example.cristianbutiri.examskeleton.controller;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cristianbutiri.examskeleton.model.Car;
import com.example.cristianbutiri.examskeleton.net.CarRestAPI;
import com.example.cristianbutiri.examskeleton.repository.CarRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by cristianbutiri on 29.01.2018.
 */

public class CarController implements Serializable{

    private static final String BASE_URL = "http://10.0.2.2:4000/";
    private Retrofit retrofit;
    private CarRepository carRepository;
    private View snackbarView;
    private ProgressBar progressBar;

    public CarController(CarRepository carRepository, View snackbarView, ProgressBar progressBar){
        this.carRepository = carRepository;
        this.snackbarView = snackbarView;
        this.progressBar = progressBar;
    }

    private void initStuff(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void getAvailableCars(){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        carRestAPI.getAvailableCars().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if(response.isSuccessful()) {
                    carRepository.setCars(response.body());
                    carRepository.notifyObservers();
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d("getAvailableCars", "Success: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("getAvailableCars", "Failed " + t.getMessage());
            }
        });
    }

    public void getAllCars(){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        carRestAPI.getAllCars().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if(response.isSuccessful()) {
                    carRepository.setCars(response.body());
                    carRepository.notifyObservers();
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d("getAllCars", "Success: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("getAllCars", "Failed " + t.getMessage());
            }
        });
    }

    public void addCar(Car car){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        carRestAPI.addCar(car).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
//                getAllCars();
                Log.d("addCar", response.body().toString());
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                Log.d("addCar", "Failed " + t.getMessage());
            }
        });
    }

    public void deleteCar(Car car){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        carRestAPI.removeCar(car).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                Log.d("removeCar: ", "Successful");
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                Log.d("removeCar", "Failed " + t.getMessage());
            }
        });
    }

    public void buyCar(Map<String, String> car){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        carRestAPI.buyCar(car).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                if(response.isSuccessful()){
                    getAvailableCars();
                    Log.d("buyCar", "Success: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                Log.d("buyCar", "Failed " + t.getMessage());
            }
        });
    }

    public void returnCar(Car car){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        carRestAPI.returnCar(car).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                if(response.isSuccessful()){
//                    getAvailableCars();
                    Log.d("buyCar", "Success: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                Log.d("buyCar", "Failed " + t.getMessage());
            }
        });
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }
}
