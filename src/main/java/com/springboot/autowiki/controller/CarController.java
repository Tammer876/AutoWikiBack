package com.springboot.autowiki.controller;

import com.springboot.autowiki.model.Car;
import com.springboot.autowiki.repository.CarRepository;

import java.util.List;


public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
