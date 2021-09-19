package com.gox.parking.finder.api.jaxb.util;

import com.gox.parking.finder.api.jaxb.ogc.Filter;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
public class FilterXMLMarshaller {

    private JAXBContext jaxbContext;
    private Marshaller jaxbMarshaller;

    public FilterXMLMarshaller() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(Filter.class);
        jaxbMarshaller = jaxbContext.createMarshaller();
    }

    /**
     * Serialize filter to string (used in WFS filter request param)
     * @param filter
     * @return XML string representation of filter
     * @throws JAXBException
     */
    public String marshalFilter(Filter filter) throws JAXBException {
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(filter, sw);
        return sw.toString();
    }
}
