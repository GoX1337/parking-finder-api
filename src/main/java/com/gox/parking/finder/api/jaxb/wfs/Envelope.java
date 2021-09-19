package com.gox.parking.finder.api.jaxb.wfs;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Envelope" )
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Envelope {

    @XmlElement(namespace = "http://www.opengis.net/gml")
    private String lowerCorner;

    @XmlElement(namespace = "http://www.opengis.net/gml")
    private String upperCorner;

}
