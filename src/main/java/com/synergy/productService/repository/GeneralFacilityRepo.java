package com.synergy.productService.repository;

import com.synergy.productService.entity.GeneralFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralFacilityRepo extends JpaRepository<GeneralFacility, Long> {
}
