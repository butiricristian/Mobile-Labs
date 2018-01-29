package ro.ubb.cristian.drawertest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ro.ubb.cristian.drawertest.R;
import ro.ubb.cristian.drawertest.model.car.Car;
import ro.ubb.cristian.drawertest.model.car.CarAdapter;

/**
 * Created by crist on 28-Jan-18.
 */

public class ClientFragment extends Fragment {
    RecyclerView recyclerView;
    List<Car> cars;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.client_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Client");

        cars = new ArrayList<>();
        recyclerView = view.findViewById(R.id.client_car_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cars.add(new Car(1L, "Mertzan", 10, "Sedan", "selling"));
        cars.add(new Car(2L, "Audi", 10, "Combi", "selling"));
        cars.add(new Car(3L, "Mertzan", 10, "4x4", "selling"));
        cars.add(new Car(4L, "BMW-u", 10, "Septar", "selling"));
        cars.add(new Car(5L, "Dacia", 10, "Logan", "selling"));
        cars.add(new Car(6L, "Caruta", 10, "Lux", "selling"));

        recyclerView.setAdapter(new CarAdapter(getContext(), cars));
    }
}
