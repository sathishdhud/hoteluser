package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.entity.CheckIn;
import com.hotel.service.CheckInService;
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
@RequestMapping("/checkins")
@Tag(name = "Check-in Management", description = "APIs for managing guest check-ins and check-outs")
public class CheckInController {
    
    @Autowired
    private CheckInService checkInService;
    
    @GetMapping
    @Operation(summary = "Get all check-ins", description = "Retrieve a list of all check-ins")
    public ResponseEntity<ApiResponse<List<CheckIn>>> getAllCheckIns() {
        try {
            List<CheckIn> checkIns = checkInService.getAllCheckIns();
            return ResponseEntity.ok(ApiResponse.success("Check-ins retrieved successfully", checkIns));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve check-ins: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{folioNo}")
    @Operation(summary = "Get check-in by folio number", description = "Retrieve a specific check-in by folio number")
    public ResponseEntity<ApiResponse<CheckIn>> getCheckInById(
            @Parameter(description = "Folio number") @PathVariable String folioNo) {
        try {
            Optional<CheckIn> checkIn = checkInService.getCheckInById(folioNo);
            if (checkIn.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Check-in found", checkIn.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Check-in not found with folio number: " + folioNo));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve check-in: " + e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Create new check-in", description = "Check-in a guest to the hotel")
    public ResponseEntity<ApiResponse<CheckIn>> createCheckIn(@Valid @RequestBody CheckIn checkIn) {
        try {
            CheckIn savedCheckIn = checkInService.saveCheckIn(checkIn);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Guest checked in successfully", savedCheckIn));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to check in guest: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{folioNo}/checkout")
    @Operation(summary = "Check out guest", description = "Check out a guest and update room status")
    public ResponseEntity<ApiResponse<Void>> checkOut(
            @Parameter(description = "Folio number") @PathVariable String folioNo) {
        try {
            checkInService.checkOut(folioNo);
            return ResponseEntity.ok(ApiResponse.success("Guest checked out successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to check out guest: " + e.getMessage()));
        }
    }
    
    @GetMapping("/current")
    @Operation(summary = "Get current check-ins", description = "Get all guests currently checked in")
    public ResponseEntity<ApiResponse<List<CheckIn>>> getCurrentCheckIns() {
        try {
            List<CheckIn> checkIns = checkInService.getCurrentCheckIns();
            return ResponseEntity.ok(ApiResponse.success("Current check-ins retrieved", checkIns));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve current check-ins: " + e.getMessage()));
        }
    }
    
    @GetMapping("/checkouts/{date}")
    @Operation(summary = "Get check-outs for date", description = "Get all check-outs scheduled for a specific date")
    public ResponseEntity<ApiResponse<List<CheckIn>>> getCheckOutsForDate(
            @Parameter(description = "Check-out date (YYYY-MM-DD)") 
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<CheckIn> checkIns = checkInService.getCheckOutsForDate(date);
            return ResponseEntity.ok(ApiResponse.success("Check-outs retrieved for date", checkIns));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve check-outs: " + e.getMessage()));
        }
    }
    
    @GetMapping("/walkins")
    @Operation(summary = "Get walk-in guests", description = "Get all walk-in guests (no prior reservation)")
    public ResponseEntity<ApiResponse<List<CheckIn>>> getWalkInGuests() {
        try {
            List<CheckIn> checkIns = checkInService.getWalkInGuests();
            return ResponseEntity.ok(ApiResponse.success("Walk-in guests retrieved", checkIns));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve walk-in guests: " + e.getMessage()));
        }
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search check-ins by guest name", description = "Search check-ins by guest name (case-insensitive)")
    public ResponseEntity<ApiResponse<List<CheckIn>>> searchCheckInsByGuestName(
            @Parameter(description = "Guest name to search") @RequestParam String guestName) {
        try {
            List<CheckIn> checkIns = checkInService.searchCheckInsByGuestName(guestName);
            return ResponseEntity.ok(ApiResponse.success("Search results retrieved", checkIns));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to search check-ins: " + e.getMessage()));
        }
    }
}
