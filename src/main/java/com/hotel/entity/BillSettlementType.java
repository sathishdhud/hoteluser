package com.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "bill_settlement_types")
public class BillSettlementType {
    
    @Id
    private String id;
    
    @NotBlank(message = "Settlement type name is required")
    @Column(name = "name")
    private String name;
    
    // Constructors
    public BillSettlementType() {}
    
    public BillSettlementType(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
