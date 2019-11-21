package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CitiesRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class CitiesServiceTest {

    @TestConfiguration
    static class CitiesServiceTestContextConfig{
        @Bean
        public CitiesService citiesService(){
            return new CitiesService();
        }
    }

    @Autowired
    private CitiesService service;

    @MockBean
    private CitiesRepository repository;

    private List<City> cityList;
    private City city1;
    private City city2;
    private City city3;

    @Before
    public void setUp(){
        city1=new City("3451190","Rio de Janeiro");
        city2=new City("6167865","Toronto");
        city3=new City("5128638","New York");

        cityList= Arrays.asList(city1, city2, city3);
        repository.deleteAll();
        repository.saveAll((Iterable<City>)cityList);
    }

    @Test
    public void getCitiesTest(){
        Mockito.when(repository.findAll()).thenReturn(cityList);
        List<City> found=service.getCities();
        assertEquals(found, cityList);
    }

    @Test
    public void saveAll(){
        City  city4= new City("4164138", "Miami");
        City  city5= new City("4164138", "Miami");
        City  city6= new City("4164138", "Miami");
        List<City> list=Arrays.asList(city4,city5,city6);

        Mockito.when(repository.saveAll(list)).thenReturn(list);
        List<City> saved=service.saveAll(list);
        assertEquals(list,saved);
    }

    @Test
    public void save(){
        City  city4= new City("4164138", "Miami");
        Mockito.when(repository.save(city4)).thenReturn(city4);
        City saved=service.save(city4);
        assertEquals(city4.getName(), saved.getName());
    }

    @Test
    public void delete(){
        List<City> nlist=Arrays.asList(city2, city3);
        service.delete("3451190");
        Mockito.when(repository.findAll()).thenReturn(nlist);
        nlist=service.getCities();
        assertNotEquals(cityList, nlist);
    }
}
