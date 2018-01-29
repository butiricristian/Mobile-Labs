package ro.ubb.cristian.drawertest;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import ro.ubb.cristian.drawertest.dummy.DummyContent;
import ro.ubb.cristian.drawertest.model.car.Car;

public class CarDetailFragment extends Fragment {

    public static final String CAR = "car";
    private Car car;

    public CarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(CAR)) {
            car = (Car)getArguments().getSerializable(CAR);

            final Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(car.getName());
            }

            FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //This will be the save button
                    car.setName(((EditText)getActivity().findViewById(R.id.details_car_name)).getText().toString());

                    Snackbar.make(view, car.getName(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.car_detail, container, false);

        if (car != null) {
            ((EditText) rootView.findViewById(R.id.details_car_name)).setText(car.getName());
            ((EditText) rootView.findViewById(R.id.details_car_quantity)).setText(car.getQuantity().toString());
            ((EditText) rootView.findViewById(R.id.details_car_type)).setText(car.getType());
            ((EditText) rootView.findViewById(R.id.details_car_status)).setText(car.getStatus());

//            ((TextView) rootView.findViewById(R.id.details_car_name_text)).setText(car.getName());
//            ((TextView) rootView.findViewById(R.id.details_car_quantity_text)).setText(car.getQuantity().toString());
//            ((TextView) rootView.findViewById(R.id.details_car_type_text)).setText(car.getType());
//            ((TextView) rootView.findViewById(R.id.details_car_status_text)).setText(car.getStatus());

        }

        return rootView;
    }
}
