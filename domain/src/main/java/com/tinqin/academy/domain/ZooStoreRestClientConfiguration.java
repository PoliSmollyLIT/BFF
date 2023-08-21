package com.tinqin.academy.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinquinstore.zooostore.restexport.ZooStoreRestClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ZooStoreRestClientConfiguration {

    @Bean(name = "ZoosStoreRestClient")
    public ZooStoreRestClient getRestExportClient() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStoreRestClient.class, "http://localhost:8080");
    }
}
