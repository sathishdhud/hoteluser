package com.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "room_type")
public class RoomType {
    
    @Id
    @Column(name = "type_id")
    private String typeId;
    
    @NotBlank(message = "Type name is required")
    @Column(name = "type_name")
    private String typeName;
    
    @NotNull(message = "Number of rooms is required")
    @Column(name = "no_of_rooms")
    private Integer noOfRooms;
    
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;
    
    // Constructors
    public RoomType() {}
    
    public RoomType(String typeId, String typeName, Integer noOfRooms) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.noOfRooms = noOfRooms;
    }
    
    // Getters and Setters
    public String getTypeId() { return typeId; }
    public void setTypeId(String typeId) { this.typeId = typeId; }
    
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    
    public Integer getNoOfRooms() { return noOfRooms; }
    public void setNoOfRooms(Integer noOfRooms) { this.noOfRooms = noOfRooms; }
    
    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
}
