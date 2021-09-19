package com.gox.parking.finder.api.service;

import com.gox.parking.finder.api.dto.ParkingDto;

import java.util.List;

/**
 * Service interface for a specific city
 */
public interface CityParkingService {

    /**
     * Find all city parkings
     * @return list of parking dto
     */
    List<ParkingDto> findAllParkings();

    /**
     * Find all city parkings around a given area defined with a center (latitude/longitude) and a distance in meters
     * @param latitude the search center latitude
     * @param longitude the search center longitude
     * @param distance the search distance in meters from the center point defined by latitude/longitude
     * @return list of parking dto
     */
    List<ParkingDto> findAllParkingNear(float latitude, float longitude, int distance);

}
