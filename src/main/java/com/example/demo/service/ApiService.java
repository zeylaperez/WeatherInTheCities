package com.example.demo.service;

import com.example.demo.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${resource}")
    private String resource;

    public Forecast getWeather(String id){
        resource=resource+"?id="+id+"&APPID=e4f74e412176755b9b7ee10ece2da8dd";
        Forecast forecast = restTemplate.getForObject(resource, Forecast.class);
        return forecast;
    }
}
