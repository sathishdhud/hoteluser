package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
@Tag(name = "Room Management", description = "APIs for managing hotel rooms")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    
    @GetMapping
    @Operation(summary = "Get all rooms", description = "Retrieve a list of all rooms in the hotel")
    public ResponseEntity<ApiResponse<List<Room>>> getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(ApiResponse.success("Rooms retrieved successfully", rooms));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve rooms: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{roomId}")
    @Operation(summary = "Get room by ID", description = "Retrieve a specific room by its ID")
    public ResponseEntity<ApiResponse<Room>> getRoomById(
            @Parameter(description = "Room ID") @PathVariable String roomId) {
        try {
            Optional<Room> room = roomService.getRoomById(roomId);
            if (room.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Room found", room.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Room not found with ID: " + roomId));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve room: " + e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Create new room", description = "Add a new room to the hotel")
    public ResponseEntity<ApiResponse<Room>> createRoom(@Valid @RequestBody Room room) {
        try {
            Room savedRoom = roomService.saveRoom(room);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Room created successfully", savedRoom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to create room: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{roomId}")
    @Operation(summary = "Update room", description = "Update an existing room")
    public ResponseEntity<ApiResponse<Room>> updateRoom(
            @Parameter(description = "Room ID") @PathVariable String roomId,
            @Valid @RequestBody Room room) {
        try {
            room.setRoomId(roomId);
            Room updatedRoom = roomService.saveRoom(room);
            return ResponseEntity.ok(ApiResponse.success("Room updated successfully", updatedRoom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to update room: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{roomId}")
    @Operation(summary = "Delete room", description = "Remove a room from the hotel")
    public ResponseEntity<ApiResponse<Void>> deleteRoom(
            @Parameter(description = "Room ID") @PathVariable String roomId) {
        try {
            roomService.deleteRoom(roomId);
            return ResponseEntity.ok(ApiResponse.success("Room deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to delete room: " + e.getMessage()));
        }
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Get rooms by status", description = "Retrieve rooms filtered by status (VR, OD, OI, Blocked)")
    public ResponseEntity<ApiResponse<List<Room>>> getRoomsByStatus(
            @Parameter(description = "Room status (VR=Vacant Ready, OD=Occupied Dirty, OI=Occupied Inspected, Blocked)") 
            @PathVariable String status) {
        try {
            List<Room> rooms = roomService.getRoomsByStatus(status);
            return ResponseEntity.ok(ApiResponse.success("Rooms retrieved by status", rooms));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve rooms by status: " + e.getMessage()));
        }
    }
    
    @GetMapping("/available")
    @Operation(summary = "Get available rooms", description = "Retrieve all available rooms (status: VR)")
    public ResponseEntity<ApiResponse<List<Room>>> getAvailableRooms() {
        try {
            List<Room> rooms = roomService.getAvailableRooms();
            return ResponseEntity.ok(ApiResponse.success("Available rooms retrieved", rooms));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve available rooms: " + e.getMessage()));
        }
    }
    
    @PatchMapping("/{roomId}/status")
    @Operation(summary = "Update room status", description = "Update the status of a specific room")
    public ResponseEntity<ApiResponse<Room>> updateRoomStatus(
            @Parameter(description = "Room ID") @PathVariable String roomId,
            @Parameter(description = "New status") @RequestParam String status) {
        try {
            Room updatedRoom = roomService.updateRoomStatus(roomId, status);
            return ResponseEntity.ok(ApiResponse.success("Room status updated successfully", updatedRoom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to update room status: " + e.getMessage()));
        }
    }
}
