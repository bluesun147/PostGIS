package com.example.postgis.init;

import com.example.postgis.entity.SpatialLab;
import com.example.postgis.repository.SpatialLabRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Order(1)
public class InitSpatialLabService implements ApplicationListener<ApplicationReadyEvent> {

    private final SpatialLabRepository spatialLabRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (spatialLabRepository.count() > 1) {
            System.out.println("There are already items.");
            return;
        }

        // JTS geometry 오브젝트를 PostGIS를 사용해 PostgreSQL에 저장

        // 아이템이 없는 경우 생성
        // init
        SpatialLab spatialLab = new SpatialLab();
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(4, 5));
        spatialLab.setPoint(point);

        Coordinate[] coordinates = new Coordinate[] {new Coordinate(39, 33), new Coordinate(46, 23)};

        MultiPoint multiPoint = geometryFactory.createMultiPointFromCoords(coordinates);

        Coordinate[] coordinates1 = new Coordinate[] {new Coordinate(39, 33), new Coordinate(46, 23)};

        LineString lineString1 = geometryFactory.createLineString(coordinates1);

        Coordinate[] coordinates2 = new Coordinate[] {new Coordinate(26, 38), new Coordinate(30, 37), new Coordinate(40.5, 39.785)};

        LineString lineString2 = geometryFactory.createLineString(coordinates2);

        LineString[] lineStrings = new LineString[] {lineString1, lineString2};

        MultiLineString multiLineString = geometryFactory.createMultiLineString(lineStrings);

        Coordinate[] polygonCoordinates = new Coordinate[] { new Coordinate(39, 33),
                new Coordinate(46, 23), new Coordinate(40, 27), new Coordinate(39, 33)};

        Coordinate[] holeCoordinates = new Coordinate[] { new Coordinate(40, 34),
                new Coordinate(45, 24), new Coordinate(41, 26), new Coordinate(40, 34)};

        LinearRing hole = geometryFactory.createLinearRing(holeCoordinates);

        LinearRing[] holes = new LinearRing[] { hole };

        LinearRing shell = geometryFactory.createLinearRing(polygonCoordinates);

        Polygon polygonWithHole = geometryFactory.createPolygon(shell, holes);

        Polygon polygon = geometryFactory.createPolygon(polygonCoordinates);

        Polygon[] polygons = new Polygon[] {polygon, polygonWithHole};

        MultiPolygon multiPolygon = geometryFactory.createMultiPolygon(polygons);

        Geometry[] geometries = new Geometry[] {point, multiLineString, polygon, multiPolygon};

        GeometryCollection geometryCollection = geometryFactory.createGeometryCollection(geometries);

        spatialLab.setMultiPoint(multiPoint);
        spatialLab.setLineString(lineString1);
        spatialLab.setPolygon(polygon);
        spatialLab.setMultiLineString(multiLineString);
        spatialLab.setMultiPolygon(multiPolygon);
        spatialLab.setGeometryCollection(geometryCollection);

        spatialLabRepository.save(spatialLab);
    }
}
