package com.example.postgis.custom;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.locationtech.jts.geom.Geometry;
import org.ugeojson.model.GeoJSONObjectDto;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.parser.UltimateGeoJSONParser;
import org.ugeojson.parser.util.JtsUGeojsonConversionUtil;

import java.io.IOException;

/*
GeoJSON를 JTS Geometry로 역직렬화
 */

public class CustomUGeojsonDeserializer<T extends Geometry> extends JsonDeserializer<T> {
    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String geojson = node.toString();
        GeoJSONObjectDto geoJSONObjectDto = UltimateGeoJSONParser.getInstance().parse(geojson);
        JtsUGeojsonConversionUtil jtsUGeojsonConversionUtil = new JtsUGeojsonConversionUtil();
        Geometry geometry = jtsUGeojsonConversionUtil.toJtsGeometry((GeometryDto) geoJSONObjectDto);

        return (T) geometry;
    }
}
