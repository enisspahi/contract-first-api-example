---
title: Books API v1.0
language_tabs:
  - shell: Shell
language_clients:
  - shell: ""
toc_footers: []
includes: []
search: false
highlight_theme: darkula
headingLevel: 2

---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="books-api">Books API v1.0</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

This is an API First example of Books API.

Base URLs:

* <a href="http://localhost:8080">http://localhost:8080</a>

<h1 id="books-api-books-api">Books API</h1>

## Show all books

<a id="opIdfindAllBooks"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/books \
  -H 'Accept: */*'

```

`GET /books`

Shows all books stored in the library

> Example responses

> 200 Response

<h3 id="show-all-books-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="show-all-books-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[Book](#schemabook)]|false|none|none|
|» title|string|false|none|The title of the Book|
|» author|string|false|none|The author of the Book|
|» isbn|string|false|none|The ISBN number|

<aside class="success">
This operation does not require authentication
</aside>

## Create a book

<a id="opIdcreateBook"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/books \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

`POST /books`

Creates a book and stores it at the library

> Body parameter

```json
{
  "title": "Practical Design Patterns for Java Developers",
  "author": "Miroslav Wengner",
  "isbn": "string"
}
```

<h3 id="create-a-book-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[Book](#schemabook)|true|none|

> Example responses

> 201 Response

<h3 id="create-a-book-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|[Book](#schemabook)|

<aside class="success">
This operation does not require authentication
</aside>

## Find book by isbn

<a id="opIdfindBookByIsbn"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/books/{isbn} \
  -H 'Accept: */*'

```

`GET /books/{isbn}`

Finds a book by searching with an isbn

<h3 id="find-book-by-isbn-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|isbn|path|string|true|none|

> Example responses

> 200 Response

<h3 id="find-book-by-isbn-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Book](#schemabook)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|string|

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_Book">Book</h2>
<!-- backwards compatibility -->
<a id="schemabook"></a>
<a id="schema_Book"></a>
<a id="tocSbook"></a>
<a id="tocsbook"></a>

```json
{
  "title": "Practical Design Patterns for Java Developers",
  "author": "Miroslav Wengner",
  "isbn": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|title|string|false|none|The title of the Book|
|author|string|false|none|The author of the Book|
|isbn|string|false|none|The ISBN number|

