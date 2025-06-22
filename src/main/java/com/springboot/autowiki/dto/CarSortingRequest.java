package com.springboot.autowiki.dto;


import io.swagger.v3.oas.annotations.Parameter;
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

    @Parameter(description = "String value for searching with such options as Manufacturer, DriveWheelsConfiguration," +
            " FuelType, GearType, BodyType.")
    @Nullable
    private String stringValue;

    @Parameter(description = "Minimum Int/BigDecimal value for searching with such options as EngineDisplacementBetween," +
            " EngineTorqueBetween, ProductionStartYearBetween, ProductionEndYearBetween, PriceBetween, NumberOfSeatsBetween," +
            " EnginePowerBetween,WheelbaseBetween, WeightBetween, AccelerationToHundredBetween, WeightPerHPBetween.")
    @Nullable
    @Positive(message = "The value must be greater than zero")
    private BigDecimal minimumValue;

    @Parameter(description = "Maximum Int/BigDecimal value for searching with such options as EngineDisplacementBetween," +
            " EngineTorqueBetween, ProductionStartYearBetween, ProductionEndYearBetween, PriceBetween, NumberOfSeatsBetween," +
            " EnginePowerBetween,WheelbaseBetween, WeightBetween, AccelerationToHundredBetween, WeightPerHPBetween.")
    @Nullable
    @Positive(message = "The value must be greater than zero")
    private BigDecimal maximumValue;

    public String getSortingOption() {
        return sortingOption;
    }

    public void setSortingOption(String sortingOption) {
        this.sortingOption = sortingOption;
    }

    @Nullable
    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(@Nullable String stringValue) {
        this.stringValue = stringValue;
    }

    @Nullable
    public BigDecimal getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(@Nullable BigDecimal minimumValue) {
        this.minimumValue = minimumValue;
    }

    @Nullable
    public BigDecimal getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(@Nullable BigDecimal maximumValue) {
        this.maximumValue = maximumValue;
    }

}
