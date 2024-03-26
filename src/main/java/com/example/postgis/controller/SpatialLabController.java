package com.example.postgis.controller;

import com.example.postgis.entity.SpatialLab;
import com.example.postgis.repository.SpatialLabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spatial")
@RequiredArgsConstructor
public class SpatialLabController {

    private final SpatialLabRepository spatialLabRepository;

    @GetMapping("/{id}")
    public SpatialLab getSpatialLab(@PathVariable Long id) {
        return spatialLabRepository.findById(id).get();
    }

    @PostMapping("")
    public void saveSpatialLab(@RequestBody SpatialLab spatialLab) {
        spatialLabRepository.save(spatialLab);
    }
}
