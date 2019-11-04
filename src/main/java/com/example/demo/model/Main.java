package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    private int temp;
    private int pressure;
    private int humidity;

    public Main(){}

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }


    @Override
    public String toString(){
        return "Main{"+
                "temp='" +temp+
                ", pressure=" +pressure+
                ", humidity=" +humidity+ '\'' +
                '}';
    }
}
