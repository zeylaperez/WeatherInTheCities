package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CitiesController {

    @Autowired
    private CitiesRepository repository;

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

//    @PutMapping("/update/{id}")
//    public void update(@PathVariable String id, @RequestBody City city) {
//        City oldCity = repository.findById(id).get();
//        if(city.getName() != null) {
//            oldCity.setName(city.getName());
//        }
//        repository.save(oldCity);
//    }

}
