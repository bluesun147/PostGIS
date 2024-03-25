package com.example.postgis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.*;

import java.io.Serializable;

/*
jts (Java Topology Suite) - 지리 공간 데이터를 처리하는 자바 라이브러리
GIS (Geographic Information System) - 지리 공간 데이터를 수집, 저장, 관리, 분석, 시각화하는 시스템
 */

@Entity
@Getter
@Setter
@Table(name = "spatiallab")
public class SpatialLab implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spatial_seq_gen")
    @SequenceGenerator(name = "spatial_seq_gen",
            sequenceName = "spatial_seq",
            allocationSize = 1)
    private Long id;

    @Column
    private Point point;

    @Column
    private MultiPoint multiPoint;

    @Column
    private LineString lineString;

    @Column
    private MultiLineString multiLineString;

    @Column
    private Polygon polygon;

    @Column
    private MultiPolygon multiPolygon;

    @Column
    private GeometryCollection geometryCollection;
}
