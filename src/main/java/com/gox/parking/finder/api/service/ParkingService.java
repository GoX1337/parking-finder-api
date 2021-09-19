package com.gox.parking.finder.api.service;

import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.exception.CityNotSupportedException;
import com.gox.parking.finder.api.exception.NoParkingFoundException;

import java.util.List;

public interface ParkingService {

    /**
     * Find all parkings for a given city
     * @param city (ex: bordeaux)
     * @return A list of parking dto to return to the client
     * @throws CityNotSupportedException if the city is not supported by the api
     */
    List<ParkingDto> findAllParkings(String city) throws CityNotSupportedException, NoParkingFoundException;


    /**
     * Find all city parkings around a given area defined with a center (latitude/longitude) and a distance in meters
     * @param latitude the search center latitude
     * @param longitude the search center longitude
     * @param distance the search distance in meters from the center point defined by latitude/longitude
     * @return list of parking dto
     */
    List<ParkingDto> findAllParkingNear(String city, float latitude, float longitude, int distance) throws CityNotSupportedException, NoParkingFoundException;

}
