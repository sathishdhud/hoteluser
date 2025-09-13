package com.hotel.repository;

import com.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    
    List<Reservation> findByGuestNameContainingIgnoreCase(String guestName);
    
    List<Reservation> findByArrivalDate(LocalDate arrivalDate);
    
    List<Reservation> findByDepartureDate(LocalDate departureDate);
    
    @Query("SELECT r FROM Reservation r WHERE r.arrivalDate BETWEEN :startDate AND :endDate")
    List<Reservation> findReservationsByDateRange(@Param("startDate") LocalDate startDate, 
                                                  @Param("endDate") LocalDate endDate);
    
    @Query("SELECT r FROM Reservation r WHERE r.arrivalDate = :date OR r.departureDate = :date")
    List<Reservation> findReservationsForDate(@Param("date") LocalDate date);
    
    List<Reservation> findByCompanyCompanyId(String companyId);
    
    List<Reservation> findByRoomTypeTypeId(String roomTypeId);
}
