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

    @NotBlank(message = "Brand is required")
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

    @NotBlank(message = "Engine power is required")
    @Positive(message = "The value must be greater than zero")
    private Integer enginePower;

    @NotBlank(message = "Engine torque is required")
    @Positive(message = "The value must be greater than zero")
    private Integer engineTorque;

    @NotBlank(message = "Production start year is required")
    @Digits(fraction = 0, integer = 4, message = "Must contain 4 digits only")
    @Positive(message = "The value must be greater than zero")
    private Integer productionStartYear;

    @Positive(message = "The value must be greater than zero")
    @Nullable
    @Digits(fraction = 0, integer = 4, message = "Must contain 4 digits only")
    private Integer productionEndYear;

    @NotBlank(message = "Price is required")
    @Positive(message = "The value must be greater than zero")
    private Integer price;

    @NotBlank(message = "Number of seats value is required")
    @Positive(message = "The value must be greater than zero")
    private Integer numberOfSeats;

    @NotBlank(message = "Body type is required")
    private String bodyType;

    @NotBlank(message = "Number of doors value is required")
    @Positive(message = "The value must be greater than zero")
    private Integer numberOfDoors;

    @NotBlank(message = "Length is required")
    @Positive(message = "The value must be greater than zero")
    private Integer length;

    @NotBlank(message = "Width is required")
    @Positive(message = "The value must be greater than zero")
    private Integer width;

    @NotBlank(message = "Height is required")
    @Positive(message = "The value must be greater than zero")
    private Integer height;

    @NotBlank(message = "Wheelbase is required")
    @Positive(message = "The value must be greater than zero")
    private Integer wheelbase;

    @NotBlank(message = "Weight is required")
    @Positive(message = "The value must be greater than zero")
    private Integer weight;

    @NotBlank(message = "Acceleration to 100km/h is required")
    @Positive(message = "The value must be greater than zero")
    BigDecimal accelerationToHundred;

    @NotBlank(message = "Top speed is required")
    @Positive(message = "The value must be greater than zero")
    private Integer topSpeed;

    @NotBlank(message = "Drive wheels configuration is required")
    private String driveWheelsConfiguration;

    @NotBlank(message = "Weight to horsepower ratio is required")
    @Positive(message = "The value must be greater than zero")
    private BigDecimal weightPerHP;

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

    public BigDecimal getWeightPerHP() {
        return weightPerHP;
    }

    public void setWeightPerHP(BigDecimal weightPerHP) {
        this.weightPerHP = weightPerHP;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
