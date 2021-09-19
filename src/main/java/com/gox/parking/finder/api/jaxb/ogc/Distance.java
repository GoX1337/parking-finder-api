package com.gox.parking.finder.api.jaxb.ogc;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Distance")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Distance {

    @XmlAttribute
    private String units;

    @XmlValue
    private Integer value;
}
