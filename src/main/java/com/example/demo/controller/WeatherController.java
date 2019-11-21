package com.example.demo.controller;

import com.example.demo.exceptions.NoConnectionException;
import com.example.demo.model.City;
import com.example.demo.model.Forecast;
import com.example.demo.service.ApiService;
import com.example.demo.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private CitiesService service;

    //Vista inicial
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView= new ModelAndView();
        List<City> cities= service.getCities();
        modelAndView.addObject("index");
        modelAndView.addObject("cities", cities);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    //Empleo de la API
    @GetMapping("/weatherInTheCity/{id}")
    public ModelAndView weatherInTheCity(@PathVariable String id) {
        ModelAndView view= new ModelAndView();
        try {
            Forecast forecast = apiService.getWeather(id);
            double Ctemp = forecast.getMain().getTemp() - 273.15;
            int valor = (int) Ctemp;
            double decimal = Ctemp - valor;
            if (decimal >= 0.5) {
                valor += 1;
            }
            forecast.getMain().setTemp(valor);
            view.addObject("weatherInTheCity");
            view.addObject("weather", forecast);
            view.setViewName("weatherInTheCity");
        }catch (Exception e ){
               throw new NoConnectionException();
        }
        return view;
    }

}
