package com.springboot.autowiki.dto;


import com.springboot.autowiki.model.Car;

import java.util.List;

public class CarSortingResponse {
    private List<Car> sortedCars;

    public CarSortingResponse(List<Car> sortedCars) {
        this.sortedCars = sortedCars;
    }

    public List<Car> getSortedCars() {
        return sortedCars;
    }

    public void setSortedCars(List<Car> sortedCars) {
        this.sortedCars = sortedCars;
    }
}
