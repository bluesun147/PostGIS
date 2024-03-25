package com.example.postgis;

import com.example.postgis.entity.SpatialLab;
import com.example.postgis.repository.SpatialLabRepository;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ugeojson.builder.UltimateGeoJSONBuilder;
import org.ugeojson.model.GeoJSONObjectDto;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;
import org.ugeojson.parser.UltimateGeoJSONParser;
import org.ugeojson.parser.util.JtsUGeojsonConversionUtil;

@SpringBootTest
class PostGisApplicationTests {

    @Autowired
    private SpatialLabRepository spatialLabRepository;

    @Test
    void conversionTest() {
        SpatialLab spatialLab = spatialLabRepository.findById(1L).get();
        Point point = spatialLab.getPoint();
        Polygon polygon = spatialLab.getPolygon();

        JtsUGeojsonConversionUtil jtsUGeojsonConversionUtil = new JtsUGeojsonConversionUtil();
        PointDto pointDto = jtsUGeojsonConversionUtil.toPointDto(point);
        PolygonDto polygonDto = jtsUGeojsonConversionUtil.toPolygonDto(polygon);
        GeometryDto geometryDto = jtsUGeojsonConversionUtil.toGeometryDto(spatialLab.getGeometryCollection());

        String pointJson = UltimateGeoJSONBuilder.getInstance().toGeoJSON(pointDto);
        String polygonJson = UltimateGeoJSONBuilder.getInstance().toGeoJSON(polygonDto);

        System.out.println("pointJson = " + pointJson);
        System.out.println("------------");
        System.out.println("polygonJson = " + polygonJson);

        GeoJSONObjectDto parse = UltimateGeoJSONParser.getInstance().parse(pointJson);
        Geometry geometry = jtsUGeojsonConversionUtil.toJtsGeometry((GeometryDto) parse);

        System.out.println("geometry = " + geometry);
    }

}
