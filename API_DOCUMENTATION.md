# Hotel Management System API Documentation

## Overview
This API provides comprehensive endpoints for managing hotel operations including rooms, reservations, check-ins, and company management.

**Base URL:** `http://localhost:8080/api/v1`
**API Documentation:** `http://localhost:8080/api/v1/swagger-ui.html`

## Authentication
Currently, the API uses basic authentication. Future versions will include JWT token-based authentication.

## Response Format
All API responses follow a consistent format:

\`\`\`json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... },
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

## Room Management APIs

### 1. Get All Rooms
**Endpoint:** `GET /rooms`
**Description:** Retrieve all rooms in the hotel

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Rooms retrieved successfully",
  "data": [
    {
      "roomId": "R001",
      "roomNo": "101",
      "floor": "1",
      "status": "VR",
      "roomType": {
        "typeId": "RT001",
        "typeName": "Standard",
        "noOfRooms": 50
      }
    }
  ],
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 2. Get Room by ID
**Endpoint:** `GET /rooms/{roomId}`
**Description:** Retrieve a specific room by ID

**Path Parameters:**
- `roomId` (string): Unique room identifier

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Room found",
  "data": {
    "roomId": "R001",
    "roomNo": "101",
    "floor": "1",
    "status": "VR",
    "roomType": {
      "typeId": "RT001",
      "typeName": "Standard",
      "noOfRooms": 50
    }
  },
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 3. Create New Room
**Endpoint:** `POST /rooms`
**Description:** Add a new room to the hotel

**Request Body:**
\`\`\`json
{
  "roomId": "R002",
  "roomNo": "102",
  "floor": "1",
  "status": "VR",
  "roomType": {
    "typeId": "RT001"
  }
}
\`\`\`

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Room created successfully",
  "data": {
    "roomId": "R002",
    "roomNo": "102",
    "floor": "1",
    "status": "VR",
    "roomType": {
      "typeId": "RT001",
      "typeName": "Standard",
      "noOfRooms": 50
    }
  },
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 4. Update Room Status
**Endpoint:** `PATCH /rooms/{roomId}/status`
**Description:** Update the status of a specific room

**Path Parameters:**
- `roomId` (string): Unique room identifier

**Query Parameters:**
- `status` (string): New room status (VR, OD, OI, Blocked)

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Room status updated successfully",
  "data": {
    "roomId": "R001",
    "roomNo": "101",
    "floor": "1",
    "status": "OD",
    "roomType": {
      "typeId": "RT001",
      "typeName": "Standard",
      "noOfRooms": 50
    }
  },
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 5. Get Available Rooms
**Endpoint:** `GET /rooms/available`
**Description:** Retrieve all available rooms (status: VR)

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Available rooms retrieved",
  "data": [
    {
      "roomId": "R001",
      "roomNo": "101",
      "floor": "1",
      "status": "VR",
      "roomType": {
        "typeId": "RT001",
        "typeName": "Standard",
        "noOfRooms": 50
      }
    }
  ],
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

## Reservation Management APIs

### 1. Create New Reservation
**Endpoint:** `POST /reservations`
**Description:** Create a new hotel reservation

**Request Body:**
\`\`\`json
{
  "reservationNo": "RES001",
  "guestName": "John Doe",
  "company": {
    "companyId": "C001"
  },
  "planType": {
    "planId": "P001"
  },
  "roomType": {
    "typeId": "RT001"
  },
  "arrivalDate": "2024-01-20",
  "departureDate": "2024-01-25",
  "noOfPersons": 2,
  "noOfRooms": 1,
  "mobileNumber": "+1234567890",
  "emailId": "john.doe@email.com",
  "rate": 150.00,
  "includingGst": "Y",
  "remarks": "Late arrival expected"
}
\`\`\`

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Reservation created successfully",
  "data": {
    "reservationNo": "RES001",
    "guestName": "John Doe",
    "company": {
      "companyId": "C001",
      "companyName": "ABC Corp"
    },
    "planType": {
      "planId": "P001",
      "planName": "European Plan"
    },
    "roomType": {
      "typeId": "RT001",
      "typeName": "Standard"
    },
    "arrivalDate": "2024-01-20",
    "departureDate": "2024-01-25",
    "noOfDays": 5,
    "noOfPersons": 2,
    "noOfRooms": 1,
    "mobileNumber": "+1234567890",
    "emailId": "john.doe@email.com",
    "rate": 150.00,
    "includingGst": "Y",
    "remarks": "Late arrival expected",
    "roomsCheckedIn": 0,
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  },
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 2. Get Arrivals for Date
**Endpoint:** `GET /reservations/arrivals/{date}`
**Description:** Get all reservations arriving on a specific date

**Path Parameters:**
- `date` (string): Arrival date in YYYY-MM-DD format

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Arrivals retrieved for date",
  "data": [
    {
      "reservationNo": "RES001",
      "guestName": "John Doe",
      "arrivalDate": "2024-01-20",
      "departureDate": "2024-01-25",
      "noOfRooms": 1,
      "roomType": {
        "typeId": "RT001",
        "typeName": "Standard"
      }
    }
  ],
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 3. Search Reservations by Guest Name
**Endpoint:** `GET /reservations/search?guestName={name}`
**Description:** Search reservations by guest name (case-insensitive)

**Query Parameters:**
- `guestName` (string): Guest name to search for

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Search results retrieved",
  "data": [
    {
      "reservationNo": "RES001",
      "guestName": "John Doe",
      "arrivalDate": "2024-01-20",
      "departureDate": "2024-01-25",
      "mobileNumber": "+1234567890",
      "emailId": "john.doe@email.com"
    }
  ],
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

## Check-in Management APIs

### 1. Create Check-in
**Endpoint:** `POST /checkins`
**Description:** Check-in a guest to the hotel

**Request Body:**
\`\`\`json
{
  "folioNo": "F001",
  "reservation": {
    "reservationNo": "RES001"
  },
  "guestName": "John Doe",
  "room": {
    "roomId": "R001"
  },
  "arrivalDate": "2024-01-20",
  "departureDate": "2024-01-25",
  "mobileNumber": "+1234567890",
  "emailId": "john.doe@email.com",
  "rate": 150.00,
  "remarks": "VIP guest",
  "auditDate": "2024-01-20",
  "walkIn": "N"
}
\`\`\`

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Guest checked in successfully",
  "data": {
    "folioNo": "F001",
    "reservation": {
      "reservationNo": "RES001"
    },
    "guestName": "John Doe",
    "room": {
      "roomId": "R001",
      "roomNo": "101",
      "status": "OD"
    },
    "arrivalDate": "2024-01-20",
    "departureDate": "2024-01-25",
    "mobileNumber": "+1234567890",
    "emailId": "john.doe@email.com",
    "rate": 150.00,
    "walkIn": "N"
  },
  "timestamp": "2024-01-20T14:30:00"
}
\`\`\`

### 2. Check-out Guest
**Endpoint:** `DELETE /checkins/{folioNo}/checkout`
**Description:** Check out a guest and update room status

**Path Parameters:**
- `folioNo` (string): Folio number of the check-in

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Guest checked out successfully",
  "data": null,
  "timestamp": "2024-01-25T11:00:00"
}
\`\`\`

### 3. Get Current Check-ins
**Endpoint:** `GET /checkins/current`
**Description:** Get all guests currently checked in

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Current check-ins retrieved",
  "data": [
    {
      "folioNo": "F001",
      "guestName": "John Doe",
      "room": {
        "roomId": "R001",
        "roomNo": "101"
      },
      "arrivalDate": "2024-01-20",
      "departureDate": "2024-01-25"
    }
  ],
  "timestamp": "2024-01-22T10:00:00"
}
\`\`\`

## Company Management APIs

### 1. Create Company
**Endpoint:** `POST /companies`
**Description:** Add a new company to the system

**Request Body:**
\`\`\`json
{
  "companyId": "C001",
  "companyName": "ABC Corporation",
  "address1": "123 Business Street",
  "address2": "Suite 100",
  "address3": "Business District",
  "gstNumber": "GST123456789"
}
\`\`\`

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Company created successfully",
  "data": {
    "companyId": "C001",
    "companyName": "ABC Corporation",
    "address1": "123 Business Street",
    "address2": "Suite 100",
    "address3": "Business District",
    "gstNumber": "GST123456789"
  },
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 2. Search Companies by Name
**Endpoint:** `GET /companies/search?companyName={name}`
**Description:** Search companies by name (case-insensitive)

**Query Parameters:**
- `companyName` (string): Company name to search for

**Response Example:**
\`\`\`json
{
  "success": true,
  "message": "Search results retrieved",
  "data": [
    {
      "companyId": "C001",
      "companyName": "ABC Corporation",
      "gstNumber": "GST123456789"
    }
  ],
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

## Error Responses

### 400 Bad Request
\`\`\`json
{
  "success": false,
  "message": "Validation failed: Guest name is required",
  "data": null,
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 404 Not Found
\`\`\`json
{
  "success": false,
  "message": "Room not found with ID: R999",
  "data": null,
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### 500 Internal Server Error
\`\`\`json
{
  "success": false,
  "message": "Failed to retrieve rooms: Database connection error",
  "data": null,
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

## Room Status Codes
- **VR**: Vacant Ready - Room is clean and ready for occupancy
- **OD**: Occupied Dirty - Room is occupied by a guest
- **OI**: Occupied Inspected - Room is occupied and has been inspected
- **Blocked**: Room is blocked for maintenance or other reasons

## Best Practices

1. **Date Format**: Always use ISO 8601 date format (YYYY-MM-DD) for date parameters
2. **Error Handling**: Always check the `success` field in responses before processing data
3. **Validation**: Ensure required fields are provided in request bodies
4. **Status Updates**: Use appropriate room status codes when updating room status
5. **Search**: Use URL encoding for search parameters containing special characters

## Rate Limiting
Currently, there are no rate limits imposed. Future versions may include rate limiting for API protection.

## Versioning
The API uses URL versioning. Current version is v1. Future versions will be available at `/api/v2`, etc.
