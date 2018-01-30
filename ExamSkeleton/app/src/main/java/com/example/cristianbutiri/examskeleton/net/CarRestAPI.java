package com.example.cristianbutiri.examskeleton.net;

import com.example.cristianbutiri.examskeleton.model.Car;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by cristianbutiri on 29.01.2018.
 */

public interface CarRestAPI {
    @GET("cars")
    Call<List<Car>> getAvailableCars();

    @GET("all")
    Call<List<Car>> getAllCars();

    @POST("addCar")
    Call<Car> addCar(@Body Car car);

    @POST("removeCar")
    Call<Car> removeCar(@Body Car id);

    @POST("buyCar")
    Call<Car> buyCar(@Body Map<String, String> car);

    @POST("returnCar")
    Call<Car> returnCar(@Body Car car);

}
