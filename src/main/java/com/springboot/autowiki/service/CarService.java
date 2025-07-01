package com.springboot.autowiki.service;

import com.springboot.autowiki.exception.CarAlreadyExistsException;
import com.springboot.autowiki.exception.ElectricCarWithEngineDisplacementException;
import com.springboot.autowiki.exception.RegularCarWithoutEngineDisplacementException;
import com.springboot.autowiki.model.Car;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import com.springboot.autowiki.repository.CarRepository;

import java.security.InvalidParameterException;
import java.util.List;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
                sortedCars = switch (sortingOption) {
                    case "Manufacturer" -> carRepository.findAllByManufacturerOrderByProductionStartYearDesc(stringValue);
                    case "DriveWheelsConfiguration" -> carRepository.findAllByDriveWheelsConfiguration(stringValue);
                    case "FuelType" -> carRepository.findAllByFuelType(stringValue);
                    case "GearType" -> carRepository.findAllByGearType(stringValue);
                    case "BodyType" -> carRepository.findAllByBodyType(stringValue);
                    default -> sortedCars;
                };
            }
            else throw new InvalidParameterException("The search parameter for manufacturer/drive wheels configuration/fuel type/gear type/body type cannot be empty.");
        }
        else if (!(minValue).equals(null) && !(maxValue).equals(null)) {
            sortedCars = switch (sortingOption) {
                case "EngineDisplacementBetween" ->
                        carRepository.findByEngineDisplacementBetweenOrderByEngineDisplacementDesc(minValue.intValue(), maxValue.intValue());
                case "EngineTorqueBetween" ->
                        carRepository.findByEngineTorqueBetweenOrderByEngineTorqueDesc(minValue.intValue(), maxValue.intValue());
                case "ProductionStartYearBetween" ->
                        carRepository.findByProductionStartYearBetweenOrderByProductionStartYearDesc(minValue.intValue(), maxValue.intValue());
                case "ProductionEndYearBetween" ->
                        carRepository.findByProductionEndYearBetweenOrderByProductionEndYearDesc(minValue.intValue(), maxValue.intValue());
                case "PriceBetween" -> carRepository.findByPriceBetweenOrderByPriceDesc(minValue.intValue(), maxValue.intValue());
                case "NumberOfSeatsBetween" ->
                        carRepository.findByNumberOfSeatsBetweenOrderByNumberOfSeatsDesc(minValue.intValue(), maxValue.intValue());
                case "EnginePowerBetween" ->
                        carRepository.findByEnginePowerBetweenOrderByEnginePowerDesc(minValue.intValue(), maxValue.intValue());
                case "WheelbaseBetween" ->
                        carRepository.findByWheelbaseBetweenOrderByWheelbaseDesc(minValue.intValue(), maxValue.intValue());
                case "WeightBetween" -> carRepository.findByWeightBetweenOrderByWeightDesc(minValue.intValue(), maxValue.intValue());
                case "AccelerationToHundredBetween" ->
                        carRepository.findByAccelerationToHundredBetweenOrderByAccelerationToHundredDesc(minValue, maxValue);
                case "WeightPerHPBetween" -> carRepository.findByWeightPerHPBetweenOrderByWeightPerHPDesc(minValue, maxValue);
                default -> sortedCars;
            };
        }
        else throw new InvalidParameterException("The minimum/maximum values for searching cannot be null.");

        return sortedCars;
    }

}
