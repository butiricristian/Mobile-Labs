package ro.ubb.cristian.drawertest.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ro.ubb.cristian.drawertest.model.car.Car;
import ro.ubb.cristian.drawertest.observer.Subject;

/**
 * Created by crist on 29-Jan-18.
 */

public class CarRepository extends Subject implements Serializable{
    private List<Car> cars;

    public CarRepository(){
        cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
