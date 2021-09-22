package com.gox.parking.finder.api.service.impl;

import com.gox.parking.finder.api.assembler.ParkingAssembler;
import com.gox.parking.finder.api.config.WfsRestTemplate;
import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.jaxb.ogc.DWithin;
import com.gox.parking.finder.api.jaxb.ogc.Distance;
import com.gox.parking.finder.api.jaxb.ogc.Filter;
import com.gox.parking.finder.api.jaxb.util.FilterXMLMarshaller;
import com.gox.parking.finder.api.jaxb.wfs.FeatureCollection;
import com.gox.parking.finder.api.jaxb.wfs.Point;
import com.gox.parking.finder.api.service.BordeauxParkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to call Bordeaux parking Webservice WFS
 * https://opendata.bordeaux-metropole.fr/explore/dataset/st_park_p/information/
 */
@Service
@Slf4j
public class BordeauxParkingServiceImpl implements BordeauxParkingService {

    private final WfsRestTemplate wfsRestTemplate;

    private final ParkingAssembler parkingAssembler;

    private final FilterXMLMarshaller filterXMLMarshaller;

    @Value("${bordeaux.api.baseUrl}")
    private String baseUrl;

    @Value("${bordeaux.api.key}")
    private String key;

    public BordeauxParkingServiceImpl(WfsRestTemplate wfsRestTemplate, ParkingAssembler parkingAssembler, FilterXMLMarshaller filterXMLMarshaller) {
        this.wfsRestTemplate = wfsRestTemplate;
        this.parkingAssembler = parkingAssembler;
        this.filterXMLMarshaller = filterXMLMarshaller;
    }

    /**
     * Build a WFS WS URL
     * @param filter jaxb annotated filter object
     * @return WFS endpoint url with request params
     */
    private String buildUrl(Filter filter){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("KEY", key)
                .queryParam("SERVICE", "WFS")
                .queryParam("VERSION", "1.1.0")
                .queryParam("REQUEST", "GetFeature")
                .queryParam("TYPENAME", "ST_PARK_P")
                .queryParam("SRSNAME", "EPSG:4326");
        String url = builder.toUriString();
        if(filter != null){
            try {
               url += "&filter=" + filterXMLMarshaller.marshalFilter(filter);
            } catch (JAXBException e) {
                log.error("Error XML marshalling the filter object");
            }
        }
        return url;
    }

    /**
     * {@inheritDoc}
     *  (Service dedicated to Bordeaux city)
     */
    @Override
    public List<ParkingDto> findAllParkings() {
        try {
            FeatureCollection featureCollection = wfsRestTemplate.getForObject(buildUrl(null), FeatureCollection.class, 200);
            return buildParkingDtoList(featureCollection);
        } catch (RestClientException ex){
            log.error("findAllParkings WFS call error {}", ex.getMessage());
            throw ex;
        }
    }

    /**
     * {@inheritDoc}
     * (Service dedicated to Bordeaux city)
     */
    @Override
    public List<ParkingDto> findAllParkingNear(float latitude, float longitude, int distance) {
        try {
            Filter filter = buildFilter(latitude, longitude, distance);
            String url = buildUrl(filter);
            FeatureCollection featureCollection = wfsRestTemplate.getForObject(url, FeatureCollection.class, 200);
            return buildParkingDtoList(featureCollection);
        } catch (RestClientException ex){
            ex.printStackTrace();
            log.error("findAllParkingNear WFS call error {}", ex.getMessage());
            throw ex;
        }
    }

    /**
     * Build a new filter object for the WFS filter DWithin
     * ex :
     * <Filter>
     *    <DWithin>
     *       <PropertyName>the_geom</PropertyName>
     *       <gml:Point srsName="EPSG:4326">
     *           <gml:pos>44.827794 -0.689417</gml:pos>
     *        </gml:Point>
     *       <Distance units="meter">1500</Distance>
     *    </DWithin>
     * </Filter>
     * @param latitude latitude of the search center area
     * @param longitude longitude of the search center area
     * @param distance search distance around the center at latitude/longitude
     * @return filter object ready to be serialized
     */
    private Filter buildFilter(float latitude, float longitude, int distance) {
        Distance distanceObj = new Distance();
        distanceObj.setUnits("meters");
        distanceObj.setValue(distance);

        Point point = new Point();
        point.setPos(latitude + " " + longitude);
        point.setSrsName("EPSG:4326");

        DWithin dWithin = new DWithin();
        dWithin.setPropertyName("the_geom");
        dWithin.setPoint(point);
        dWithin.setDistance(distanceObj);

        Filter filter = new Filter();
        filter.setDWithin(dWithin);
        return filter;
    }

    /**
     * Build a parking dto list from feature collection
     * @param featureCollection response object from WFS WS
     * @return a list of parking dto objects
     */
    private List<ParkingDto> buildParkingDtoList(FeatureCollection featureCollection){
        if(featureCollection != null){
            // iterate on response feature members, transform and collect in a parking dto list
            return featureCollection.getFeatureMembers()
                    .stream()
                    .map(parkingAssembler::wfsFeatureMemberToParkingDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}