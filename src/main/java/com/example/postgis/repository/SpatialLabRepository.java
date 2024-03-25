package com.example.postgis.repository;

import com.example.postgis.entity.SpatialLab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpatialLabRepository extends JpaRepository<SpatialLab, Long> {
}
