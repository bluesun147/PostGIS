package com.example.postgis.controller;

import com.example.postgis.entity.SpatialLab;
import com.example.postgis.service.SpatialLabService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// https://geojson.io/#map=2/0/20

@RestController
@RequestMapping("/spatial")
@RequiredArgsConstructor
public class SpatialLabController {

    private final SpatialLabService spatialLabService;

    @GetMapping("/{id}")
    public SpatialLab getSpatialLab(@PathVariable Long id) {
        return spatialLabService.getSpatialLab(id);
    }

    @PostMapping("")
    public void saveSpatialLab(@RequestBody SpatialLab spatialLab) {
        spatialLabService.saveSpatialLab(spatialLab);
    }

    // intersects - db에 겹치는 구간 있을 시 리턴
    @PostMapping("/intersects")
    public List<SpatialLab> getIntersects(@RequestBody SpatialLab spatialLab) {
         return spatialLabService.getIntersects(spatialLab);
    }

    // ST_DistanceSphere - distance 이내에 저장된 위치 검색
    @PostMapping("/withindistance/{distance}")
    public List<SpatialLab> getNearWithinDistance(@RequestBody SpatialLab spatialLab, @PathVariable Double distance) {
        return spatialLabService.getNearWithinDistance(spatialLab, distance);
    }
}
