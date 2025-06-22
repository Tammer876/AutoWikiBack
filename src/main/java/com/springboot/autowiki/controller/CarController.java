package com.springboot.autowiki.controller;

import com.springboot.autowiki.dto.CarListingResponse;
import com.springboot.autowiki.dto.CarSortingRequest;
import com.springboot.autowiki.dto.CarSortingResponse;
import com.springboot.autowiki.repository.CarRepository;
import com.springboot.autowiki.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Car sorting", description = "Methods for getting cars from the database.")
@RestController
@RequestMapping("/api")
public class CarController {

    private final CarRepository carRepository;
    private final CarService carService;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.carService = new CarService(carRepository);
    }

    @Operation(description = "Gets all cars in the database.")
    @PostMapping("/carlist")
    public CarListingResponse getAllCars() {
        return new CarListingResponse(carRepository.findAll());
    }

    @Operation(description = "Searches for cars by specs.\n String value for searching with such options as Manufacturer," +
            " DriveWheelsConfiguration, FuelType, GearType, BodyType.\n \n minValue and maxValue are for searching with such options as EngineDisplacementBetween," +
            " EngineTorqueBetween, ProductionStartYearBetween, ProductionEndYearBetween, PriceBetween, NumberOfSeatsBetween," +
            " EnginePowerBetween, WheelbaseBetween, WeightBetween, AccelerationToHundredBetween, WeightPerHPBetween.\n")
    @PostMapping("/sortcars")
    public CarSortingResponse sortCars(@Valid @RequestBody CarSortingRequest request) {
        return new CarSortingResponse(carService.sortCars(request.getSortingOption(), request.getStringValue(),
                request.getMinimumValue(), request.getMaximumValue()));
    }

}
