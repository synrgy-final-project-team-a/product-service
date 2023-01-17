package com.synergy.productService.Repository;

import com.synergy.productService.Entity.GeneralFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralFacilityRepo extends JpaRepository<GeneralFacility, Long> {
}
