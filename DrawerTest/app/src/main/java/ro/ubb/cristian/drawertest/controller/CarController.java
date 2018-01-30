package ro.ubb.cristian.drawertest.controller;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

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
import ro.ubb.cristian.drawertest.R;
import ro.ubb.cristian.drawertest.model.car.Car;
import ro.ubb.cristian.drawertest.net.CarRestAPI;
import ro.ubb.cristian.drawertest.repository.CarRepository;

/**
 * Created by cristianbutiri on 29.01.2018.
 */

public class CarController implements Serializable{

    private static final String BASE_URL = "http://192.168.100.2:4000/";
    private Retrofit retrofit;
    private CarRepository carRepository;
    private View snackbarView;

    public CarController(CarRepository carRepository, View snackbarView){
        this.carRepository = carRepository;
        this.snackbarView = snackbarView;
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
                    Log.d("getAvailableCars", "Success: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
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
                    Log.d("getAllCars", "Success: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Snackbar.make(snackbarView, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
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

    public Call<Car> deleteCar(Car car){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        return carRestAPI.removeCar(car);
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
}
