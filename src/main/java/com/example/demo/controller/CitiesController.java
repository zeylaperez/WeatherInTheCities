package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.List;


@RequestMapping("cities")
@RestController
public class CitiesController {

    @Autowired
    private CitiesService service;


    @GetMapping("")
    public ModelAndView selectAll(){
        ModelAndView modelAndView= new ModelAndView();
        List<City> cities = service.getCities();
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
    public String save(City city){
        service.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/cities";
    }
}
