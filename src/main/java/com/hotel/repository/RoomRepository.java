package com.hotel.repository;

import com.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    
    List<Room> findByStatus(String status);
    
    List<Room> findByFloor(String floor);
    
    List<Room> findByRoomTypeTypeId(String roomTypeId);
    
    Optional<Room> findByRoomNo(String roomNo);
    
    @Query("SELECT r FROM Room r WHERE r.status = 'VR' AND r.roomType.typeId = :roomTypeId")
    List<Room> findAvailableRoomsByType(@Param("roomTypeId") String roomTypeId);
    
    @Query("SELECT COUNT(r) FROM Room r WHERE r.status = :status")
    Long countByStatus(@Param("status") String status);
}
