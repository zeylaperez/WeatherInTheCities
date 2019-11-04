package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    @JsonProperty("weather")
    private List<Weather> weathers;
    private Main main;
    @JsonProperty("sys")
    private System system;
    private String name;

    public Forecast(){}

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    @Override
    public String toString(){
        return "Forecast{"+
                "weathers='" +weathers+
                "main=" + main +
                ", system=" + system +
                ", name=" + name +
                '}';
    }
}
