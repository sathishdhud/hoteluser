package com.hotel.service;

import com.hotel.entity.Reservation;
import com.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    
    public Optional<Reservation> getReservationById(String reservationNo) {
        return reservationRepository.findById(reservationNo);
    }
    
    public Reservation saveReservation(Reservation reservation) {
        // Calculate number of days
        if (reservation.getArrivalDate() != null && reservation.getDepartureDate() != null) {
            long days = ChronoUnit.DAYS.between(reservation.getArrivalDate(), reservation.getDepartureDate());
            reservation.setNoOfDays((int) days);
        }
        return reservationRepository.save(reservation);
    }
    
    public void deleteReservation(String reservationNo) {
        reservationRepository.deleteById(reservationNo);
    }
    
    public List<Reservation> searchReservationsByGuestName(String guestName) {
        return reservationRepository.findByGuestNameContainingIgnoreCase(guestName);
    }
    
    public List<Reservation> getReservationsForDate(LocalDate date) {
        return reservationRepository.findReservationsForDate(date);
    }
    
    public List<Reservation> getReservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findReservationsByDateRange(startDate, endDate);
    }
    
    public List<Reservation> getArrivalsForDate(LocalDate date) {
        return reservationRepository.findByArrivalDate(date);
    }
    
    public List<Reservation> getDeparturesForDate(LocalDate date) {
        return reservationRepository.findByDepartureDate(date);
    }
    
    public Reservation updateReservation(String reservationNo, Reservation updatedReservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(reservationNo);
        if (existingReservation.isPresent()) {
            updatedReservation.setReservationNo(reservationNo);
            return saveReservation(updatedReservation);
        }
        throw new RuntimeException("Reservation not found with number: " + reservationNo);
    }
}
