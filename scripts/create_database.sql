-- Hotel Management System Database Schema
-- Run this script to create the database and tables

CREATE DATABASE IF NOT EXISTS hotel_management;
USE hotel_management;

-- Room Types Table
CREATE TABLE IF NOT EXISTS room_type (
    type_id VARCHAR(50) PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    no_of_rooms INT NOT NULL
);

-- Rooms Table
CREATE TABLE IF NOT EXISTS rooms (
    room_id VARCHAR(50) PRIMARY KEY,
    room_no VARCHAR(20) NOT NULL UNIQUE,
    floor VARCHAR(10),
    status VARCHAR(20) DEFAULT 'VR',
    room_type_id VARCHAR(50),
    FOREIGN KEY (room_type_id) REFERENCES room_type(type_id)
);

-- Company Table
CREATE TABLE IF NOT EXISTS company (
    company_id VARCHAR(50) PRIMARY KEY,
    company_name VARCHAR(200) NOT NULL,
    address1 VARCHAR(200),
    address2 VARCHAR(200),
    address3 VARCHAR(200),
    gst_number VARCHAR(50)
);

-- Plan Type Table
CREATE TABLE IF NOT EXISTS plan_type (
    plan_id VARCHAR(50) PRIMARY KEY,
    plan_name VARCHAR(100) NOT NULL
);

-- User Type Table
CREATE TABLE IF NOT EXISTS user_type (
    user_type_id VARCHAR(50) PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL
);

-- Hotel Users Table
CREATE TABLE IF NOT EXISTS hotelsoftusers (
    user_id VARCHAR(50) PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_type_id VARCHAR(50),
    FOREIGN KEY (user_type_id) REFERENCES user_type(user_type_id)
);

-- User Rights Data Table
CREATE TABLE IF NOT EXISTS user_rights_data (
    id VARCHAR(50) PRIMARY KEY,
    user_id VARCHAR(50),
    module_name VARCHAR(100),
    permission_type VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES hotelsoftusers(user_id)
);

-- Bill Settlement Types Table
CREATE TABLE IF NOT EXISTS bill_settlement_types (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Hotel Account Head Table
CREATE TABLE IF NOT EXISTS hotel_account_head (
    acc_head_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

-- Reservations Table
CREATE TABLE IF NOT EXISTS reservations (
    reservation_no VARCHAR(50) PRIMARY KEY,
    guest_name VARCHAR(200) NOT NULL,
    company_id VARCHAR(50),
    plan_id VARCHAR(50),
    room_type_id VARCHAR(50),
    arrival_date DATE NOT NULL,
    departure_date DATE NOT NULL,
    no_of_days INT,
    no_of_persons INT,
    no_of_rooms INT,
    mobile_number VARCHAR(20),
    email_id VARCHAR(100),
    rate DECIMAL(10,2),
    including_gst CHAR(1) DEFAULT 'N',
    remarks TEXT,
    rooms_checked_in INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES company(company_id),
    FOREIGN KEY (plan_id) REFERENCES plan_type(plan_id),
    FOREIGN KEY (room_type_id) REFERENCES room_type(type_id)
);

-- Check-in Table
CREATE TABLE IF NOT EXISTS checkin (
    folio_no VARCHAR(50) PRIMARY KEY,
    reservation_no VARCHAR(50),
    guest_name VARCHAR(200) NOT NULL,
    room_id VARCHAR(50),
    arrival_date DATE NOT NULL,
    departure_date DATE NOT NULL,
    mobile_number VARCHAR(20),
    email_id VARCHAR(100),
    rate DECIMAL(10,2),
    remarks TEXT,
    audit_date DATE,
    walk_in CHAR(1) DEFAULT 'N',
    FOREIGN KEY (reservation_no) REFERENCES reservations(reservation_no),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

-- Advances Table
CREATE TABLE IF NOT EXISTS advances (
    receipt_no VARCHAR(50) PRIMARY KEY,
    folio_no VARCHAR(50),
    reservation_no VARCHAR(50),
    bill_no VARCHAR(50),
    guest_name VARCHAR(200),
    date DATE,
    arrival_date DATE,
    audit_date DATE,
    shift_date DATE,
    shift_no VARCHAR(50),
    mode_of_payment_id VARCHAR(50),
    amount DECIMAL(10,2),
    credit_card_company VARCHAR(100),
    card_number VARCHAR(50),
    online_company_name VARCHAR(100),
    details TEXT,
    narration TEXT,
    FOREIGN KEY (folio_no) REFERENCES checkin(folio_no),
    FOREIGN KEY (reservation_no) REFERENCES reservations(reservation_no),
    FOREIGN KEY (mode_of_payment_id) REFERENCES bill_settlement_types(id)
);

-- FO Bill Table
CREATE TABLE IF NOT EXISTS fobill (
    bill_no VARCHAR(50) PRIMARY KEY,
    folio_no VARCHAR(50),
    guest_name VARCHAR(200),
    room_id VARCHAR(50),
    total_amount DECIMAL(10,2),
    advance_amount DECIMAL(10,2),
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_no) REFERENCES checkin(folio_no),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

-- Post Transactions Table
CREATE TABLE IF NOT EXISTS post_transactions (
    transaction_id VARCHAR(50) PRIMARY KEY,
    folio_no VARCHAR(50),
    bill_no VARCHAR(50),
    room_id VARCHAR(50),
    guest_name VARCHAR(200),
    date DATE,
    audit_date DATE,
    acc_head_id VARCHAR(50),
    voucher_no VARCHAR(50),
    amount DECIMAL(10,2),
    narration TEXT,
    FOREIGN KEY (folio_no) REFERENCES checkin(folio_no),
    FOREIGN KEY (bill_no) REFERENCES fobill(bill_no),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id),
    FOREIGN KEY (acc_head_id) REFERENCES hotel_account_head(acc_head_id)
);

-- Shift Table
CREATE TABLE IF NOT EXISTS shift (
    shift_no VARCHAR(50) PRIMARY KEY,
    shift_date DATE,
    balance DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Additional lookup tables
CREATE TABLE IF NOT EXISTS taxation (
    tax_id VARCHAR(50) PRIMARY KEY,
    tax_name VARCHAR(100),
    percentage DECIMAL(5,2)
);

CREATE TABLE IF NOT EXISTS nationality (
    id VARCHAR(50) PRIMARY KEY,
    nationality VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS arrival_mode (
    id VARCHAR(50) PRIMARY KEY,
    arrival_mode VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS resv_source (
    id VARCHAR(50) PRIMARY KEY,
    resv_source VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS ref_mode (
    id VARCHAR(50) PRIMARY KEY,
    ref_mode VARCHAR(100)
);
