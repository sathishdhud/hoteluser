package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.entity.Reservation;
import com.hotel.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservation Management", description = "APIs for managing hotel reservations")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping
    @Operation(summary = "Get all reservations", description = "Retrieve a list of all reservations")
    public ResponseEntity<ApiResponse<List<Reservation>>> getAllReservations() {
        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            return ResponseEntity.ok(ApiResponse.success("Reservations retrieved successfully", reservations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve reservations: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{reservationNo}")
    @Operation(summary = "Get reservation by number", description = "Retrieve a specific reservation by its number")
    public ResponseEntity<ApiResponse<Reservation>> getReservationById(
            @Parameter(description = "Reservation number") @PathVariable String reservationNo) {
        try {
            Optional<Reservation> reservation = reservationService.getReservationById(reservationNo);
            if (reservation.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Reservation found", reservation.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Reservation not found with number: " + reservationNo));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve reservation: " + e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Create new reservation", description = "Create a new hotel reservation")
    public ResponseEntity<ApiResponse<Reservation>> createReservation(@Valid @RequestBody Reservation reservation) {
        try {
            Reservation savedReservation = reservationService.saveReservation(reservation);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Reservation created successfully", savedReservation));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to create reservation: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{reservationNo}")
    @Operation(summary = "Update reservation", description = "Update an existing reservation")
    public ResponseEntity<ApiResponse<Reservation>> updateReservation(
            @Parameter(description = "Reservation number") @PathVariable String reservationNo,
            @Valid @RequestBody Reservation reservation) {
        try {
            Reservation updatedReservation = reservationService.updateReservation(reservationNo, reservation);
            return ResponseEntity.ok(ApiResponse.success("Reservation updated successfully", updatedReservation));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to update reservation: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{reservationNo}")
    @Operation(summary = "Cancel reservation", description = "Cancel/delete a reservation")
    public ResponseEntity<ApiResponse<Void>> deleteReservation(
            @Parameter(description = "Reservation number") @PathVariable String reservationNo) {
        try {
            reservationService.deleteReservation(reservationNo);
            return ResponseEntity.ok(ApiResponse.success("Reservation cancelled successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to cancel reservation: " + e.getMessage()));
        }
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search reservations by guest name", description = "Search reservations by guest name (case-insensitive)")
    public ResponseEntity<ApiResponse<List<Reservation>>> searchReservationsByGuestName(
            @Parameter(description = "Guest name to search") @RequestParam String guestName) {
        try {
            List<Reservation> reservations = reservationService.searchReservationsByGuestName(guestName);
            return ResponseEntity.ok(ApiResponse.success("Search results retrieved", reservations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to search reservations: " + e.getMessage()));
        }
    }
    
    @GetMapping("/arrivals/{date}")
    @Operation(summary = "Get arrivals for date", description = "Get all reservations arriving on a specific date")
    public ResponseEntity<ApiResponse<List<Reservation>>> getArrivalsForDate(
            @Parameter(description = "Arrival date (YYYY-MM-DD)") 
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<Reservation> reservations = reservationService.getArrivalsForDate(date);
            return ResponseEntity.ok(ApiResponse.success("Arrivals retrieved for date", reservations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve arrivals: " + e.getMessage()));
        }
    }
    
    @GetMapping("/departures/{date}")
    @Operation(summary = "Get departures for date", description = "Get all reservations departing on a specific date")
    public ResponseEntity<ApiResponse<List<Reservation>>> getDeparturesForDate(
            @Parameter(description = "Departure date (YYYY-MM-DD)") 
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<Reservation> reservations = reservationService.getDeparturesForDate(date);
            return ResponseEntity.ok(ApiResponse.success("Departures retrieved for date", reservations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve departures: " + e.getMessage()));
        }
    }
}
