package com.gox.parking.finder.api.service.impl;

import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.exception.CityNotSupportedException;
import com.gox.parking.finder.api.exception.NoParkingFoundException;
import com.gox.parking.finder.api.service.BordeauxParkingService;
import com.gox.parking.finder.api.service.CityParkingService;
import com.gox.parking.finder.api.service.ParkingService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service to dispatch to the dedicated city parking service
 */
@Service
public class ParkingServiceImpl implements ParkingService {

    private static final String BORDEAUX = "BORDEAUX";

    private Map<String, CityParkingService> cityParkingServiceMap = new HashMap<>();

    public ParkingServiceImpl(BordeauxParkingService bordeauxParkingService) {
        cityParkingServiceMap.put(BORDEAUX, bordeauxParkingService);
    }

    private CityParkingService getCityParkingService(String city) throws CityNotSupportedException {
        CityParkingService cityParkingService = cityParkingServiceMap.get(city.toUpperCase());
        if(cityParkingService == null){
            throw new CityNotSupportedException(city);
        }
        return cityParkingService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ParkingDto> findAllParkings(String city) throws CityNotSupportedException, NoParkingFoundException {
        List<ParkingDto> parkingDtos = getCityParkingService(city).findAllParkings();
        if(parkingDtos.isEmpty()){
            throw new NoParkingFoundException();
        }
        return parkingDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ParkingDto> findAllParkingNear(String city, float latitude, float longitude, int distance) throws CityNotSupportedException, NoParkingFoundException {
        List<ParkingDto> parkingDtos = getCityParkingService(city).findAllParkingNear(latitude, longitude, distance);
        if(parkingDtos.isEmpty()){
            throw new NoParkingFoundException();
        }
        return parkingDtos;
    }
}
