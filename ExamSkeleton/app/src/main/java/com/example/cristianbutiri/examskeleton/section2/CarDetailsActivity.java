package com.example.cristianbutiri.examskeleton.section2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.cristianbutiri.examskeleton.R;
import com.example.cristianbutiri.examskeleton.controller.CarController;
import com.example.cristianbutiri.examskeleton.model.Car;
import com.example.cristianbutiri.examskeleton.repository.CarRepository;

public class CarDetailsActivity extends AppCompatActivity {
    CarController carController;
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CarRepository carRepository = (CarRepository) getIntent().getExtras().getSerializable("repository");
        carController = new CarController(this, carRepository, getParent().findViewById(R.id.parent_layout), null);

        car = (Car) getIntent().getExtras().getSerializable("car");

        ((EditText) findViewById(R.id.details_car_name)).setText(car.getName());
        ((EditText) findViewById(R.id.details_car_quantity)).setText(car.getQuantity().toString());
        ((EditText) findViewById(R.id.details_car_type)).setText(car.getType());
        ((EditText) findViewById(R.id.details_car_status)).setText(car.getStatus());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.remove_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carController.deleteCar(car);
                finish();
            }
        });
    }
}
