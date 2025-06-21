package com.springboot.autowiki.service;

import com.springboot.autowiki.exception.CarAlreadyExistsException;
import com.springboot.autowiki.exception.ElectricCarWithEngineDisplacementException;
import com.springboot.autowiki.exception.RegularCarWithoutEngineDisplacementException;
import com.springboot.autowiki.model.Car;
import org.springframework.stereotype.Service;
import com.springboot.autowiki.repository.CarRepository;
import java.util.List;
import java.math.BigDecimal;


@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(String model, String manufacturer, String gearType, String fuelType, Integer engineDisplacement,
                         Integer enginePower, Integer engineTorque, Integer productionStartYear, Integer productionEndYear,
                         Integer price, Integer numberOfSeats, String bodyType, Integer numberOfDoors, Integer length,
                         Integer width, Integer height, Integer wheelbase, Integer weight, BigDecimal accelerationToHundred,
                         Integer topSpeed, String driveWheelsConfiguration, BigDecimal weightPerHP, String imageUrl) {

        if (carRepository.existsByModel(model)) {
            throw new CarAlreadyExistsException("Such car model already exists in the database.");
        }

        if (!"Electric".equals(fuelType) && engineDisplacement == null) {
            throw new RegularCarWithoutEngineDisplacementException("The engine displacement cannot be null for non electric cars.");
        } else if ("Electric".equals(fuelType) && engineDisplacement != null) {
            throw new ElectricCarWithEngineDisplacementException("Invalid engine displacement value for an electric car.");
        }
        else if (fuelType.equals("Electric") && (engineDisplacement != null)) {
            throw new ElectricCarWithEngineDisplacementException("Invalid engine displacement value for an electric car.");
        }

        Car car = new Car();
        car.setModel(model);
        car.setManufacturer(manufacturer);
        car.setGearType(gearType);
        car.setFuelType(fuelType);
        car.setEngineDisplacement(engineDisplacement);
        car.setEnginePower(enginePower);
        car.setEngineTorque(engineTorque);
        car.setProductionStartYear(productionStartYear);
        car.setProductionEndYear(productionEndYear);
        car.setPrice(price);
        car.setNumberOfSeats(numberOfSeats);
        car.setBodyType(bodyType);
        car.setNumberOfDoors(numberOfDoors);
        car.setLength(length);
        car.setWidth(width);
        car.setHeight(height);
        car.setWheelbase(wheelbase);
        car.setWeight(weight);
        car.setAccelerationToHundred(accelerationToHundred);
        car.setTopSpeed(topSpeed);
        car.setDriveWheelsConfiguration(driveWheelsConfiguration);
        car.setWeightPerHP(weightPerHP);
        car.setImageUrl(imageUrl);

        Car savedCar;
        savedCar = carRepository.save(car);

        return savedCar;
    }

    public List<Car> sortCars(String sortingOption, @Nullable String stringValue, @Nullable BigDecimal minValue,
                              @Nullable BigDecimal maxValue) {
        List<Car> sortedCars = null;
        Pattern patternForStringSpecs = Pattern.compile("Manufacturer|DriveWheelsConfiguration|FuelType|GearType|BodyType");
        Matcher matcherForStringSpecs = patternForStringSpecs.matcher(sortingOption);

        if  (matcherForStringSpecs.find()) {
            if (!(stringValue.isBlank())) {
                switch (sortingOption) {
                    case "Manufacturer":
                        sortedCars = carRepository.findAllByManufacturer(stringValue);
                        break;
                    case "DriveWheelsConfiguration":
                        sortedCars = carRepository.findAllByDriveWheelsConfiguration(stringValue);
                        break;
                    case "FuelType":
                        sortedCars = carRepository.findAllByFuelType(stringValue);
                        break;
                    case "GearType":
                        sortedCars = carRepository.findAllByGearType(stringValue);
                        break;
                    case "BodyType":
                        sortedCars = carRepository.findAllByBodyType(stringValue);
                        break;
                }
            }
            else throw new InvalidParameterException("The search parameter for manufacturer/drive wheels configuration/fuel type/gear type/body type cannot be empty.");
        }
        else if (!(minValue).equals(null) && !(maxValue).equals(null)) {
            switch (sortingOption) {
                case "EngineDisplacementBetween":
                    sortedCars = carRepository.findByEngineDisplacementBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "EngineTorqueBetween":
                    sortedCars = carRepository.findByEngineTorqueBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "ProductionStartYearBetween":
                    sortedCars = carRepository.findByProductionStartYearBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "ProductionEndYearBetween":
                    sortedCars = carRepository.findByProductionEndYearBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "PriceBetween":
                    sortedCars = carRepository.findByPriceBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "NumberOfSeatsBetween":
                    sortedCars = carRepository.findByNumberOfSeatsBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "EnginePowerBetween":
                    sortedCars = carRepository.findByEnginePowerBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "WheelbaseBetween":
                    sortedCars = carRepository.findByWheelbaseBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "WeightBetween":
                    sortedCars = carRepository.findByWeightBetween(minValue.intValue(), maxValue.intValue());
                    break;
                case "AccelerationToHundredBetween":
                    sortedCars = carRepository.findByAccelerationToHundredBetween(minValue, maxValue);
                    break;
                case "WeightPerHPBetween":
                    sortedCars = carRepository.findByWeightPerHPBetween(minValue, maxValue);
                    break;
            }
        }
        else throw new InvalidParameterException("The minimum/maximum values for searching cannot be null.");

        return sortedCars;
    }

}
// gear_type fuel_type engine_displacement engine_power engine_torque production_start_year production_end_year price number_of_seats body_type number_of_doors length width
//height wheelbase weight
//acceleration_to_hundred top_speed drive_wheels_configuration weight perhp image_url