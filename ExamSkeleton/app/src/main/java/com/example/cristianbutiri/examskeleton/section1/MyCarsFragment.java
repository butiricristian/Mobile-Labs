package com.example.cristianbutiri.examskeleton.section1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cristianbutiri.examskeleton.R;
import com.example.cristianbutiri.examskeleton.controller.CarController;
import com.example.cristianbutiri.examskeleton.database.AppDatabase;
import com.example.cristianbutiri.examskeleton.database.CarDao;
import com.example.cristianbutiri.examskeleton.database.DatabaseProvider;
import com.example.cristianbutiri.examskeleton.observer.Observer;
import com.example.cristianbutiri.examskeleton.repository.CarRepository;

/**
 * Created by cristianbutiri on 30.01.2018.
 */

public class MyCarsFragment extends Fragment implements Observer{
    RecyclerView recyclerView;
    CarController carController;
    CarRepository carRepository;
    CarDao carDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cars, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase database = DatabaseProvider.getDatabaseInstance(getContext());
        CarDao carDao = database.getCarDao();

        carRepository = new CarRepository();
        carRepository.setCars(carDao.findAll());
        carRepository.subscribe(this);
        carController = new CarController(carRepository, getActivity().findViewById(R.id.parent_layout), null);

        recyclerView = view.findViewById(R.id.my_car_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new MyCarsAdapter(getContext(), carRepository.getCars(), carController));
    }

    @Override
    public void updateMe() {
        Log.d("My cars", "updated");
        recyclerView.setAdapter(new MyCarsAdapter(getContext(), carRepository.getCars(), carController));
    }
}
