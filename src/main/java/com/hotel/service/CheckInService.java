package com.hotel.service;

import com.hotel.entity.CheckIn;
import com.hotel.entity.Room;
import com.hotel.repository.CheckInRepository;
import com.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CheckInService {
    
    @Autowired
    private CheckInRepository checkInRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }
    
    public Optional<CheckIn> getCheckInById(String folioNo) {
        return checkInRepository.findById(folioNo);
    }
    
    @Transactional
    public CheckIn saveCheckIn(CheckIn checkIn) {
        // Update room status to occupied when checking in
        if (checkIn.getRoom() != null) {
            Room room = checkIn.getRoom();
            room.setStatus("OD"); // Occupied Dirty
            roomRepository.save(room);
        }
        return checkInRepository.save(checkIn);
    }
    
    @Transactional
    public void checkOut(String folioNo) {
        Optional<CheckIn> checkInOpt = checkInRepository.findById(folioNo);
        if (checkInOpt.isPresent()) {
            CheckIn checkIn = checkInOpt.get();
            // Update room status to vacant dirty when checking out
            if (checkIn.getRoom() != null) {
                Room room = checkIn.getRoom();
                room.setStatus("VD"); // Vacant Dirty
                roomRepository.save(room);
            }
            checkInRepository.deleteById(folioNo);
        } else {
            throw new RuntimeException("Check-in not found with folio number: " + folioNo);
        }
    }
    
    public List<CheckIn> getCurrentCheckIns() {
        return checkInRepository.findCurrentCheckIns(LocalDate.now());
    }
    
    public List<CheckIn> getCheckOutsForDate(LocalDate date) {
        return checkInRepository.findCheckOutsForDate(date);
    }
    
    public List<CheckIn> getWalkInGuests() {
        return checkInRepository.findByWalkIn("Y");
    }
    
    public List<CheckIn> searchCheckInsByGuestName(String guestName) {
        return checkInRepository.findByGuestNameContainingIgnoreCase(guestName);
    }
}
