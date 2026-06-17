----------------------------------------------------------------
2. CONCEPTS TO RESEARCH
----------------------------------------------------------------
  Q1. What does CRUD stand for?
  Q2. Difference between HTTP methods POST, PUT, PATCH, DELETE?
  Q3. Give the correct HTTP status code for each:
        a. A new category was created
        b. A category was deleted successfully
        c. The id requested does not exist
        d. The request body is missing a required field
        e. The user is logged in but not allowed
  Q4. Difference between @RequestBody, @RequestParam,
      @PathVariable - with one tiny example of each.
  Q5. What is Jakarta Bean Validation? Explain @Valid,
      @NotBlank, @Size.
  Q6. Why return a DTO and not the entity itself? Give 2
      reasons.
  Q7. What is Optional<T>? Why does findById return Optional?

----------------------------------------------------------------
9. SELF-QUIZ (write answers in RESEARCH-DAY-01.md)
----------------------------------------------------------------
  Q1. Why ResponseEntity instead of returning the object?
  Q2. What status should a successful DELETE return? Why?
  Q3. Update only one field - PUT or PATCH? Defend your answer.
  Q4. What happens if you forget @Valid on the controller?
  Q5. Why must update/delete have {id} in the URL but create
      does not
