package com.synergy.productService.repository;

import com.synergy.productService.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    @Query(value = "SELECT * FROM room r WHERE r.room_id = :id", nativeQuery = true)
    Room checkExistingRoomId(Long id);

    @Query(value = "select\n" +
                "f.*,\n" +
                "r.room_name,\n" +
                "r.inside_room_photo,\n" +
                "r.other_room_photo,\n" +
                "r.room_id,\n" +
                "r.quantity_room,\n" +
                "r.available_room,\n" +
                "r.size_room,\n" +
                "p.price_id,\n" +
                "p.duration_type,\n" +
                "p.price\n" +
                "from\n" +
                "room r\n" +
                "left join facility f on f.facility_id = r.facility_id\n" +
                "and f.deleted_at is null\n" +
                "left join price p on p.room_id = r.room_id\n" +
                "and p.deleted_at is null\n" +
                "where\n" +
                "r.deleted_at is null\n" +
                "and r.room_id = :room_id", nativeQuery = true)
    List<Map<String, Object>> getRoomByIdAdmin(@Param(value = "room_id") Long id);
}
