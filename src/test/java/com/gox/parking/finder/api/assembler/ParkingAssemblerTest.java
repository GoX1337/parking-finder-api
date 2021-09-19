package com.gox.parking.finder.api.assembler;

import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.jaxb.wfs.FeatureMember;
import com.gox.parking.finder.api.jaxb.wfs.Geometry;
import com.gox.parking.finder.api.jaxb.wfs.Point;
import com.gox.parking.finder.api.jaxb.wfs.StParkP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingAssemblerTest {

    private final ParkingAssembler parkingAssembler = new ParkingAssembler();

    @Test
    void wfsFeatureMemberToParkingDto() {
        String pkg1 = "PKG1";
        FeatureMember featureMember = new FeatureMember();
        StParkP stParkP = new StParkP();
        stParkP.setIdent(pkg1);
        stParkP.setTotal(150);
        stParkP.setLibres(100);

        Geometry geometry = new Geometry();
        Point point = new Point();
        point.setPos("45 85");
        geometry.setPoint(point);
        stParkP.setGeometry(geometry);

        stParkP.setAdresse("12 avenue leclerc");
        featureMember.setStParkP(stParkP);

        ParkingDto parkingDto = parkingAssembler.wfsFeatureMemberToParkingDto(featureMember);

        assertEquals(pkg1, parkingDto.getIdent());
        assertEquals(150, parkingDto.getTotal());
        assertEquals(100, parkingDto.getFree());
        assertEquals(45, parkingDto.getLocation().getLatitude());
    }
}