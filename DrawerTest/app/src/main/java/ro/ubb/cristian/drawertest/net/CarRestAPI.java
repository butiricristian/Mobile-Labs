package ro.ubb.cristian.drawertest.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ro.ubb.cristian.drawertest.model.car.Car;

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
    Call<Car> removeCar(@Body Long id);

}
