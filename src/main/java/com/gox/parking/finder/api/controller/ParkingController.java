package com.gox.parking.finder.api.controller;

import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.exception.CityNotSupportedException;
import com.gox.parking.finder.api.exception.NoParkingFoundException;
import com.gox.parking.finder.api.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Parking finder REST API entry point
 */
@RestController
@RequestMapping("/api")
@Valid
@Slf4j
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    /**
     * Search and returned all parkings of the given city (url ex: /bordeaux/parking)
     * @param city (ex: bordeaux)
     * @return A list of parkings founds
     * @throws CityNotSupportedException if the city is not supported by the api
     */
    @GetMapping(value = "/{city}/parking", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all city parkings")
    public List<ParkingDto> parkings(@PathVariable String city) throws CityNotSupportedException, NoParkingFoundException {
        log.info("GET all {} parkings", city);
        List<ParkingDto> parkings = parkingService.findAllParkings(city);
        if(log.isDebugEnabled()){
            log.debug("{} parkings found", parkings.size());
        }
        return parkings;
    }

    /**
     * Search all parkings of the given city, in a zone defined by a center point (latitude/longitude) and a distance in meters
     * (url ex: /bordeaux/parking/near?latitude=44.827794&longitude=-0.689417&distance=1500)
     * @param city (ex: bordeaux)
     * @param latitude search position latitude
     * @param longitude search position longitude
     * @param distance search distance (in meters) from the point at latitude/longitude
     * @return The list of parkings found near the area defined in parameter
     * @throws CityNotSupportedException if the city is not supported by the api
     */
    @GetMapping(value = "/{city}/parking/near", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all city parkings around a point")
    public List<ParkingDto> parkingNear(@Parameter(description = "city name") @PathVariable String city,
                                        @Parameter(description = "latitude of search center point") @RequestParam float latitude,
                                        @Parameter(description = "longitude of search center point") @RequestParam float longitude,
                                        @Parameter(description = "distance of search from the point (in meters)") @RequestParam int distance) throws CityNotSupportedException, NoParkingFoundException {

        log.info("GET all {} parkings around {} meters of the point [lat:{}, long:{}]", city, distance, latitude, longitude);
        List<ParkingDto> parkings = parkingService.findAllParkingNear(city, latitude, longitude, distance);
        if(log.isDebugEnabled()){
            log.debug("{} parkings found", parkings.size());
        }
        return parkings;
    }
}
