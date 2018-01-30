package com.example.cristianbutiri.examskeleton.section1;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.example.cristianbutiri.examskeleton.R;
import com.example.cristianbutiri.examskeleton.controller.CarController;
import com.example.cristianbutiri.examskeleton.net.NetworkUtil;
import com.example.cristianbutiri.examskeleton.observer.Observer;
import com.example.cristianbutiri.examskeleton.repository.CarRepository;

public class AvailableCarsFragment extends Fragment implements Observer {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    Button retryButton;
    TextView textViewNoConnection;
    CarController carController;
    CarRepository carRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_available_cars, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.available_car_list);
        progressBar = view.findViewById(R.id.progressBar);
        textViewNoConnection = view.findViewById(R.id.no_connection_text);
        retryButton = view.findViewById(R.id.retry_button);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(getArguments().containsKey("repository")) {
            carRepository = (CarRepository) getArguments().getSerializable("repository");
            carRepository.subscribe(this);
            carController = new CarController(carRepository, getActivity().findViewById(R.id.parent_layout), progressBar);
        }

        checkConnection();

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnection();
            }
        });
    }

    private void checkConnection(){
        progressBar.setVisibility(View.VISIBLE);
        if(NetworkUtil.isNetworkAvailable(getContext())){
            Log.d("Network", "Connection available");
            recyclerView.setVisibility(View.VISIBLE);
            textViewNoConnection.setVisibility(View.INVISIBLE);
            retryButton.setVisibility(View.INVISIBLE);

            carController.getAvailableCars();
        }
        else{
            Log.d("Network", "Connection not available");
            recyclerView.setVisibility(View.INVISIBLE);
            textViewNoConnection.setVisibility(View.VISIBLE);
            retryButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void updateMe() {
        Log.d("AvailableCars: ", "Update cars' list");
        recyclerView.setAdapter(new AvailableCarsAdapter(getContext(), this.carRepository.getCars(), carController));
    }
}
