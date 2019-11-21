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
    @Value("${APPID}")
    private String APPID;

    public Forecast getWeather(String id){
        String url=resource+"?id="+id+"&APPID="+APPID;
        Forecast forecast = restTemplate.getForObject(url, Forecast.class);
        return forecast;
    }

}
