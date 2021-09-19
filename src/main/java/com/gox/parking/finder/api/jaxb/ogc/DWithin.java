package com.gox.parking.finder.api.jaxb.ogc;

import com.gox.parking.finder.api.jaxb.wfs.Point;
import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "DWithin")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class DWithin {

    @XmlElement(name = "PropertyName")
    private String propertyName;

    @XmlElement(name = "Point")
    private Point point;

    @XmlElement(name = "Distance")
    private Distance distance;
}
