package ro.ubb.cristian.examproject.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.ubb.cristian.examproject.model.Project;
import ro.ubb.cristian.examproject.net.RestAPI;
import ro.ubb.cristian.examproject.observer.Subject;

/**
 * Created by crist on 31-Jan-18.
 */

public class ItemController extends Subject{
    private static final String BASE_URL = "http://192.168.100.2:4026/";
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

    public Call<List<Project>> getIdeas() {
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.getIdeas();
    }

    public Call<List<Project>> getProjects(){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.getProjects();
    }

    public Call<Project> addProject(Project car){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.addItem(car);
    }

    public Call<Project> deleteProject(Project car){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.removeCar(car.getId());
    }

    public Call<Project> promoteIdea(Project idea){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.promoteIdea(idea);
    }

    public Call<Project> approveProject(Project project){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.approveProject(project);
    }

    public Call<Project> discardProject(Project project){
        initStuff();
        RestAPI carRestAPI = retrofit.create(RestAPI.class);
        return carRestAPI.discardProject(project);
    }
}
