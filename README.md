# food-ordering-system

====================================================
PART 4: PROJECT INVESTIGATION
====================================================
1. What is Spring Boot?
It is a web framework that makes it easier to develop applications as the Socket Layers are already programed. It makes building applications faster. 

2. What is Maven?
Its a build automation tool used to manage depencies, runs test and compiles the code.

4. What is the purpose of pom.xml?

5. What is the purpose of application.properties?
It enables one to make changes and configure Spring Boot. This makes it possible to change to port number if a similar endpoint already exits. 

6. What does @SpringBootApplication do?
It combines 3 different dependencies Configuration, EnableAutoConfiguration and ComponentScan into one request. Configuration marks classes as a source of Bean. EnableAutoConfiguration tell spring to automatically configure the application based on the found dependecies. ComponentScan scans the package containing the class and subpackages for components.

7. Why do developers use dependency management tools such as Maven?
These tool make it easier to ensure that the version of all dependencies across various projects and the same and ensures there is compatability. It also makes it easier for developers as they do not have to program the dependencies from scratch for every project. It makes code recyclable.     

8. What is a REST API?
Is an application layer that allows software applications over HTTP. It makes use of the CRUD. A request gets sent to the server and response is returned depenfing of the type of request. (POST / GET / PATCH / UPDATE / DELETE)

9. What is JSON?
Its a lightweight text-based format used to store and exchange data between systems. Its easy for humans to read and write. 

10. What is Dependency Injection?
This is when a third party dependecy is imported into an application from an external source to make use of its features in another application.

====================================================
PART 5: PACKAGE STRUCTURE
====================================================

1.Controller
It process all incoming requests and returns response. All parameters are passed through here.

2.Service
It seprates the controller and repositories by implementing application rules and coordinates operations

3.Repository
Holds abstract data persistence and retrieval. It performs CRUD and separates the database from the rest of the applciaion

4.Entity
Represents the Database schema (Columns and Relationships). 

5.Dto
Prevents the exposing of internal entity stuctures directly to clients. 

6.Config
This package is used for custom Spring configuration, security setup, database configuration, and other application settings.

7.Exception
Holds all the exceptions and error handling logic

====================================================
ENDPOINTS
====================================================

  | Method | URL                       | Body         |
  |--------|---------------------------|--------------|
  | POST   | /api/categories           | { "name" }   |
  | GET    | /api/categories           | -            |
  | GET    | /api/categories/{id}      | -            |
  | PUT    | /api/categories/{id}      | { "name" }   |
  | DELETE | /api/categories/{id}      | -            |
