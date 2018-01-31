package ro.ubb.cristian.examskeletonimproved.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.ubb.cristian.examskeletonimproved.model.Item;
import ro.ubb.cristian.examskeletonimproved.net.RestAPI;

/**
 * Created by crist on 31-Jan-18.
 */

public class ItemController {
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
}
