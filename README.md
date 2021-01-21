# FireChief
A backend service (built using Java and Spring Boot) which returns the schedule of tasks in a given day or period of time in a Fire station.

# Progress
- [x] Server running -- Spring Boot
- [x] Base Use Cases -- Scheduling for 1 day or an interval of days
- [ ] JSON Server Response -- Beautifying the server response for easier reading client-side
- [ ] Unit Testing

# Requirements
- Java
- Maven
- Spring Boot

# API Endpoints 
1. "/test" -- tests whether the Tomcat server started correctly and is listening on port :8080
2. "/schedule?date=dd-MM-yyyy" -- returns the schedule for the given date input in the query parameter "date"
3. "schedule/multiple?dateStart={dd-MM-yyyy}&dateEnd={dd-MM-yyyy}" -- returns the schedule for the given date range : "dateStart" to "dateEnd"

# How to Install
1. Clone repository on local
2. "mvn package"
3. "mvn spring-boot:run"
4. The service will then be running on localhost:8080
5. In the terminal window press CTRL + C to stop the server