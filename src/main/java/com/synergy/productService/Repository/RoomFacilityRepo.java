package com.synergy.productService.Repository;

import com.synergy.productService.Entity.RoomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilityRepo extends JpaRepository<RoomFacility, Long> {
}