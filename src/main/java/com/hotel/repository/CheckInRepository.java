package com.hotel.repository;

import com.hotel.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, String> {
    
    List<CheckIn> findByGuestNameContainingIgnoreCase(String guestName);
    
    List<CheckIn> findByArrivalDate(LocalDate arrivalDate);
    
    List<CheckIn> findByDepartureDate(LocalDate departureDate);
    
    List<CheckIn> findByWalkIn(String walkIn);
    
    @Query("SELECT c FROM CheckIn c WHERE c.arrivalDate <= :currentDate AND c.departureDate >= :currentDate")
    List<CheckIn> findCurrentCheckIns(@Param("currentDate") LocalDate currentDate);
    
    @Query("SELECT c FROM CheckIn c WHERE c.departureDate = :date")
    List<CheckIn> findCheckOutsForDate(@Param("date") LocalDate date);
}
