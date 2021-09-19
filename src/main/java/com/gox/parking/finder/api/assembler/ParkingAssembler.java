package com.gox.parking.finder.api.assembler;

import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.dto.PointDto;
import com.gox.parking.finder.api.jaxb.wfs.FeatureMember;
import com.gox.parking.finder.api.jaxb.wfs.Point;
import com.gox.parking.finder.api.jaxb.wfs.StParkP;
import org.springframework.stereotype.Component;

/**
 * Parking object type converter
 */
@Component
public class ParkingAssembler {

    /**
     * Utility method to convert a feature member object (from WFS response) to parking DTO
     * @param featureMember
     * @return ParkingDto
     */
    public ParkingDto wfsFeatureMemberToParkingDto(FeatureMember featureMember){
        ParkingDto parkingDto = new ParkingDto();
        StParkP stParkP = featureMember.getStParkP();

        if(stParkP != null) {
            parkingDto.setId(stParkP.getGid());
            parkingDto.setIdent(stParkP.getIdent());
            parkingDto.setName(stParkP.getName());
            parkingDto.setTotal(stParkP.getTotal());
            parkingDto.setFree(stParkP.getLibres());
            parkingDto.setState(stParkP.getEtat());
            parkingDto.setAddress(stParkP.getAdresse());
        }

        if(stParkP.getGeometry() != null && stParkP.getGeometry().getPoint() != null){
            parkingDto.setLocation(wfsPointToPointDto(stParkP.getGeometry().getPoint()));
        }
        return parkingDto;
    }

    /**
     * Build a point dto object from a point from WFS XML response
     * @param point
     * @return
     */
    private PointDto wfsPointToPointDto(Point point){
        PointDto pointDto = null;
        String pos = point.getPos();
        if(pos != null){
            String[] splitPos = pos.split(" ");
            if(splitPos.length == 2){
                float latitude = Float.parseFloat(splitPos[0]);
                float longitude = Float.parseFloat(splitPos[1]);
                pointDto = new PointDto(latitude, longitude);
            }
        }
        return pointDto;
    }
}
