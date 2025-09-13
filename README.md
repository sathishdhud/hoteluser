# Hotel Management System API

A comprehensive Spring Boot REST API for managing hotel operations including rooms, reservations, check-ins, billing, and more.

## Features

- **Room Management**: CRUD operations for rooms and room types
- **Reservation System**: Complete reservation lifecycle management
- **Check-in/Check-out**: Guest check-in and check-out processes
- **Company Management**: Corporate client management
- **Dashboard Analytics**: Real-time statistics and summaries
- **Comprehensive API Documentation**: Swagger/OpenAPI integration
- **Security**: Spring Security with authentication
- **Database Integration**: MySQL with JPA/Hibernate

## Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA / Hibernate
- **Security**: Spring Security
- **Documentation**: SpringDoc OpenAPI 3
- **Build Tool**: Maven
- **Java Version**: 17

## Getting Started

### Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Installation

1. **Clone the repository**
   \`\`\`bash
   git clone <repository-url>
   cd hotel-management-system
   \`\`\`

2. **Setup Database**
   - Create a MySQL database named \`hotel_management\`
   - Run the SQL scripts in the \`scripts\` folder:
     \`\`\`bash
     mysql -u root -p hotel_management < scripts/create_database.sql
     mysql -u root -p hotel_management < scripts/insert_sample_data.sql
     \`\`\`

3. **Configure Database Connection**
   Update \`src/main/resources/application.properties\`:
   \`\`\`properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hotel_management
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   \`\`\`

4. **Build and Run**
   \`\`\`bash
   mvn clean install
   mvn spring-boot:run
   \`\`\`

5. **Access the Application**
   - API Base URL: \`http://localhost:8080/api/v1\`
   - Swagger UI: \`http://localhost:8080/api/v1/swagger-ui.html\`
   - API Docs: \`http://localhost:8080/api/v1/api-docs\`

## API Endpoints

### Room Management
- \`GET /rooms\` - Get all rooms
- \`GET /rooms/{id}\` - Get room by ID
- \`POST /rooms\` - Create new room
- \`PUT /rooms/{id}\` - Update room
- \`DELETE /rooms/{id}\` - Delete room
- \`GET /rooms/available\` - Get available rooms
- \`PATCH /rooms/{id}/status\` - Update room status

### Reservation Management
- \`GET /reservations\` - Get all reservations
- \`GET /reservations/{id}\` - Get reservation by ID
- \`POST /reservations\` - Create new reservation
- \`PUT /reservations/{id}\` - Update reservation
- \`DELETE /reservations/{id}\` - Cancel reservation
- \`GET /reservations/arrivals/{date}\` - Get arrivals for date
- \`GET /reservations/departures/{date}\` - Get departures for date

### Check-in Management
- \`GET /checkins\` - Get all check-ins
- \`POST /checkins\` - Check-in guest
- \`DELETE /checkins/{id}/checkout\` - Check-out guest
- \`GET /checkins/current\` - Get current check-ins
- \`GET /checkins/walkins\` - Get walk-in guests

### Company Management
- \`GET /companies\` - Get all companies
- \`POST /companies\` - Create new company
- \`PUT /companies/{id}\` - Update company
- \`DELETE /companies/{id}\` - Delete company

### Dashboard
- \`GET /dashboard/room-status-summary\` - Get room status summary
- \`GET /dashboard/today-summary\` - Get today's summary

## Authentication

The API uses HTTP Basic Authentication. Default credentials:
- Username: \`admin\`
- Password: \`password123\`

## Room Status Codes

- **VR**: Vacant Ready
- **OD**: Occupied Dirty
- **OI**: Occupied Inspected
- **Blocked**: Blocked for maintenance

## Database Schema

The system includes the following main entities:
- Rooms and Room Types
- Reservations
- Check-ins
- Companies
- Users and User Types
- Bills and Transactions
- Various lookup tables

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.

## Support

For support and questions, please contact the development team.
\`\`\`
