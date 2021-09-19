package com.gox.parking.finder.api.jaxb.ogc;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Filter")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Filter {

    @XmlElement(name = "DWithin")
    private DWithin dWithin;
}
