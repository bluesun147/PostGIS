package com.example.postgis.config;

import com.example.postgis.custom.CustomUGeojsonDeserializer;
import com.example.postgis.custom.CustomUGeojsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpatialLabAppConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        // ObjectMapper는 파싱 과정에서 커스터마이징 할 때, 직렬화 할 때 주로 사용
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Geometry.class, new CustomUGeojsonSerializer());
        simpleModule.addDeserializer(Polygon.class, new CustomUGeojsonDeserializer<>());
        simpleModule.addDeserializer(LineString.class, new CustomUGeojsonDeserializer<>());

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}