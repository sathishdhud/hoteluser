package com.hotel.repository;

import com.hotel.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, String> {
    
    Optional<RoomType> findByTypeName(String typeName);
}
