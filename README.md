# Java Attendance Project

## Table of Contents

1. [Introduction](#introduction)
2. [System Overview](#system-overview)
3. [Key Features](#key-features)
4. [Installation](#installation)
5. [User Guide](#user-guide)
    1. [Login and Registration](#login-and-registration)
    2. [Employee Management](#employee-management)
    3. [Attendance Tracking](#attendance-tracking)
    4. [Reports and Notifications](#reports-and-notifications)
6. [Technical Details](#technical-details)
    1. [Technology Stack](#technology-stack)
    2. [Code Structure](#code-structure)
7. [Models](#models)
    1. [Employee Model](#employee-model)
    2. [Attendance Model](#attendance-model)
    3. [User Model](#user-model)
8. [Scripts and Styles](#scripts-and-styles)
9. [Conclusion](#conclusion)

---

## 1. Introduction <a name="introduction"></a>

Welcome to the Attendance Management System documentation. This system is designed to streamline the process of managing employee attendance, providing features for tracking attendance records, generating reports, and facilitating efficient employee management.

## 2. System Overview <a name="system-overview"></a>

The Attendance Management System allows administrators to manage employee data, track attendance, and generate reports for analysis. The system is built on a robust technology stack, ensuring scalability, security, and a user-friendly experience.

## 3. Key Features <a name="key-features"></a>

- **Employee Management:** Add, edit, and delete employee profiles with detailed information, including images.
- **Attendance Tracking:** Record employee attendance with timestamps, allowing efficient monitoring of work hours.
- **Reports and Notifications:** Generate reports based on attendance data and receive notifications for important events.

## 4. Installation <a name="installation"></a>

To install the Attendance Management System, follow the provided installation guide in the repository. Ensure that the required dependencies are met, and the system is configured correctly.

## 5. User Guide <a name="user-guide"></a>

### 5.1 Login and Registration <a name="login-and-registration"></a>

**Login:** Access the system by providing valid credentials. Ensure that the username and password are correct. Use the validation features to handle login errors effectively.

**Registration:** New users can register by providing necessary details. Password complexity requirements are enforced, and username availability is checked before registration.

### 5.2 Employee Management <a name="employee-management"></a>

**Add Employee:** Add new employees to the system with details such as name, role, and image. Unique employee IDs are automatically generated.

**Edit Employee:** Modify employee details, including name, role, and image. The system supports seamless editing and updating of employee information.

**Delete Employee:** Remove employees from the system when necessary. Confirmations and validations are in place to prevent accidental deletions.

### 5.3 Attendance Tracking <a name="attendance-tracking"></a>

**Record Attendance:** Capture attendance records, including timestamps for check-in and check-out. The system logs attendance data efficiently.

**View Attendance:** Access attendance records for individual employees or generate reports for a specified date range.

### 5.4 Reports and Notifications <a name="reports-and-notifications"></a>

**Generate Reports:** Utilize the reporting module to generate comprehensive attendance reports. Filter data by date, employee, or role.

**Receive Notifications:** Stay informed with event notifications. The system provides alerts for crucial events such as attendance submissions.

## 6. Technical Details <a name="technical-details"></a>

### 6.1 Technology Stack <a name="technology-stack"></a>

Frontend: HTML, CSS, JavaScript
Backend: Java, EJB
Database: JPA, Hibernate
Web Framework: Servlets, JSP
Libraries: Bootstrap, jQuery, DataTables, Buttons

### 6.2 Code Structure <a name="code-structure"></a>

The codebase follows a modular structure with packages for beans, models, utilities, and views. The business logic is encapsulated in EJBs, providing a scalable and maintainable architecture.

## 7. Models <a name="models"></a>

### 7.1 Employee Model <a name="employee-model"></a>

The Employee model represents employee information, including details such as ID, name, role, image, and attendance records.

Attributes:

- employeeImage: URL or path to the employee's image.
- employeeId: Unique identifier for the employee.
- firstName: First name of the employee.
- lastName: Last name of the employee.
- role: Employee's role (enumerated type).
- attendances: List of attendance records associated with the employee.

Constructors:

- `Employee(String employeeId, String firstName, String lastName, EmployeeRole role, String employeeImage)`: Parameterized constructor.
- `Employee()`: Default constructor.

Methods:

- `getEmployeeId()`: Returns the employee's ID.
- `setEmployeeId(String employeeId)`: Sets the employee's ID.
- `getFirstName()`: Returns the employee's first name.
- `setFirstName(String firstName)`: Sets the employee's first name.
- `getLastName()`: Returns the employee's last name.
- `setLastName(String lastName)`: Sets the employee's last name.
- `getRole()`: Returns the employee's role.
- `setRole(EmployeeRole role)`: Sets the employee's role.
- `getEmployeeImage()`: Returns the URL or path to the employee's image.
- `setEmployeeImage(String employeeImage)`: Sets the URL or path to the employee's image.
- `getAttendances()`: Returns the list of attendance records associated with the employee.
- `setAttendances(List<Attendance> attendances)`: Sets the list of attendance records associated with the employee.
- `getId()`: Returns the ID of the employee.

### 7.2 Attendance Model <a name="attendance-model"></a>

The Attendance model represents attendance records for employees.

Attributes:

- employeeImage: URL or path to the employee's image.
- employee: Associated employee.
- employeeID: Employee's ID (derived from formula).
- displayId: Display ID of the employee.
- employeeName: Full name of the employee.
- attendanceDate: Date of the attendance record.
- attendanceTime: Time of the attendance record.
- timeIn: Time when the employee clocked in.
- timeOut: Time when the employee clocked out.
- attendanceStatus: Status of the attendance (background color annotated).
- joiningStatus: Joining status of the employee.

Constructors:

- `Attendance(String employeeImage, String employeeID, String employeeName, LocalDate attendanceDate, LocalTime attendanceTime, String attendanceStatus)`: Parameterized constructor.
- `Attendance()`: Default constructor.

Methods:

- `getEmployeeID()`: Returns the employee's ID.
- `setEmployeeID(String employeeID)`: Sets the employee's ID.
- `getEmployeeName()`: Returns the employee's full name.
- `setEmployeeName(String employeeName)`: Sets the employee's full name.
- `getAttendanceDate()`: Returns the date of the attendance record.
- `setAttendanceDate(LocalDate attendanceDate)`: Sets the date of the attendance record.
- `getAttendanceTime()`: Returns the time of the attendance record.
- `setAttendanceTime(LocalTime attendanceTime)`: Sets the time of the attendance record.
- `getAttendanceStatus()`: Returns the status of the attendance record.
- `setAttendanceStatus(String attendanceStatus)`: Sets the status of the attendance record.
- `getEmployeeImage()`: Returns the URL or path to the employee's image.
- `setEmployeeImage(String employeeImage)`: Sets the URL or path to the employee's image.
- `getTimeIn()`: Returns the time when the employee clocked in.
- `setTimeIn(LocalTime timeIn)`: Sets the time when the employee clocked in.
- `getTimeOut()`: Returns the time when the employee clocked out.
- `setTimeOut(LocalTime timeOut)`: Sets the time when the employee clocked out.
- `getJoiningStatus()`: Returns the joining status of the employee.
- `setJoiningStatus(String joiningStatus)`: Sets the joining status of the employee.
- `getEmployee()`: Returns the associated employee.
- `setEmployee(Employee employee)`: Sets the associated employee.
- `getDisplayId()`: Returns the display ID of the employee.
- `setDisplayId(String displayId)`: Sets the display ID of the employee.

### 7.3 User Model <a name="user-model"></a>

The User model represents user accounts for accessing the system.

Attributes:

- username: Username of the user.
- password: Password of the user.
- confirmPassword: Confirmation of the user's password.

Constructors:

- `User()`: Default constructor.
- `User(String username, String password)`: Parameterized constructor.

Methods:

- `getUsername()`: Returns the user's username.
- `setUsername(String username)`: Sets the user's username.
- `getPassword()`: Returns the user's password.
- `setPassword(String password)`: Sets the user's password.
- `getConfirmPassword()`: Returns the confirmation of the user's password.
- `setConfirmPassword(String confirmPassword)`: Sets the confirmation of the user's password.

## 8. Scripts and Styles <a name="scripts-and-styles"></a>

The frontend of the Attendance Management System is powered by HTML, CSS, and JavaScript. Bootstrap and jQuery are utilized for enhanced styling and interactivity. DataTables and Buttons libraries are employed for efficient table management and report generation.

## 9. Conclusion <a name="conclusion"></a>

The Attendance Management System provides a comprehensive solution for organizations to manage employee attendance effectively. With its user-friendly interface, robust features, and scalable architecture, the system ensures a streamlined attendance tracking process.

Thank you for choosing the Attendance Management System!
