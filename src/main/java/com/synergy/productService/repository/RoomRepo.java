package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import com.synergy.productService.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    @Query(value = "SELECT * FROM room r WHERE r.room_id = :id", nativeQuery = true)
    Room checkExistingRoomId(Long id);


}
