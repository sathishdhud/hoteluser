-- Sample data for Hotel Management System
-- Run this after creating the database schema

USE hotel_management;

-- Insert Room Types
INSERT INTO room_type (type_id, type_name, no_of_rooms) VALUES
('RT001', 'Standard', 50),
('RT002', 'Deluxe', 30),
('RT003', 'Suite', 20),
('RT004', 'Presidential Suite', 5);

-- Insert Sample Rooms
INSERT INTO rooms (room_id, room_no, floor, status, room_type_id) VALUES
('R001', '101', '1', 'VR', 'RT001'),
('R002', '102', '1', 'VR', 'RT001'),
('R003', '103', '1', 'OD', 'RT001'),
('R004', '201', '2', 'VR', 'RT002'),
('R005', '202', '2', 'VR', 'RT002'),
('R006', '301', '3', 'VR', 'RT003'),
('R007', '401', '4', 'VR', 'RT004');

-- Insert Companies
INSERT INTO company (company_id, company_name, address1, address2, address3, gst_number) VALUES
('C001', 'ABC Corporation', '123 Business Street', 'Suite 100', 'Business District', 'GST123456789'),
('C002', 'XYZ Enterprises', '456 Corporate Ave', 'Floor 5', 'Downtown', 'GST987654321'),
('C003', 'Tech Solutions Ltd', '789 Innovation Blvd', 'Building A', 'Tech Park', 'GST456789123');

-- Insert Plan Types
INSERT INTO plan_type (plan_id, plan_name) VALUES
('P001', 'European Plan'),
('P002', 'American Plan'),
('P003', 'Modified American Plan'),
('P004', 'Continental Plan');

-- Insert User Types
INSERT INTO user_type (user_type_id, type_name) VALUES
('UT001', 'Administrator'),
('UT002', 'Front Desk Manager'),
('UT003', 'Front Desk Executive'),
('UT004', 'Housekeeping Manager');

-- Insert Sample Users (password is 'password123' encoded with BCrypt)
INSERT INTO hotelsoftusers (user_id, user_name, password, user_type_id) VALUES
('U001', 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'UT001'),
('U002', 'frontdesk', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'UT002'),
('U003', 'reception', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'UT003');

-- Insert Bill Settlement Types
INSERT INTO bill_settlement_types (id, name) VALUES
('BST001', 'Cash'),
('BST002', 'Credit Card'),
('BST003', 'Debit Card'),
('BST004', 'Online Transfer'),
('BST005', 'Company Account');

-- Insert Hotel Account Heads
INSERT INTO hotel_account_head (acc_head_id, name) VALUES
('AH001', 'Room Charges'),
('AH002', 'Food & Beverage'),
('AH003', 'Laundry'),
('AH004', 'Telephone'),
('AH005', 'Miscellaneous');

-- Insert Sample Reservations
INSERT INTO reservations (reservation_no, guest_name, company_id, plan_id, room_type_id, arrival_date, departure_date, no_of_days, no_of_persons, no_of_rooms, mobile_number, email_id, rate, including_gst, remarks) VALUES
('RES001', 'John Doe', 'C001', 'P001', 'RT001', '2024-02-01', '2024-02-05', 4, 2, 1, '+1234567890', 'john.doe@email.com', 150.00, 'Y', 'Corporate booking'),
('RES002', 'Jane Smith', 'C002', 'P002', 'RT002', '2024-02-03', '2024-02-07', 4, 1, 1, '+1987654321', 'jane.smith@email.com', 200.00, 'Y', 'VIP guest'),
('RES003', 'Bob Johnson', NULL, 'P001', 'RT003', '2024-02-05', '2024-02-08', 3, 2, 1, '+1122334455', 'bob.johnson@email.com', 350.00, 'N', 'Anniversary celebration');

-- Insert Taxation Data
INSERT INTO taxation (tax_id, tax_name, percentage) VALUES
('TAX001', 'GST', 18.00),
('TAX002', 'Service Tax', 12.00),
('TAX003', 'Luxury Tax', 5.00);

-- Insert Nationality Data
INSERT INTO nationality (id, nationality) VALUES
('NAT001', 'Indian'),
('NAT002', 'American'),
('NAT003', 'British'),
('NAT004', 'Canadian'),
('NAT005', 'Australian');

-- Insert Arrival Mode Data
INSERT INTO arrival_mode (id, arrival_mode) VALUES
('AM001', 'Flight'),
('AM002', 'Train'),
('AM003', 'Car'),
('AM004', 'Bus'),
('AM005', 'Taxi');

-- Insert Reservation Source Data
INSERT INTO resv_source (id, resv_source) VALUES
('RS001', 'Direct'),
('RS002', 'Online Portal'),
('RS003', 'Travel Agent'),
('RS004', 'Corporate'),
('RS005', 'Phone');

-- Insert Reference Mode Data
INSERT INTO ref_mode (id, ref_mode) VALUES
('RM001', 'Advertisement'),
('RM002', 'Word of Mouth'),
('RM003', 'Social Media'),
('RM004', 'Website'),
('RM005', 'Travel Agent');
