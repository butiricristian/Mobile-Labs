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

public class NewCarActivity extends AppCompatActivity {
    private Car currentCar;
    EditText etCarName, etCarType, etCarQuantity, etCarStatus;
    CarController carController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentCar = new Car();

        etCarName = (EditText) findViewById(R.id.new_car_name);
        etCarQuantity = (EditText) findViewById(R.id.new_car_quantity);
        etCarStatus = (EditText) findViewById(R.id.new_car_status);
        etCarType = (EditText) findViewById(R.id.new_car_type);

        final CarRepository carRepository = (CarRepository) getIntent().getExtras().getSerializable("repository");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carController = new CarController(view.getContext(), carRepository, view, null);

                currentCar.setName(etCarName.getText().toString());
                currentCar.setQuantity(Integer.valueOf(etCarQuantity.getText().toString()));
                currentCar.setStatus(etCarStatus.getText().toString());
                currentCar.setType(etCarType.getText().toString());

                carController.addCar(currentCar);
                finish();
            }
        });
    }
}
