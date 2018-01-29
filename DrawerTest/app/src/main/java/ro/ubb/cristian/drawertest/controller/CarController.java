package ro.ubb.cristian.drawertest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.ubb.cristian.drawertest.model.car.Car;
import ro.ubb.cristian.drawertest.net.CarRestAPI;

/**
 * Created by cristianbutiri on 29.01.2018.
 */

public class CarController {

    private static final String BASE_URL = "http://10.0.2.2:4000/";
    private Retrofit retrofit;

    private void initStuff(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Call<List<Car>> start(){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        return carRestAPI.getAvailableCars();
    }

    public Call<Car> addCar(Car car){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        return carRestAPI.addCar(car);
    }

    public Call<Car> deleteCar(Long id){
        initStuff();
        CarRestAPI carRestAPI = retrofit.create(CarRestAPI.class);
        return carRestAPI.removeCar(id);
    }
}
