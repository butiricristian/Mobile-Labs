package ro.ubb.cristian.drawertest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.ubb.cristian.drawertest.controller.CarController;
import ro.ubb.cristian.drawertest.model.car.Car;

/**
 * Created by crist on 29-Jan-18.
 */

public class CarNewFragment extends Fragment {

    public static final String CAR = "car";
    private Car currentCar;
    EditText etCarName, etCarType, etCarQuantity, etCarStatus;

    public CarNewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle("New Car");
        }

        currentCar = new Car();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.car_new, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etCarName = (EditText)view.findViewById(R.id.new_car_name);
        etCarQuantity = (EditText)view.findViewById(R.id.new_car_quantity);
        etCarStatus = (EditText)view.findViewById(R.id.new_car_status);
        etCarType = (EditText)view.findViewById(R.id.new_car_type);

        final CarController carController = new CarController();

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                currentCar.setName(etCarName.getText().toString());
                currentCar.setQuantity(Integer.valueOf(etCarQuantity.getText().toString()));
                currentCar.setStatus(etCarStatus.getText().toString());
                currentCar.setType(etCarType.getText().toString());

                carController.addCar(currentCar).enqueue(new Callback<Car>() {
                    @Override
                    public void onResponse(Call<Car> call, Response<Car> response) {
                        Log.d("New Car", response.body().toString());
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(Call<Car> call, Throwable t) {
                        Snackbar.make(view, t.getLocalizedMessage(), Snackbar.LENGTH_LONG);
                    }
                });
            }
        });
    }
}
