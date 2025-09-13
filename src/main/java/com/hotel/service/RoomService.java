package com.hotel.service;

import com.hotel.entity.Room;
import com.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    
    public Optional<Room> getRoomById(String roomId) {
        return roomRepository.findById(roomId);
    }
    
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
    
    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }
    
    public List<Room> getRoomsByStatus(String status) {
        return roomRepository.findByStatus(status);
    }
    
    public List<Room> getAvailableRooms() {
        return roomRepository.findByStatus("VR");
    }
    
    public List<Room> getAvailableRoomsByType(String roomTypeId) {
        return roomRepository.findAvailableRoomsByType(roomTypeId);
    }
    
    public Room updateRoomStatus(String roomId, String status) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isPresent()) {
            Room room = roomOpt.get();
            room.setStatus(status);
            return roomRepository.save(room);
        }
        throw new RuntimeException("Room not found with id: " + roomId);
    }
    
    public Long getRoomCountByStatus(String status) {
        return roomRepository.countByStatus(status);
    }
}
