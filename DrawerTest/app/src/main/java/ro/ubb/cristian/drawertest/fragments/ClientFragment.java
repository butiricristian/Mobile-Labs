package ro.ubb.cristian.drawertest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.ubb.cristian.drawertest.R;
import ro.ubb.cristian.drawertest.controller.CarController;
import ro.ubb.cristian.drawertest.model.car.Car;
import ro.ubb.cristian.drawertest.model.car.CarAdapter;
import ro.ubb.cristian.drawertest.net.NetworkUtil;

/**
 * Created by crist on 28-Jan-18.
 */

public class ClientFragment extends Fragment {
    RecyclerView recyclerView;
    List<Car> cars = new ArrayList<>();
    TextView noNetworkConnection;
    Button retryButton;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.client_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.client_car_list);
        noNetworkConnection = view.findViewById(R.id.no_connection_text);
        retryButton = view.findViewById(R.id.retry_button);
        progressBar = view.findViewById(R.id.progressBar);

        checkConnection(view);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnection(view);
            }
        });

        getActivity().setTitle("Client");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        cars.add(new Car(1L, "Mertzan", 10, "Sedan", "selling"));
//        cars.add(new Car(2L, "Audi", 10, "Combi", "selling"));
//        cars.add(new Car(3L, "Mertzan", 10, "4x4", "selling"));
//        cars.add(new Car(4L, "BMW-u", 10, "Septar", "selling"));
//        cars.add(new Car(5L, "Dacia", 10, "Logan", "selling"));
//        cars.add(new Car(6L, "Caruta", 10, "Lux", "selling"));

        recyclerView.setAdapter(new CarAdapter(getContext(), cars));
    }

    private void checkConnection(final View view){
        progressBar.setVisibility(View.VISIBLE);
        if(NetworkUtil.isNetworkAvailable(getContext())){
            Log.d("Network", "Connection available");
            recyclerView.setVisibility(View.VISIBLE);
            noNetworkConnection.setVisibility(View.INVISIBLE);
            retryButton.setVisibility(View.INVISIBLE);

            CarController carController = new CarController();
            carController.start().enqueue(new Callback<List<Car>>() {
                @Override
                public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                    cars = response.body();
                    recyclerView.setAdapter(new CarAdapter(getContext(), cars));
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<List<Car>> call, Throwable t) {
                    Snackbar.make(view, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }
        else{
            Log.d("Network", "Connection not available");
            recyclerView.setVisibility(View.INVISIBLE);
            noNetworkConnection.setVisibility(View.VISIBLE);
            retryButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
