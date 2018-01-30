package ro.ubb.cristian.drawertest.client;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ro.ubb.cristian.drawertest.R;
import ro.ubb.cristian.drawertest.controller.CarController;
import ro.ubb.cristian.drawertest.database.AppDatabase;
import ro.ubb.cristian.drawertest.database.CarDao;
import ro.ubb.cristian.drawertest.database.DatabaseProvider;
import ro.ubb.cristian.drawertest.model.car.Car;
import ro.ubb.cristian.drawertest.repository.CarRepository;

public class ClientCarsFragment extends Fragment {
    RecyclerView recyclerView;
    List<Car> cars = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client_cars, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.client_personal_car_list);
        AppDatabase appDatabase = DatabaseProvider.getDatabaseInstance(getContext());
        CarDao carDao = appDatabase.getCarDao();

        cars = carDao.findAll();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ClientPersonalCarAdapter());
    }
}
