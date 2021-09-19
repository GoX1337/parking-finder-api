package com.gox.parking.finder.api.exception;

/**
 * Exception thrown when the city is not supported by the api
 */
public class CityNotSupportedException extends Exception {

    public CityNotSupportedException(String city) {
        super(String.format("The city %s is not supported", city));
    }
}
