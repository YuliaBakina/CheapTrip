package com.qa25.trips.model;

public class CityName {
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public CityName setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    @Override
    public String toString() {
        return "City name: " + this.cityName;
    }

}
