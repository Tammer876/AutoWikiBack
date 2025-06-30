package com.springboot.autowiki.repository;


import com.springboot.autowiki.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByModel(String model);

    boolean existsByModel(String model);

    List<Car> findAllByManufacturerOrderByProductionStartYearDesc(String manufacturer);
    List<Car> findAllByDriveWheelsConfiguration(String driveWheelsConfiguration);
    List<Car> findAllByFuelType(String fuelType);
    List<Car> findAllByGearType(String gearType);
    List<Car> findAllByBodyType(String bodyType);

    List<Car> findByEngineDisplacementBetweenOrderByEngineDisplacementDesc(Integer min, Integer max);
    List<Car> findByEngineTorqueBetweenOrderByEngineTorqueDesc(Integer min, Integer max);
    List<Car> findByProductionStartYearBetweenOrderByProductionStartYearDesc(Integer min, Integer max);
    List<Car> findByProductionEndYearBetweenOrderByProductionEndYearDesc(Integer min, Integer max);
    List<Car> findByPriceBetweenOrderByPriceDesc(Integer min, Integer max);
    List<Car> findByNumberOfSeatsBetweenOrderByNumberOfSeatsDesc(Integer min, Integer max);
    List<Car> findByEnginePowerBetweenOrderByEnginePowerDesc(Integer min, Integer max);
    List<Car> findByWheelbaseBetweenOrderByWheelbaseDesc(Integer min, Integer max);
    List<Car> findByWeightBetweenOrderByWeightDesc(Integer min, Integer max);
    List<Car> findByAccelerationToHundredBetweenOrderByAccelerationToHundredDesc(BigDecimal min, BigDecimal max);
    List<Car> findByWeightPerHPBetweenOrderByWeightPerHPDesc(BigDecimal min, BigDecimal max);


}
