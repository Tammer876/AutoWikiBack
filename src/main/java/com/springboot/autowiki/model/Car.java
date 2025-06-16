package com.springboot.autowiki.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Gear is required")
    private String gearType;

    @NotBlank(message = "Fuel type is required")
    @Pattern(regexp = "Petrol|Diesel|Electric|Hybrid", message = "Fuel type must be either Petrol, Diesel, Electric, or Hybrid")
    private String fuelType;

    @Positive(message = "The value must be greater than zero")
    @Nullable
    private Integer engineDisplacement;


    @Positive(message = "The value must be greater than zero")
    private Integer enginePower;


    @Positive(message = "The value must be greater than zero")
    private Integer engineTorque;


    @Digits(fraction = 0, integer = 4, message = "Must contain 4 digits only")
    @Positive(message = "The value must be greater than zero")
    private Integer productionStartYear;

    @Positive(message = "The value must be greater than zero")
    @Nullable
    @Digits(fraction = 0, integer = 4, message = "Must contain 4 digits only")
    private Integer productionEndYear;


    @Positive(message = "The value must be greater than zero")
    private Integer price;


    @Positive(message = "The value must be greater than zero")
    private Integer numberOfSeats;

    @NotBlank(message = "Body type is required")
    private String bodyType;


    @Positive(message = "The value must be greater than zero")
    private Integer numberOfDoors;


    @Positive(message = "The value must be greater than zero")
    private Integer length;


    @Positive(message = "The value must be greater than zero")
    private Integer width;


    @Positive(message = "The value must be greater than zero")
    private Integer height;


    @Positive(message = "The value must be greater than zero")
    private Integer wheelbase;


    @Positive(message = "The value must be greater than zero")
    private Integer weight;


    @Positive(message = "The value must be greater than zero")
    BigDecimal accelerationToHundred;


    @Positive(message = "The value must be greater than zero")
    private Integer topSpeed;

    @NotBlank(message = "Drive wheels configuration is required")
    private String driveWheelsConfiguration;


    @Positive(message = "The value must be greater than zero")
    private BigDecimal weightPerHp;

    @NotBlank(message = "Image url is required")
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Nullable
    public Integer getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(@Nullable Integer engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public Integer getEngineTorque() {
        return engineTorque;
    }

    public void setEngineTorque(Integer engineTorque) {
        this.engineTorque = engineTorque;
    }

    public Integer getProductionStartYear() {
        return productionStartYear;
    }

    public void setProductionStartYear(Integer productionStartYear) {
        this.productionStartYear = productionStartYear;
    }

    @Nullable
    public Integer getProductionEndYear() {
        return productionEndYear;
    }

    public void setProductionEndYear(@Nullable Integer productionEndYear) {
        this.productionEndYear = productionEndYear;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWheelbase() {
        return wheelbase;
    }

    public void setWheelbase(Integer wheelbase) {
        this.wheelbase = wheelbase;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BigDecimal getAccelerationToHundred() {
        return accelerationToHundred;
    }

    public void setAccelerationToHundred(BigDecimal accelerationToHundred) {
        this.accelerationToHundred = accelerationToHundred;
    }

    public Integer getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(Integer topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getDriveWheelsConfiguration() {
        return driveWheelsConfiguration;
    }

    public void setDriveWheelsConfiguration(String driveWheelsConfiguration) {
        this.driveWheelsConfiguration = driveWheelsConfiguration;
    }

    public BigDecimal getWeightPerHp() {
        return weightPerHp;
    }

    public void setWeightPerHp(BigDecimal weightPerHp) {
        this.weightPerHp = weightPerHp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
