package com.example.postgis.service;

import com.example.postgis.entity.SpatialLab;
import com.example.postgis.repository.SpatialLabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpatialLabService {

    private final SpatialLabRepository spatialLabRepository;

    public SpatialLab getSpatialLab(Long id) {
        return spatialLabRepository.findById(id).get();
    }

    public void saveSpatialLab(SpatialLab spatialLab) {
        spatialLabRepository.save(spatialLab);
    }

    public List<SpatialLab> getIntersects(SpatialLab spatialLab) {
        return spatialLabRepository.findItemsIntersects(spatialLab.getPolygon());
    }

    public List<SpatialLab> getNearWithinDistance(SpatialLab spatialLab, Double distance) {
        return spatialLabRepository.findNearWithinDistance(spatialLab.getPoint(), distance);
    }
}
