package com.springboot.autowiki.dto;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ArticleCreationRequest {

    @NotNull
    @Positive
    private Long carId;

    @NotBlank(message = "Manufacturer is required")
    private String proposedManufacturer;

    @NotBlank(message = "Model is required")
    private String proposedModel;

    @NotBlank(message = "Gear is required")
    private String proposedGearType;

    @NotBlank(message = "Fuel type is required")
    @Pattern(regexp = "Petrol|Diesel|Electric|Hybrid", message = "Fuel type must be either Petrol, Diesel, Electric, or Hybrid")
    private String proposedFuelType;

    @Positive(message = "The value must be greater than zero")
    @Nullable
    private Integer proposedEngineDisplacement;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedEnginePower;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedEngineTorque;


    @Digits(fraction = 0, integer = 4, message = "Must contain 4 digits only")
    @Positive(message = "The value must be greater than zero")
    private Integer proposedProductionStartYear;

    @Positive(message = "The value must be greater than zero")
    @Nullable
    @Digits(fraction = 0, integer = 4, message = "Must contain 4 digits only")
    private Integer proposedProductionEndYear;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedPrice;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedNumberOfSeats;

    @NotBlank(message = "Body type is required")
    private String proposedBodyType;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedNumberOfDoors;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedLength;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedWidth;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedHeight;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedWheelbase;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedWeight;


    @Positive(message = "The value must be greater than zero")
    private BigDecimal proposedAccelerationToHundred;


    @Positive(message = "The value must be greater than zero")
    private Integer proposedTopSpeed;

    @NotBlank(message = "Drive wheels configuration is required")
    private String proposedDriveWheelsConfiguration;


    @Positive(message = "The value must be greater than zero")
    private BigDecimal proposedWeightPerHP;

    @NotBlank(message = "Image url is required")
    private String proposedImageUrl;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getProposedManufacturer() {
        return proposedManufacturer;
    }

    public void setProposedManufacturer(String proposedManufacturer) {
        this.proposedManufacturer = proposedManufacturer;
    }

    public String getProposedModel() {
        return proposedModel;
    }

    public void setProposedModel(String proposedModel) {
        this.proposedModel = proposedModel;
    }

    public String getProposedGearType() {
        return proposedGearType;
    }

    public void setProposedGearType(String proposedGearType) {
        this.proposedGearType = proposedGearType;
    }

    public String getProposedFuelType() {
        return proposedFuelType;
    }

    public void setProposedFuelType(String proposedFuelType) {
        this.proposedFuelType = proposedFuelType;
    }

    @Nullable
    public Integer getProposedEngineDisplacement() {
        return proposedEngineDisplacement;
    }

    public void setProposedEngineDisplacement(@Nullable Integer proposedEngineDisplacement) {
        this.proposedEngineDisplacement = proposedEngineDisplacement;
    }

    public Integer getProposedEnginePower() {
        return proposedEnginePower;
    }

    public void setProposedEnginePower(Integer proposedEnginePower) {
        this.proposedEnginePower = proposedEnginePower;
    }

    public Integer getProposedEngineTorque() {
        return proposedEngineTorque;
    }

    public void setProposedEngineTorque(Integer proposedEngineTorque) {
        this.proposedEngineTorque = proposedEngineTorque;
    }

    public Integer getProposedProductionStartYear() {
        return proposedProductionStartYear;
    }

    public void setProposedProductionStartYear(Integer proposedProductionStartYear) {
        this.proposedProductionStartYear = proposedProductionStartYear;
    }

    @Nullable
    public Integer getProposedProductionEndYear() {
        return proposedProductionEndYear;
    }

    public void setProposedProductionEndYear(@Nullable Integer proposedProductionEndYear) {
        this.proposedProductionEndYear = proposedProductionEndYear;
    }

    public Integer getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(Integer proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public Integer getProposedNumberOfSeats() {
        return proposedNumberOfSeats;
    }

    public void setProposedNumberOfSeats(Integer proposedNumberOfSeats) {
        this.proposedNumberOfSeats = proposedNumberOfSeats;
    }

    public String getProposedBodyType() {
        return proposedBodyType;
    }

    public void setProposedBodyType(String proposedBodyType) {
        this.proposedBodyType = proposedBodyType;
    }

    public Integer getProposedNumberOfDoors() {
        return proposedNumberOfDoors;
    }

    public void setProposedNumberOfDoors(Integer proposedNumberOfDoors) {
        this.proposedNumberOfDoors = proposedNumberOfDoors;
    }

    public Integer getProposedLength() {
        return proposedLength;
    }

    public void setProposedLength(Integer proposedLength) {
        this.proposedLength = proposedLength;
    }

    public Integer getProposedWidth() {
        return proposedWidth;
    }

    public void setProposedWidth(Integer proposedWidth) {
        this.proposedWidth = proposedWidth;
    }

    public Integer getProposedHeight() {
        return proposedHeight;
    }

    public void setProposedHeight(Integer proposedHeight) {
        this.proposedHeight = proposedHeight;
    }

    public Integer getProposedWheelbase() {
        return proposedWheelbase;
    }

    public void setProposedWheelbase(Integer proposedWheelbase) {
        this.proposedWheelbase = proposedWheelbase;
    }

    public Integer getProposedWeight() {
        return proposedWeight;
    }

    public void setProposedWeight(Integer proposedWeight) {
        this.proposedWeight = proposedWeight;
    }

    public BigDecimal getProposedAccelerationToHundred() {
        return proposedAccelerationToHundred;
    }

    public void setProposedAccelerationToHundred(BigDecimal proposedAccelerationToHundred) {
        this.proposedAccelerationToHundred = proposedAccelerationToHundred;
    }

    public Integer getProposedTopSpeed() {
        return proposedTopSpeed;
    }

    public void setProposedTopSpeed(Integer proposedTopSpeed) {
        this.proposedTopSpeed = proposedTopSpeed;
    }

    public String getProposedDriveWheelsConfiguration() {
        return proposedDriveWheelsConfiguration;
    }

    public void setProposedDriveWheelsConfiguration(String proposedDriveWheelsConfiguration) {
        this.proposedDriveWheelsConfiguration = proposedDriveWheelsConfiguration;
    }

    public BigDecimal getProposedWeightPerHP() {
        return proposedWeightPerHP;
    }

    public void setProposedWeightPerHP(BigDecimal proposedWeightPerHP) {
        this.proposedWeightPerHP = proposedWeightPerHP;
    }

    public String getProposedImageUrl() {
        return proposedImageUrl;
    }

    public void setProposedImageUrl(String proposedImageUrl) {
        this.proposedImageUrl = proposedImageUrl;
    }
}
