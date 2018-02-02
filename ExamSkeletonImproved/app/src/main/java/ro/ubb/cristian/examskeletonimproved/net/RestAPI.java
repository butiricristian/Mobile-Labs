package ro.ubb.cristian.examskeletonimproved.net;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import ro.ubb.cristian.examskeletonimproved.model.Item;

/**
 * Created by cristianbutiri on 29.01.2018.
 */

public interface RestAPI {
    @GET("cars")
    Call<List<Item>> getAvailableCars();

    @GET("all")
    Call<List<Item>> getAllCars();

    @POST("addCar")
    Call<Item> addItem(@Body Item car);

    @POST("removeCar")
    Call<Item> removeCar(@Body Item id);

    @POST("buyCar")
    Call<Item> buyCar(@Body Map<String, String> car);

    @POST("returnCar")
    Call<Item> returnCar(@Body Item car);

    @POST("updateCar")
    Call<Item> updateItem(@Body Item car);

}
