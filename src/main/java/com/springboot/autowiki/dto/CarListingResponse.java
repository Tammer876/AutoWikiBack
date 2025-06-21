package com.springboot.autowiki.dto;

import java.util.List;
import com.springboot.autowiki.model.Car;


public class CarListingResponse {

    private List<Car> cars;

    public CarListingResponse(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
