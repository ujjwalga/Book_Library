1.
Technologies Used
Java
Spring Boot
Spring Data JPA
MySQL
Maven

2.
Configure Database (application.properties):
Open src/main/resources/application.properties.
Configure the database connection properties (e.g., URL, username, password).
properties

spring.datasource.url=jdbc:mysql://localhost:3306/book_library_db
spring.datasource.username=root
spring.datasource.password=root

3.
Access the Application:
The application will start on http://localhost:9091/BookLibrary/api.

4.API Endpoints
The Book Library API provides the following endpoints:

Authors:
GET /authors: Get all authors
GET /authors/{id}: Get author by ID
POST /authors/create: Create a new author

Books:
GET /books: Get all books
GET /books/{id}: Get book by ID
POST /books/create: Create a new book

Rentals:
POST /rentals/rent: Rent a book
POST /rentals/return: Return a rented book
GET /rentals: Get all rentals

5.

Sample Requests

1. Create Author
POST /BookLibrary/api/authors/create
Content-Type: application/json

{
  "authorName": "John Doe",
  "authorBio": "Bestselling author with a passion for fiction."
}

2. Get All Books

GET /BookLibrary/api/books

3. Rent a Book

POST /BookLibrary/api/rentals/rent
Content-Type: application/json

{
  "bookId": 1,
  "renterName": "Alice Smith"
}

4. Return a Book

POST /BookLibrary/api/rentals/return
Content-Type: application/json

{
  "rentalId": 123
}

