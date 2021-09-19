package com.gox.parking.finder.api.jaxb.wfs;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Point")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Point {

    @XmlElement(namespace = "http://www.opengis.net/gml")
    private String pos;

    @XmlAttribute
    private String srsName;

}
