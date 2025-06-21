package com.springboot.autowiki.controller;

import com.springboot.autowiki.dto.CarListingResponse;
import com.springboot.autowiki.model.Car;
import com.springboot.autowiki.repository.CarRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PostMapping("/carlist")
    public CarListingResponse getAllCars() {
        return new CarListingResponse(carRepository.findAll());
    }
}
