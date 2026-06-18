----------------------------------------------------------------
2. CONCEPTS TO RESEARCH
----------------------------------------------------------------
  Q1. What does CRUD stand for?
Create, Read, Update and Delete. 

  Q2. Difference between HTTP methods POST, PUT, PATCH, DELETE?
Post, creates a new record. Put, updates and the full existing records, Patch udates only a portion of the data. Delete, permanetly removes the data. 
  
  Q3. Give the correct HTTP status code for each:
        a. A new category was created
        (201 Created)
        b. A category was deleted successfully 
        (204 No Content)
        c. The id requested does not exist 
        (404 Not Found)
        d. The request body is missing a required field 
        (400 Bad Request)
        e. The user is logged in but not allowed 
        (403 Forbidden)
        
  Q4. Difference between @RequestBody, @RequestParam,
@PathVariable - used to read values from the url. This is used when finding items by ID.
@RequestBody - is used to read JSON from the request body. The result displayed in JSON when a GET request is processed. 
@RequestParam is used to read query parameters - GET /categories?name=Electronics
      
  Q5. What is Jakarta Bean Validation? Explain @Valid,
      @NotBlank, @Size.
Its a framework used to validate data before it reaches business logic. Valid checks the object, NotBlank checks that the input is not null and size ensure the length matches the required standard tailed to the input information
      
  Q6. Why return a DTO and not the entity itself? Give 2
      reasons.
Entity contains information that should not be exposed. If the Database Entity changes the API contract/structure won't have to be changed. This makes the application scalable. 
      
  Q7. What is Optional<T>? Why does findById return Optional?
It is a container that can contain a value or can also be empty

----------------------------------------------------------------
9. SELF-QUIZ (write answers in RESEARCH-DAY-01.md)
----------------------------------------------------------------
  Q1. Why ResponseEntity instead of returning the object?
It gives full control over the HTTP Response and including status codes. 

  Q2. What status should a successful DELETE return? Why?
204 No content, because the resource was successfully deleted. 

  Q3. Update only one field - PUT or PATCH? Defend your answer.
Patch, it only updates a particular section or field. 

  Q4. What happens if you forget @Valid on the controller?
The entire validation annotations will not execute an no validation will occur. 

  Q5. Why must update/delete have {id} in the URL but create
      does not?
Create will automatically be assigned an ID. The ID is used to find the record which you want to delete. 
