---
title: "Use Cases"
date: 2023-04-26T16:53:17+02:00
---

### Use Case 1: Store a new Book in the library
{{< mermaid >}}
    sequenceDiagram
        Client->>BooksApi: POST /books
        BooksApi->>Library DB: save(book)
        Library DB-->>BooksApi: Book
        BooksApi-->>Client: Book resource
{{< /mermaid >}}

### Use Case 2: Get a book by isbn
{{< mermaid >}}
  sequenceDiagram
      Client->>BooksApi: GET /books/{isbn}
      BooksApi->>Library DB: find(isbn)
      Library DB-->>BooksApi: Book
      BooksApi-->>Client: Book resource
{{< /mermaid >}}
### Use Case 3: Get all books
{{< mermaid >}}
  sequenceDiagram
      Client->>BooksApi: GET /books
      BooksApi->>Library DB: findAll()
      Library DB-->>BooksApi: Book[]
      BooksApi-->>Client: Book[]
{{< /mermaid >}}
