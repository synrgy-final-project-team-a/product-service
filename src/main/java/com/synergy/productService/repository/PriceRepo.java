package com.synergy.productService.repository;

import com.synergy.productService.entity.Price;
import com.synergy.productService.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM price WHERE room_id = :room_id")
    void deletePriceByRoomId(@Param(value = "room_id") Long roomId);

    @Query(value = "SELECT * FROM price p WHERE p.room_id = :id", nativeQuery = true)
    List checkExistingRoomId(Long id);
}