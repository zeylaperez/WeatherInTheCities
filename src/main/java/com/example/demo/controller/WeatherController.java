package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Forecast;
import com.example.demo.repository.CitiesRepository;
import com.example.demo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    private ApiService apiService;

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

    //Empleo de la API
    @GetMapping("/weatherInTheCity/{id}")
    public ModelAndView weatherInTheCity(@PathVariable String id) {
        ModelAndView view= new ModelAndView();
        Forecast forecast=apiService.getWeather(id);
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

    //Metodos REST:
    @GetMapping("cities")
    public ModelAndView selectAll(){
        ModelAndView modelAndView= new ModelAndView();
        List<City> cities = repository.findAll();
        modelAndView.addObject("cities");
        modelAndView.addObject("cities", cities);
        modelAndView.setViewName("cities");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView view= new ModelAndView();
        City city=new City();
        view.addObject("cityForm");
        view.addObject("city",city);
        view.setViewName("cityForm");
        return  view;
    }

    @PostMapping("/save")
    public ModelAndView save(City city){
        repository.save(city);
        return selectAll();
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable String id) {
        City deleteCity = repository.findById(id).get();
        repository.delete(deleteCity);
        return selectAll();
    }
}
