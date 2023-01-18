package com.synergy.productService.repository;

import com.synergy.productService.entity.RoomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilityRepo extends JpaRepository<RoomFacility, Long> {
}