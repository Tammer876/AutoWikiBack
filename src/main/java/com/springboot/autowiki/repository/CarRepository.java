package com.springboot.autowiki.repository;


import com.springboot.autowiki.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> FindByModel(String model);
    Optional<Car> FindByManufacturer(String manufacturer);
    Optional<Car> FindByDriveWheelsConfiguration(String driveWheelsConfiguration);
    Optional<Car> FindByFuelType(String fuelType);
    Optional<Car> FindByGearType(String gearType);
    Optional<Car> FindByBodyType(String bodyType);

    boolean ExistsByModel(String model);

    List<Car> FindByEngineDisplacementBetween(Integer min, Integer max);
    List<Car> FindByEngineTorqueBetween(Integer min,  Integer max);
    List<Car> FindByProductionYearBetween(Integer min,  Integer max);
    List<Car> FindByPriceBetween(Integer min,  Integer max);
    List<Car> FindByNumberOfSeatsBetween(Integer min,  Integer max);
    List<Car> FindByEnginePowerBetween(Integer min, Integer max);
    List<Car> FindByDimensionBetween(Integer min,  Integer max);
    List<Car> FindByWheelBaseBetween(Integer min,  Integer max);
    List<Car> FindByWeightBetween(Integer min,  Integer max);
    List<Car> FindByAccelerationToHundredBetween(Integer min,  Integer max);
    List<Car> FindTopSpeedByBetween(Integer min,  Integer max);
    List<Car> FindByWeightPerHpBetween(Integer min,  Integer max);


}
