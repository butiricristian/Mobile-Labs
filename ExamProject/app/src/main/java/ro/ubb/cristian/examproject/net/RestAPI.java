package ro.ubb.cristian.examproject.net;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ro.ubb.cristian.examproject.model.Project;

/**
 * Created by cristianbutiri on 29.01.2018.
 */

public interface RestAPI {
    @GET("ideas")
    Call<List<Project>> getIdeas();

    @POST("add")
    Call<Project> addItem(@Body Project project);

    @DELETE("remove/{id}")
    Call<Project> removeCar(@Path("id") Long id);

    @GET("projects")
    Call<List<Project>> getProjects();

    @POST("promote")
    Call<Project> promoteIdea(@Body Project car);

    @POST("approve")
    Call<Project> approveProject(@Body Project car);

    @POST("discard")
    Call<Project> discardProject(@Body Project car);

}
