package com.example.demo.service;

import com.example.demo.model.Forecast;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ApiServiceTest {

    @TestConfiguration
    static class ApiServiceTestContextConfig{
        @Bean
        public ApiService apiService(){
            return new ApiService();
        }
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }
    }

    @Autowired
    private ApiService service;

    @Test
    public void getForecastTest(){
        String id="5128638";
        Forecast forecast =service.getWeather(id);
        assertNotNull(forecast);
    }

}
