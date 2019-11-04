package com.example.demo.repository;

import com.example.demo.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CitiesRepository extends MongoRepository<City,String> {
}
