package com.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rooms")
public class Room {
    
    @Id
    @Column(name = "room_id")
    private String roomId;
    
    @NotBlank(message = "Room number is required")
    @Column(name = "room_no")
    private String roomNo;
    
    @Column(name = "floor")
    private String floor;
    
    @Column(name = "status")
    private String status; // VR, OD, OI, Blocked
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;
    
    // Constructors
    public Room() {}
    
    public Room(String roomId, String roomNo, String floor, String status) {
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.floor = floor;
        this.status = status;
    }
    
    // Getters and Setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    
    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
    
    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
}
