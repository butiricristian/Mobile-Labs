package com.example.cristianbutiri.examskeleton.section2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import com.example.cristianbutiri.examskeleton.R;
import com.example.cristianbutiri.examskeleton.controller.CarController;
import com.example.cristianbutiri.examskeleton.observer.Observer;
import com.example.cristianbutiri.examskeleton.repository.CarRepository;

/**
 * Created by cristianbutiri on 30.01.2018.
 */

public class AllCarsFragment extends Fragment implements Observer {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    CarController carController;
    CarRepository carRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_cars, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.all_car_list);
        progressBar = view.findViewById(R.id.progressBar);

        carRepository = (CarRepository) getArguments().getSerializable("repository");
        carRepository.subscribe(this);
        Log.d("AllCars: ", "Subscribe");
        carController = new CarController(carRepository, getActivity().findViewById(R.id.parent_layout), progressBar);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.add_car);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NewCarActivity.class);
                i.putExtra("repository", carRepository);
                startActivity(i);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();

        carController.getAllCars();
    }

    @Override
    public void updateMe() {
        Log.d("AllCars: ", "Update all cars");
        recyclerView.setAdapter(new AllCarsAdapter(getContext(), carRepository.getCars(), carController));
    }
}