package com.springboot.autowiki.dto;

import java.util.List;
import com.springboot.autowiki.model.Car;
import jakarta.validation.constraints.NotBlank;


public class CarListingResponse {

    @NotBlank
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
