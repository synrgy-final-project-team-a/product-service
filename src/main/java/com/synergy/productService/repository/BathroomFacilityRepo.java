package com.synergy.productService.repository;

import com.synergy.productService.entity.BathroomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BathroomFacilityRepo extends JpaRepository<BathroomFacility, Long> {
}
