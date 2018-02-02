package ro.ubb.cristian.examskeletonimproved.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.ubb.cristian.examskeletonimproved.model.Item;
import ro.ubb.cristian.examskeletonimproved.net.RestAPI;
import ro.ubb.cristian.examskeletonimproved.observer.Subject;

/**
 * Created by crist on 31-Jan-18.
 */

public class ItemController extends Subject{
    private static final String BASE_URL = "http://192.168.100.2:4000/";
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

    public Call<List<Item>> getAvailableCars() {
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.getAvailableCars();
    }

    public Call<List<Item>> getAllCars(){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.getAllCars();
    }

    public Call<Item> addItem(Item car){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.addItem(car);
    }

    public Call<Item> deleteCar(Item car){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.removeCar(car);
    }

    public Call<Item> buyCar(Map<String, String> car){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.buyCar(car);
    }

    public Call<Item> returnCar(Item item){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.returnCar(item);
    }
}
