package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Forecast;
import com.example.demo.repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CitiesRepository repository;

    //Vista inicial
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView= new ModelAndView();
        List<City> cities= repository.findAll();
        modelAndView.addObject("index");
        modelAndView.addObject("cities", cities);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/weatherInTheCity/{id}")
    public ModelAndView weatherInTheCity(@PathVariable String id) {
        ModelAndView view= new ModelAndView();
        String url= "http://api.openweathermap.org/data/2.5/weather?id="+id+"&APPID=e4f74e412176755b9b7ee10ece2da8dd";

        Forecast forecast = restTemplate.getForObject( url.trim(), Forecast.class);
        double Ctemp=forecast.getMain().getTemp()-273.15;
        int valor= (int) Ctemp;
        double decimal= Ctemp-valor;
        if(decimal>=0.5){
            valor+=1;
        }
        forecast.getMain().setTemp(valor);
        view.addObject("weatherInTheCity");
        view.addObject("weather", forecast);
        view.setViewName("weatherInTheCity");

        return view;
    }

}
