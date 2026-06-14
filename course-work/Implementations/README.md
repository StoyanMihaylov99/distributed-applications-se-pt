# WorkBuddy

**Faculty Number:** 2401322030 
**Name:** Stoyan Mihaylov
**Project Name:** WorkBuddy

## Description
WorkBuddy is a peer-to-peer (P2P) platform designed to connect people in need of services with local professionals directly. 
Whether it's an elderly lady needing a plumber for her kitchen or someone looking for a mechanic, WorkBuddy provides a straightforward, middleman-free connection.

The platform allows users to:
- **Service Posts:** Professionals can offer their services with descriptions and hourly rates.
- **Work Tasks:** Clients can create specific tasks that need to be completed at a certain time and location.
- **Direct Connection:** No middleman or markup—just person-to-person service.
- **Reviews & Ratings:** Build trust through a community-driven review system.

## Tech Stack
- **Backend:** Java 21, Spring Boot 4.0.6, Spring Data JPA, Spring Security (JWT)
- **Frontend:** Angular 22+, Tailwind CSS
- **Database:** MySQL

## Features
- **User Authentication:** Secure login and registration using JWT tokens.
- **Service Management:** CRUD operations for service offerings and task requests.
- **Search & Filtering:** Find buddies by category and location.
- **Location-based:** Integrated location details for each service and task.
- **Status Tracking:** Monitor the progress of tasks from pending to completed.

## Setup Instructions

### Backend
1. Ensure you have **Java 21** and **Maven** installed.
2. Configure your MySQL database:
   - Create a database named `work_buddy`.
   - The application is configured to use `app_user` with password `123456QaZ!`. You can change this in `Implementations/work-buddy/src/main/resources/application.yaml`.
3. Navigate to the backend directory:
   ```bash
   cd Implementations/work-buddy
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend
1. Ensure you have **Node.js** and **npm** installed.
2. Navigate to the frontend directory:
   ```bash
   cd Implementations/work-buddy-front
   ```
3. Install dependencies:
   ```bash
   npm install
   ```
4. Start the development server:
   ```bash
   npm start
   ```
5. Access the application at `http://localhost:4200`.
