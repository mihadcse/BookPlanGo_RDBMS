# BookPlanGo: A Tour Planner and Booking Management System

**BookPlanGo** is a comprehensive solution designed to simplify tour planning and booking management. This system provides users with the ability to make reservations for hotels and cars, plan tours, track expenses and receive personalized trip suggestions based on their destination. With a user-friendly interface, it serves as a one-stop service for travelers.

## Key Features

### 1. User Roles
- **Traveler**: 
  - Create an account.
  - Search for available hotels and cars.
  - Manage bookings (view or cancel).
  
- **Manager**: 
  - Add and update services (hotels, cars).
  - Manage pricing and availability.
  
- **Admin**: 
  - Oversee the platform.
  - Approve services added by managers(Hotel/Car).
  - Handle feedback and maintain the system.

### 2. Booking and Reservation System
- Users can search and book different types of cars and hotels.
- Select specific hotel rooms, floors, and amenities.
- Users can view their previous bookings for better management.
- Admin approval is required for services added by managers.

### 3. Security
- User passwords are securely stored using encryption, ensuring privacy and data protection.

### 4. Personalization
- Users can plan tours, track their expenses, and receive suggestions tailored to their destination.

## Role of Relational Database (RDBMS)
- The system uses a relational database to manage:
  - **User Data**: Information about travelers, managers, and admins.
  - **Bookings**: Hotel and car rental reservations.
  - **Booking History**: Allows users to access previous bookings.
  
## Tech Stack and Implementation
![Java](https://www.oracle.com/a/ocom/img/cb71-java-logo.png)
![MySQL](https://www.mysql.com/common/logos/logo-mysql-170x115.png)

- **Programming Language**: Java
- **Frontend Framework**: JavaFX, SceneBuilder
- **Database**: MySQL (managed using MySQL Workbench)
- **Database Connectivity**: JDBC
- **Security**: Password encryption techniques
