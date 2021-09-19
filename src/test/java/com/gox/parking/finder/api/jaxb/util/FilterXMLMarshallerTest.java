package com.gox.parking.finder.api.jaxb.util;

import com.gox.parking.finder.api.jaxb.ogc.DWithin;
import com.gox.parking.finder.api.jaxb.ogc.Distance;
import com.gox.parking.finder.api.jaxb.ogc.Filter;
import com.gox.parking.finder.api.jaxb.wfs.Point;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.*;

class FilterXMLMarshallerTest {

    private FilterXMLMarshaller filterXMLMarshaller;

    @BeforeEach
    void setUp() throws JAXBException {
        filterXMLMarshaller = new FilterXMLMarshaller();
    }

    @Test
    void marshalFilter() throws JAXBException {
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Filter xmlns:ns2=\"http://www.opengis.net/gml\"><DWithin><PropertyName>the_geom</PropertyName><Point srsName=\"EPSG:4326\"><ns2:pos>44.827794 -0.689417</ns2:pos></Point><Distance units=\"meters\">3000</Distance></DWithin></Filter>";
        Distance distanceObj = new Distance();
        distanceObj.setUnits("meters");
        distanceObj.setValue(3000);

        Point point = new Point();
        point.setPos("44.827794 -0.689417");
        point.setSrsName("EPSG:4326");

        DWithin dWithin = new DWithin();
        dWithin.setPropertyName("the_geom");
        dWithin.setPoint(point);
        dWithin.setDistance(distanceObj);

        Filter filter = new Filter();
        filter.setDWithin(dWithin);

        String filterXmlStr = filterXMLMarshaller.marshalFilter(filter);

        assertEquals(expected, filterXmlStr);
    }
}