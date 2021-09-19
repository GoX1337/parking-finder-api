package com.gox.parking.finder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {

    private Long id;
    private String ident;
    private String name;
    private PointDto location;
    private String address;
    private int free;
    private int total;
    private String state;

}
