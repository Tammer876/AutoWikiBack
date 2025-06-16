package com.springboot.autowiki.dto;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CarCreationRequest {
    @NotBlank(message = "Brand is required")
    private String manufacturer;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Gear is required")
    private String gearType;


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


    private String driveWheelsConfiguration;


    @Positive(message = "The value must be greater than zero")
    private BigDecimal weightPerHP;


    private String imageUrl;

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
