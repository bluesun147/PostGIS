package com.example.postgis.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Geometry;
import org.ugeojson.builder.UltimateGeoJSONBuilder;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.parser.util.JtsUGeojsonConversionUtil;

import java.io.IOException;

/*
JTS Geometry를 GeoJSON으로 직렬화
 */

public class CustomUGeojsonSerializer extends JsonSerializer<Geometry> {
    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JtsUGeojsonConversionUtil jtsUGeojsonConversionUtil = new JtsUGeojsonConversionUtil();
        GeometryDto geometryDto = jtsUGeojsonConversionUtil.toGeometryDto(geometry);
        String geoJSON = UltimateGeoJSONBuilder.getInstance().toGeoJSON(geometryDto);

        jsonGenerator.writeRawValue(geoJSON);
    }
}
