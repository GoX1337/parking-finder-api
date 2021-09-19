package com.gox.parking.finder.api.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * HTTP response from WFS GET call has a Content-type header "text/xml; subtype=gml/3.1.1; charset=iso-8859-1"
 * Default RestTemplate can not process this Content-type value WfsRestTemplate will erase it with just text/xml
 */
public class WfsRestTemplate extends RestTemplate {

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback callback, final ResponseExtractor<T> responseExtractor) throws RestClientException {
        return super.doExecute(url, method, callback, response -> {
            response.getHeaders().setContentType(MediaType.TEXT_XML);
            return responseExtractor.extractData(response);
        });
    }
}