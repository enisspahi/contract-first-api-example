This is an API First example of Books API.

# Introduction

## Use Cases

### Use Case 1: Store a new Book in the library 
```mermaid
  sequenceDiagram
    actor Client
      Client->>BooksApi: POST /books
      BooksApi->>Library DB: save(book)
      Library DB-->>BooksApi: Book
      BooksApi-->>Client: Book resource
```
### Use Case 2: Get a book by isbn 
```mermaid
  sequenceDiagram
    actor Client
      Client->>BooksApi: GET /books/{isbn}
      BooksApi->>Library DB: find(isbn)
      Library DB-->>BooksApi: Book
      BooksApi-->>Client: Book resource
```
### Use Case 3: Get all books 
```mermaid
  sequenceDiagram
    actor Client
      Client->>BooksApi: GET /books
      BooksApi->>Library DB: findAll()
      Library DB-->>BooksApi: List<Book>
      BooksApi-->>Client: Book[]
```

# Quickstart
Clone API from [github project](https://github.com/enisspahi/code-first-api-example) and run
````
./gradlew bootRun
````
Then try out the api calls.