package com.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "hotelsoftusers")
public class HotelUser {
    
    @Id
    @Column(name = "user_id")
    private String userId;
    
    @NotBlank(message = "Username is required")
    @Column(name = "user_name")
    private String userName;
    
    @NotBlank(message = "Password is required")
    @Column(name = "password")
    private String password;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserType userType;
    
    // Constructors
    public HotelUser() {}
    
    public HotelUser(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    
    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public UserType getUserType() { return userType; }
    public void setUserType(UserType userType) { this.userType = userType; }
}
