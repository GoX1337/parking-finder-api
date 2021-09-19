package com.gox.parking.finder.api.jaxb.wfs;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "featureMember")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class FeatureMember {

    @XmlElement(name = "ST_PARK_P", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private StParkP stParkP;
}
