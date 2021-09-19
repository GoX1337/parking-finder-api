package com.gox.parking.finder.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WfsRestTemplateConfig {

    @Bean
    public WfsRestTemplate wfsRestTemplate() {
        return new WfsRestTemplate();
    }
}
