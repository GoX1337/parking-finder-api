package com.gox.parking.finder.api.service.impl;

import com.gox.parking.finder.api.assembler.ParkingAssembler;
import com.gox.parking.finder.api.config.WfsRestTemplate;
import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.jaxb.util.FilterXMLMarshaller;
import com.gox.parking.finder.api.jaxb.wfs.*;
import com.gox.parking.finder.api.service.BordeauxParkingService;
import com.gox.parking.finder.api.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BordeauxParkingServiceImplTest {

    private BordeauxParkingService bordeauxParkingService;

    @Mock
    private WfsRestTemplate wfsRestTemplate;

    @BeforeEach
    void setUp() throws JAXBException {
        bordeauxParkingService = new BordeauxParkingServiceImpl(wfsRestTemplate, new ParkingAssembler(), new FilterXMLMarshaller());
    }

    @Test
    void findAllParkings() {
        FeatureCollection featureCollection = new FeatureCollection();
        FeatureMember fm1 = new FeatureMember();
        FeatureMember fm2 = new FeatureMember();

        StParkP stParkP1 = new StParkP();
        String pkg1 = "PKG1";
        FeatureMember featureMember = new FeatureMember();
        stParkP1.setIdent(pkg1);
        stParkP1.setTotal(600);
        stParkP1.setLibres(450);
        Geometry geometry1 = new Geometry();
        Point point1 = new Point();
        point1.setPos("45 85");
        geometry1.setPoint(point1);
        stParkP1.setGeometry(geometry1);
        stParkP1.setAdresse("12 avenue leclerc");
        fm1.setStParkP(stParkP1);

        StParkP stParkP2 = new StParkP();
        String pkg2 = "PKG2";
        stParkP2.setIdent(pkg2);
        stParkP2.setTotal(150);
        stParkP2.setLibres(100);
        Geometry geometry2 = new Geometry();
        Point point2 = new Point();
        point2.setPos("36 78");
        geometry2.setPoint(point2);
        stParkP2.setGeometry(geometry2);
        stParkP2.setAdresse("32 avenue marchand");
        fm2.setStParkP(stParkP2);

        featureCollection.getFeatureMembers().add(fm1);
        featureCollection.getFeatureMembers().add(fm2);

        when(wfsRestTemplate.getForObject("null?key=null&SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&TYPENAME=ST_PARK_P&SRSNAME=EPSG:4326", FeatureCollection.class, 200)).thenReturn(featureCollection);
        List<ParkingDto> pkgs = bordeauxParkingService.findAllParkings();
        assertEquals(2, pkgs.size());
        assertEquals("PKG1", pkgs.get(0).getIdent());
        assertEquals(450, pkgs.get(0).getFree());
        assertEquals(36, pkgs.get(1).getLocation().getLatitude());
        assertEquals(150, pkgs.get(1).getTotal());
    }

    @Test
    void findAllParkingNear() {
        FeatureCollection featureCollection = new FeatureCollection();
        FeatureMember fm1 = new FeatureMember();
        FeatureMember fm2 = new FeatureMember();

        StParkP stParkP1 = new StParkP();
        String pkg1 = "PKG1";
        FeatureMember featureMember = new FeatureMember();
        stParkP1.setIdent(pkg1);
        stParkP1.setTotal(600);
        stParkP1.setLibres(450);
        Geometry geometry1 = new Geometry();
        Point point1 = new Point();
        point1.setPos("45 85");
        geometry1.setPoint(point1);
        stParkP1.setGeometry(geometry1);
        stParkP1.setAdresse("12 avenue leclerc");
        fm1.setStParkP(stParkP1);

        StParkP stParkP2 = new StParkP();
        String pkg2 = "PKG2";
        stParkP2.setIdent(pkg2);
        stParkP2.setTotal(150);
        stParkP2.setLibres(100);
        Geometry geometry2 = new Geometry();
        Point point2 = new Point();
        point2.setPos("36 78");
        geometry2.setPoint(point2);
        stParkP2.setGeometry(geometry2);
        stParkP2.setAdresse("32 avenue marchand");
        fm2.setStParkP(stParkP2);

        featureCollection.getFeatureMembers().add(fm1);
        featureCollection.getFeatureMembers().add(fm2);

        when(wfsRestTemplate.getForObject("null?key=null&SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&TYPENAME=ST_PARK_P&SRSNAME=EPSG:4326&filter=<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Filter xmlns:ns2=\"http://www.opengis.net/gml\"><DWithin><PropertyName>the_geom</PropertyName><Point srsName=\"EPSG:4326\"><ns2:pos>44.827793 -0.689417</ns2:pos></Point><Distance units=\"meters\">1500</Distance></DWithin></Filter>", FeatureCollection.class, 200)).thenReturn(featureCollection);
        List<ParkingDto> pkgs = bordeauxParkingService.findAllParkingNear(44.827794f, -0.689417f, 1500);
        assertEquals(2, pkgs.size());
        assertEquals("PKG1", pkgs.get(0).getIdent());
        assertEquals(450, pkgs.get(0).getFree());
        assertEquals(36, pkgs.get(1).getLocation().getLatitude());
        assertEquals(150, pkgs.get(1).getTotal());
    }
}