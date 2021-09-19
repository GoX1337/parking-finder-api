package com.gox.parking.finder.api.jaxb.wfs;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "geometry" )
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Geometry {

    @XmlElement(name = "Point", namespace = "http://www.opengis.net/gml")
    private Point point;

}
