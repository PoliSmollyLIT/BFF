package com.tinqin.academy.domain;

import com.example.zooostore.restexport.ZooStoreRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ZooStoreRestClientConfiguration {

        @Bean(name = "ZooStoreRestClient")
        public ZooStoreRestClient getRestExportClient() {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return Feign.builder()
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
                    .target(ZooStoreRestClient.class, "http://localhost:8080");
        }
}
