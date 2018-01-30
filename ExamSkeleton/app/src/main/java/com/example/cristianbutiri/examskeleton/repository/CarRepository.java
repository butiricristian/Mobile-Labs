package com.example.cristianbutiri.examskeleton.repository;

import com.example.cristianbutiri.examskeleton.model.Car;
import com.example.cristianbutiri.examskeleton.observer.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
