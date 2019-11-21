package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepository repository;

    public List<City> getCities(){
        return repository.findAll();
    }

    public City save(City city){
        repository.save(city);
        return city;
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public List<City> saveAll(List<City> list){
        return repository.saveAll(list);
    }
}
