### **Laptop Management System - Backend**

#### Overview

This is the backend for the **Laptop Management System**. It is built using **Spring Boot** and provides endpoints for managing laptops, laptop requests, employee assignments, and reported issues. The backend also handles user authentication and authorization for different roles (Admin, Employee).

#### Features

-   **User Authentication**:

    -   JWT-based authentication and role-based access control for Admin and Employee.
-   **Laptop Management**:

    -   CRUD operations for managing laptops (Add, Update, Delete).
    -   View laptop statistics (total, available, assigned, under maintenance).
-   **Laptop Assignments**:

    -   Assign laptops to employees.
    -   Reassign or unassign laptops.
-   **Laptop Requests**:

    -   Handle employee laptop requests.
    -   Approve, reject, or mark requests as pending.
-   **Reported Issues**:

    -   Employees can report issues with laptops.
    -   Admins can resolve or close reported issues.

#### Setup Instructions

1.  **Clone the Repository**

    bash
    ```
    git clone https://github.com/yourusername/laptop-management-backend.git
    ```

3.  **Install Dependencies**

    bash
    ```
    mvn clean install
    ```

5.  **Configure Database**

    -   Ensure **MySQL** or **PostgreSQL** is running.
    -   Configure database connection details in the `application.properties` file:

        properties
        ```
        spring.datasource.url=jdbc:mysql://localhost:3306/laptop_management
        spring.datasource.username=root
        spring.datasource.password=root
        spring.jpa.hibernate.ddl-auto=update
        ```

6.  **Run the Backend**

    bash
    ```
    mvn spring-boot:run
    ```

    The backend will be available at `http://localhost:8080`.

#### API Endpoints

##### Authentication

-   `POST /api/auth/login`: Login and receive a JWT token.
-   `POST /api/auth/register`: Register a new user.

##### Laptop Management

-   `GET /api/laptops`: Get all laptops.
-   `POST /api/laptops`: Add a new laptop.
-   `PUT /api/laptops/{laptopId}`: Update laptop details.
-   `DELETE /api/laptops/{laptopId}`: Delete a laptop.

##### Laptop Requests

-   `GET /api/laptops/laptop-requests`: Get all laptop requests.
-   `PUT /api/laptops/laptop-requests/change-status/{requestId}`: Change the status of a laptop request.

##### Issues

-   `GET /api/issues`: Get all reported issues.
-   `PUT /api/issues/update-status/{issueId}`: Update the status of an issue.

* * * * *
