package com.qa25.trips.model;

public class Cities {
    private String fromCity;
    private String toCity;

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public Cities setFromCity(String fromCity) {
     //   if(fromCity != null) {
            this.fromCity = fromCity;
     //   }else{
      //      this.fromCity = " ";
      //  }
        return this;
    }

    public Cities setToCity(String toCity) {
        if(toCity != null) {
            this.toCity = toCity;
        }else{
            this.toCity = " ";
        }
        return this;
    }

    @Override
    public String toString() {
        return "Cities names: " +
                "FROM city: " + this.fromCity +
                ", TO city: " + this.toCity;
    }
}
