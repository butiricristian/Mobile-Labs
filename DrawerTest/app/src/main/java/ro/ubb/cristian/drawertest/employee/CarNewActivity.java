package ro.ubb.cristian.drawertest.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ro.ubb.cristian.drawertest.MainActivity;
import ro.ubb.cristian.drawertest.R;
import ro.ubb.cristian.drawertest.controller.CarController;
import ro.ubb.cristian.drawertest.model.car.Car;
import ro.ubb.cristian.drawertest.repository.CarRepository;

/**
 * Created by crist on 29-Jan-18.
 */

public class CarNewActivity extends AppCompatActivity {

    private Car currentCar;
    private CarRepository carRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        currentCar = new Car();
        carRepository = (CarRepository) getIntent().getSerializableExtra("repository");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(CarNewFragment.CAR,
                    currentCar);
            arguments.putSerializable("repository", carRepository);
            CarNewFragment fragment = new CarNewFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.car_new_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
