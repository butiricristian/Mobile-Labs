package ro.ubb.cristian.examskeletonimproved.net;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ro.ubb.cristian.examskeletonimproved.model.Item;

/**
 * Created by cristianbutiri on 29.01.2018.
 */

public interface RestAPI {
    @GET("cars")
    Call<List<Item>> getAvailableCars();
//
//    @GET("all")
//    Call<List<Car>> getAllCars();
//
//    @POST("addCar")
//    Call<Car> addCar(@Body Car car);
//
//    @POST("removeCar")
//    Call<Car> removeCar(@Body Car id);
//
//    @POST("buyCar")
//    Call<Car> buyCar(@Body Map<String, String> car);
//
//    @POST("returnCar")
//    Call<Car> returnCar(@Body Car car);

}
