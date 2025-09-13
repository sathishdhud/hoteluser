package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.RoomStatusSummary;
import com.hotel.service.RoomService;
import com.hotel.service.ReservationService;
import com.hotel.service.CheckInService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard", description = "APIs for dashboard statistics and summaries")
public class DashboardController {
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private CheckInService checkInService;
    
    @GetMapping("/room-status-summary")
    @Operation(summary = "Get room status summary", description = "Get summary of room statuses for dashboard")
    public ResponseEntity<ApiResponse<RoomStatusSummary>> getRoomStatusSummary() {
        try {
            Long availableRooms = roomService.getRoomCountByStatus("VR");
            Long occupiedRooms = roomService.getRoomCountByStatus("OD") + roomService.getRoomCountByStatus("OI");
            Long blockedRooms = roomService.getRoomCountByStatus("Blocked");
            Long totalRooms = (long) roomService.getAllRooms().size();
            
            RoomStatusSummary summary = new RoomStatusSummary(availableRooms, occupiedRooms, blockedRooms, totalRooms);
            return ResponseEntity.ok(ApiResponse.success("Room status summary retrieved", summary));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(ApiResponse.error("Failed to retrieve room status summary: " + e.getMessage()));
        }
    }
    
    @GetMapping("/today-summary")
    @Operation(summary = "Get today's summary", description = "Get summary of today's arrivals, departures, and current occupancy")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTodaySummary() {
        try {
            LocalDate today = LocalDate.now();
            
            Map<String, Object> summary = new HashMap<>();
            summary.put("todayArrivals", reservationService.getArrivalsForDate(today).size());
            summary.put("todayDepartures", reservationService.getDeparturesForDate(today).size());
            summary.put("currentCheckIns", checkInService.getCurrentCheckIns().size());
            summary.put("todayCheckOuts", checkInService.getCheckOutsForDate(today).size());
            summary.put("walkInGuests", checkInService.getWalkInGuests().size());
            
            return ResponseEntity.ok(ApiResponse.success("Today's summary retrieved", summary));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(ApiResponse.error("Failed to retrieve today's summary: " + e.getMessage()));
        }
    }
}
