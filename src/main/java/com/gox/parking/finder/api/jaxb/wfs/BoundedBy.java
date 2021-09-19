package com.gox.parking.finder.api.jaxb.wfs;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "boundedBy")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BoundedBy {

    @XmlElement(name = "Envelope", namespace = "http://www.opengis.net/gml")
    private Envelope envelope;

}
