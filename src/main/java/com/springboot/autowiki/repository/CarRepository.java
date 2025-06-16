package com.springboot.autowiki.repository;


import com.springboot.autowiki.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByModel(String model);
    Optional<Car> findByManufacturer(String manufacturer);
    Optional<Car> findByDriveWheelsConfiguration(String driveWheelsConfiguration);
    Optional<Car> findByFuelType(String fuelType);
    Optional<Car> findByGearType(String gearType);
    Optional<Car> findByBodyType(String bodyType);

    boolean existsByModel(String model);

    List<Car> findByEngineDisplacementBetween(Integer min, Integer max);
    List<Car> findByEngineTorqueBetween(Integer min,  Integer max);
    List<Car> findByProductionStartYearBetween(Integer min, Integer max);
    List<Car> findByProductionEndYearBetween(Integer min, Integer max);
    List<Car> findByPriceBetween(Integer min,  Integer max);
    List<Car> findByNumberOfSeatsBetween(Integer min,  Integer max);
    List<Car> findByEnginePowerBetween(Integer min, Integer max);
    List<Car> findByWheelbaseBetween(Integer min, Integer max);
    List<Car> findByWeightBetween(Integer min,  Integer max);
    List<Car> findByAccelerationToHundredBetween(BigDecimal min, Integer max);
    List<Car> findByWeightPerHpBetween(BigDecimal min, BigDecimal max);


}
