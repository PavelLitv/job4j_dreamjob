package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.repository.MemoryCityRepository;

import java.util.Collection;

@Service
public class SimpleCityService implements CityService {

    private final MemoryCityRepository cityRepository;

    public SimpleCityService(MemoryCityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Collection<City> findAll() {
        return cityRepository.findAll();
    }
}
