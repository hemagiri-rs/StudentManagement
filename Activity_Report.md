# Activity Report

## 1. Title

**Design and Develop a Basic Spring Boot Application**

## 2. Aim

To design and develop a basic executable Spring Boot web application that exposes REST APIs for adding and retrieving student records while demonstrating auto-configuration, three-layer architecture, validation, and JUnit testing.

## 3. Software Requirements

- Java 23.0.2 installed locally, with Java 21 source compatibility configured in Maven.
- Spring Boot 3.4.10
- Maven Wrapper
- Spring Web dependency
- Spring Boot Test and JUnit 5
- Visual Studio Code and PowerShell on Windows

## 4. Theory and Concepts Used

### Spring Boot

Spring Boot simplifies Spring application development through convention, starter dependencies, embedded web server support, and auto-configuration.

### REST API

A REST API exposes resources through HTTP methods. This application uses POST to create students and GET to retrieve students. JSON is used for request and response data.

### Three-layer architecture

1. **Controller:** handles HTTP requests and responses.
2. **Service:** contains validation and business logic.
3. **Repository:** stores and retrieves student data.

### Dependency injection

Spring creates the controller, service, and repository objects and supplies dependencies through constructors. This reduces manual object creation and keeps the classes loosely coupled.

### JUnit testing

JUnit 5 tests verify the service rules automatically. `@SpringBootTest` uses Spring Boot testing support to load the application context and inject the service and repository.

## 5. Algorithm or Workflow

1. Start the Spring Boot application.
2. Receive a JSON request at `/api/students`.
3. The controller passes the student to the service.
4. The service rejects a null or blank name with an `IllegalArgumentException`.
5. For valid input, the repository generates the next ID and stores the student in memory.
6. Return the created student with HTTP 201.
7. For GET requests, retrieve all records or search by ID.
8. Return HTTP 200 when a record exists and HTTP 404 when it does not.

## 6. Implementation Details

The application entry point is `StudentmanagementApplication.java` and uses `@SpringBootApplication`. `Student.java` contains the ID, name, and department fields. `StudentRepository.java` uses a `ConcurrentHashMap` and `AtomicLong`. `StudentService.java` validates names and delegates data operations. `StudentController.java` maps the REST endpoints and formats error responses.

The generated Spring Boot configuration is used without unnecessary manual MVC or server configuration. Spring Boot scans the root package and automatically registers the annotated components.

## 7. Testing Table

### Automated tests

| Test case | Input | Expected output | Actual output | Status |
|---|---|---|---|---|
| Valid student creation | Student name `Hemagiri Raj`, department `CSE` | Student is saved with an ID | Student saved with a generated ID and matching fields | PASS |
| Blank name validation | Name containing only spaces | `IllegalArgumentException` | `IllegalArgumentException` thrown | PASS |
| Missing student retrieval | ID `9999` in an empty repository | Empty result | `Optional.empty()` returned | PASS |
| Spring context loading | Application test context | Context starts successfully | Context started successfully | PASS |

Automated command:

```powershell
.\mvnw.cmd test
```

Observed result: **4 tests run, 0 failures, 0 errors, 0 skipped.**

### Manual API tests

| Test case | Input | Expected output | Actual output | Status |
|---|---|---|---|---|
| Add valid student | POST `/api/students` with name `Hemagiri Raj`, department `CSE` | HTTP 201 with generated ID | HTTP 201; `{"id":1,"name":"Hemagiri Raj","department":"CSE"}` | PASS |
| Retrieve all students | GET `/api/students` | HTTP 200 with created student array | HTTP 200; `[{"id":1,"name":"Hemagiri Raj","department":"CSE"}]` | PASS |
| Reject blank name | POST `/api/students` with empty name | HTTP 400 and meaningful message | HTTP 400; `{"error":"Student name must not be blank"}` | PASS |
| Retrieve missing student | GET `/api/students/9999` | HTTP 404 | HTTP 404; `{"error":"Student not found with id: 9999"}` | PASS |

## 8. Results

The Student Management System compiled successfully, the Spring application context loaded, all automated tests passed, and all four required REST scenarios returned the expected status codes and JSON responses.

## 9. Conclusion

A basic executable Spring Boot REST application was successfully developed using Maven. The activity demonstrates Spring Boot auto-configuration, constructor-based dependency injection, separation into controller/service/repository layers, validation, appropriate HTTP status codes, and automated testing with JUnit 5.

## Limitation

The application stores data only in memory. Records are cleared when the application stops, because a database was intentionally excluded from this introductory activity.
