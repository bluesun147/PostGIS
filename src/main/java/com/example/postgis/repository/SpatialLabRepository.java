package com.example.postgis.repository;

import com.example.postgis.entity.SpatialLab;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpatialLabRepository extends JpaRepository<SpatialLab, Long> {

    // intersects - db에 겹치는 구간 있을 시 리턴
    @Query("select s from SpatialLab s where intersects(s.polygon, :filter) = true")
    List<SpatialLab> findItemsIntersects(@Param("filter")Geometry filter);

    @Query("select s from SpatialLab s where ST_DistanceSphere(s.polygon, :filter) <:distance")
    List<SpatialLab> findNearWithinDistance(@Param("filter")Geometry filter, @Param("distance")Double distance);
}
