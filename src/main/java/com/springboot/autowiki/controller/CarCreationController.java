package com.springboot.autowiki.controller;

import com.springboot.autowiki.model.Car;
import com.springboot.autowiki.repository.CarRepository;
import com.springboot.autowiki.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.springboot.autowiki.dto.CarCreationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Car creation", description = "Car creation")
@RestController
@RequestMapping("/api")
public class CarCreationController {

    private final CarService carService;

    public CarCreationController(CarRepository carRepository) {
        this.carService = new CarService(carRepository);
    }



    @Operation(summary = "Create a new car inside the database", description = "Creates a new car with all the specifications provided")
    @PostMapping("/newcar")
    public ResponseEntity<?> createNewCar(@Valid @RequestBody CarCreationRequest request) {
        Car createdCar = carService.createCar(
                request.getModel(),
                request.getManufacturer(),
                request.getGearType(),
                request.getFuelType(),
                request.getEngineDisplacement(),
                request.getEnginePower(),
                request.getEngineTorque(),
                request.getProductionStartYear(),
                request.getProductionEndYear(),
                request.getPrice(),
                request.getNumberOfSeats(),
                request.getBodyType(),
                request.getNumberOfDoors(),
                request.getLength(),
                request.getWidth(),
                request.getHeight(),
                request.getWheelbase(),
                request.getWeight(),
                request.getAccelerationToHundred(),
                request.getTopSpeed(),
                request.getDriveWheelsConfiguration(),
                request.getWeightPerHP(),
                request.getImageUrl()
        );
        return  ResponseEntity.ok("Car created successfully.");
        }


}
