package com.springboot.autowiki.dto;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;

public class CarSortingRequest {

    @NotBlank
    @Pattern(regexp = "Manufacturer|DriveWheelsConfiguration|FuelType|GearType|BodyType|EngineDisplacementBetween" +
            "|EngineTorqueBetween|ProductionStartYearBetween|ProductionEndYearBetween|PriceBetween|NumberOfSeatsBetween" +
            "|EnginePowerBetween|WheelbaseBetween|WeightBetween|AccelerationToHundredBetween|WeightPerHPBetween")
    private String sortingOption;

    @Nullable
    @Positive(message = "The value must be greater than zero")
    private BigDecimal minimumValue;

    @Nullable
    @Positive(message = "The value must be greater than zero")
    private BigDecimal maximumValue;

}
