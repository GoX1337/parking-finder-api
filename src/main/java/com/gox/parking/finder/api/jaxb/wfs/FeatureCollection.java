package com.gox.parking.finder.api.jaxb.wfs;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement( name = "FeatureCollection" , namespace = "http://www.opengis.net/wfs")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class FeatureCollection {

    @XmlElement(namespace = "http://www.opengis.net/gml")
    private BoundedBy boundedBy;

    @XmlElement(name = "featureMember", namespace = "http://www.opengis.net/gml")
    private List<FeatureMember> featureMembers = new ArrayList<>();

}