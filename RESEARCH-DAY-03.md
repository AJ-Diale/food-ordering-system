  Q1.  What is JPA? What is Hibernate? How are they related?
JPA (Java Persitence API) is a specification used for working with relational Databases. Hibernate is an implementation of JPA. The JPA implements it and the Hibernate contains the code that makes it happen
  
  Q2.  What is the difference between @Entity and @Table?
Entity turns a Java class into a JPA entity that presents a database. Table specifies the database name, table and related tables. 
  
  Q3.  What is a foreign key? What is @ManyToOne? Give 2 real-world examples.
A foreign Key is used to connect relational databases. Many to one means that mean database can be linked to one. A real world example would be many people accessing one wifi access point. People in the library also access the same book for different or the same information. 
  
  Q4.  What does @JoinColumn(name = "category_id") do?
It joins the columns by making the category_id the foreing Key 

  Q5.  Why store price as BigDecimal and not double?
BigDecimal does not round off the decimals like double. Therefore it is more accurate and wont result in additional amounnts that was never aquired beign added
  
  Q6.  What does FetchType LAZY vs EAGER mean? What is the default for @ManyToOne?
LAZY means don't fetch the related object unless it is requested. This illiminates the display of too much information. 
EAGER load the related information immediately. 
The Default for ManyToOne is fetch EAGER. 

  Q7.  What is the N+1 query problem?
This is when a request is made to a table and another request has to be made to a related table. This creates multiples requests instead of one. 

  Q8.  What is dependency injection? Constructor injection vs field injection — which is preferred and why?
Dependency injection is when a feature from an external source is imported into an application for its features. 

  Q9.  What does @RequiredArgsConstructor (Lombok) do?
Constructor injection is preferred over field injection because it makes dependencies explicit, supports immutability, and makes unit testing easie

  Q10. What is the role of the SERVICE layer? Why must it be separate from the controller?
The service layer is where all business logic lies. The controller handles HTTP Requests and Responses

  Q11. Why MUST you validate that categoryId exists before saving a menu?
To insure the data recieved is valid and relates to all the fields in the DB. The user might insert the wrong information. Its for data integrity. 

  Q12. Difference between save() and saveAndFlush()?
They both save data to the Database. Save() has a delay where saveandFlush() is immediate and requires the DB to be in sync. 

  Q13. Why write private mapper methods (entity <-> dto)?
This is to separate data structure from API structure and keep your code clean and reuseable. 
