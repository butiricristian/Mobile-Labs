package ro.ubb.cristian.drawertest.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.ubb.cristian.drawertest.R;
import ro.ubb.cristian.drawertest.controller.CarController;
import ro.ubb.cristian.drawertest.model.car.Car;
import ro.ubb.cristian.drawertest.observer.Observer;
import ro.ubb.cristian.drawertest.repository.CarRepository;

/**
 * Created by crist on 28-Jan-18.
 */

public class EmployeeFragment extends Fragment implements Observer {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Car> cars = new ArrayList<>();
    private CarRepository carRepository;
    private CarController carController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.employee_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Employee");

        recyclerView = view.findViewById(R.id.employee_car_list);
        progressBar = view.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.fab).setVisibility(View.VISIBLE);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        carRepository = new CarRepository();
        carRepository.subscribe(this);
        carController = new CarController(carRepository, getActivity().findViewById(R.id.parent_layout));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CarNewActivity.class);
                i.putExtra("repository", carRepository);
                startActivity(i);
            }
        });

        carController.getAllCars();
    }

    @Override
    public void updateMe() {
        cars = carRepository.getCars();
        recyclerView.setAdapter(new EmployeeCarAdapter(getContext(), cars, carController));
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
