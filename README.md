# Car Rental Service App

A Spring Boot-based web application for managing car rentals, allowing users to browse, rent, and manage cars while admins and employees can oversee operations, manage listings, and maintain user data.

## Features

- **User Management**:
    - Register, update profile, and delete accounts with verification for rented cars.
    - Role-based access: customers can rent cars, employees manage rentals, and admins manage all data.
- **Car Inventory**:
    - List available cars with details and filter options by type, price category, transmission, and fuel type.
    - Rent and return cars, with availability updates.
- **Authentication and Authorization**:
    - Secure login, role-based access control, and password validation.
- **Alerts and Notifications**:
    - Real-time notifications if a car is unavailable or the user is renting a car.

## Technologies Used

- **Backend**: Java, Spring Boot, Spring Security
- **Frontend**: Thymeleaf, Bootstrap
- **Database**: MongoDB for car and user data persistence
- **Authentication**: Spring Security with role-based access

## Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/car-rental-service.git
   cd car-rental-service
   ```

2. **Configure MongoDB**:
    - Ensure MongoDB is installed and running.
    - Update application properties with your MongoDB URI.

3. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the Application**:
    - Open [http://localhost:8080](http://localhost:8080) in your browser.

## Endpoints

- `/auth/registerNewUser` – Register new users
- `/auth/createEmployee` – Register employee (Admin only)
- `/cars/showAllCars` – Display available cars
- `/cars/rentCar` – Rent a car (Customer only)
- `/users/showUserProfile` – User profile with options to update or delete account

## Notes
- **Customer Tasks**: Can search and rent cars.
- **Employee Tasks**: Can manage cars and rental status.
- **Admin Tasks**: Can manage cars, users and employee accounts.
- **Security**: User cannot delete account while renting a car.
