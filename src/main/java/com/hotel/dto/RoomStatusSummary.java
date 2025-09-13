package com.hotel.dto;

public class RoomStatusSummary {
    private Long availableRooms;
    private Long occupiedRooms;
    private Long blockedRooms;
    private Long totalRooms;
    
    public RoomStatusSummary() {}
    
    public RoomStatusSummary(Long availableRooms, Long occupiedRooms, Long blockedRooms, Long totalRooms) {
        this.availableRooms = availableRooms;
        this.occupiedRooms = occupiedRooms;
        this.blockedRooms = blockedRooms;
        this.totalRooms = totalRooms;
    }
    
    // Getters and Setters
    public Long getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(Long availableRooms) { this.availableRooms = availableRooms; }
    
    public Long getOccupiedRooms() { return occupiedRooms; }
    public void setOccupiedRooms(Long occupiedRooms) { this.occupiedRooms = occupiedRooms; }
    
    public Long getBlockedRooms() { return blockedRooms; }
    public void setBlockedRooms(Long blockedRooms) { this.blockedRooms = blockedRooms; }
    
    public Long getTotalRooms() { return totalRooms; }
    public void setTotalRooms(Long totalRooms) { this.totalRooms = totalRooms; }
}
