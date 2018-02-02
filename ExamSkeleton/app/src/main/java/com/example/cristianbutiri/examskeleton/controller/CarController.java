package com.example.cristianbutiri.examskeleton.controller;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cristianbutiri.examskeleton.database.AppDatabase;
import com.example.cristianbutiri.examskeleton.database.CarDao;
import com.example.cristianbutiri.examskeleton.database.DatabaseProvider;
import com.example.cristianbutiri.examskeleton.model.Car;
import com.example.cristianbutiri.examskeleton.net.CarRestAPI;
import com.example.cristianbutiri.examskeleton.repository.CarRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by cristianbutiri on 29.01.2018.
 */

public class CarController implements Serializable{

    private static final String BASE_URL = "http://192.168.100.2:4000/";
    private Retrofit retrofit;
    private CarRepository carRepository;
    private View snackbarView;
    private ProgressBar progressBar;
    private Context mCtx;

    public CarController(Context ctx, CarRepository carRepository, View snackbarView, ProgressBar progressBar){
        this.carRepository = carRepository;
        this.snackbarView = snackbarView;
        this.progressBar = progressBar;
        this.mCtx = ctx;
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
//                Snackbar.make(snackbarView, "You have added a car", Snackbar.LENGTH_LONG).show();
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
//                Snackbar.make(snackbarView, "You have removed a car", Snackbar.LENGTH_LONG).show();
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
                    Car car = response.body();
                    AppDatabase database = DatabaseProvider.getDatabaseInstance(mCtx);
                    CarDao carDao = database.getCarDao();
                    Log.d("buyCar", car.toString());
                    Car car2 = carDao.findOne(car.getId());
                    if(car2 == null) {
                        car2 = new Car();
                        car2.setId(car.getId());
                        car2.setQuantity(1);
                        car2.setName(car.getName());
                        car2.setStatus(car.getStatus());
                        car2.setType(car.getType());
                        carDao.save(car2);
                    }
                    else{
                        Log.d("buyCar", car2.toString());
                        car2.setQuantity(car2.getQuantity() + 1);
                        carDao.update(car2);
                    }
                    getAvailableCars();
                    Snackbar.make(snackbarView, "You have purchased a car", Snackbar.LENGTH_LONG).show();
                    Log.d("buyCar", "Success: " + response.message());
                }
                else{
                    String errMessage = "";
                    try {
                        errMessage = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Snackbar.make(snackbarView, errMessage, Snackbar.LENGTH_LONG).show();
                    Log.d("buyCar", "Failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Snackbar.make(snackbarView, t.getMessage(), Snackbar.LENGTH_LONG).show();
                Log.d("buyCar", "Failed " + t.toString());
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
                    Snackbar.make(snackbarView, "You have returned a car", Snackbar.LENGTH_LONG).show();
                    Log.d("returnCar", "Success: " + response.message());
                }
                else{
                    String errMessage = "";
                    try {
                        errMessage = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Snackbar.make(snackbarView, errMessage, Snackbar.LENGTH_LONG).show();
                    Log.d("returnCar", "Failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                Log.d("returnCar", "Failed " + t.getMessage());
            }
        });
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }
}
