# Spring REST Overview

**Definition**: RESTful web services in Spring are built on Spring MVC, returning data (JSON/XML) instead of views.

**Key Annotations**:
- @RestController → Marks a class as a REST controller.
- @RequestMapping / @GetMapping / @PostMapping / @PutMapping / @DeleteMapping → Map HTTP methods to handler methods.
- @RequestBody → Bind request JSON to Java object.
- @PathVariable → Extract URI path parameters.
- @ResponseStatus → Set HTTP status codes.

**Principles**:
- Stateless communication
- Resource-based URIs
- Use of standard HTTP methods
- Content negotiation for representation formats



# HTTP Response Status Codes in Spring Boot

**Purpose**: Communicate the result of an HTTP request to the client.

**Categories**:
- 2xx → Success
- 3xx → Redirection
- 4xx → Client error
- 5xx → Server error

**Common Codes**:
- 200 OK → Success
- 201 Created → Resource created
- 204 No Content → Successful but no content to return
- 302 Found → Resource temporarily moved
- 304 Not Modified → Resource not modified
- 400 Bad Request → Invalid input
- 401 Unauthorized → Missing/invalid credentials
- 403 Forbidden → Access denied
- 404 Not Found → Resource missing
- 500 Internal Server Error → Server failure

**Ways to Set Status**:
- `ResponseEntity.status(HttpStatus).body(...)`
- `@ResponseStatus(HttpStatus)`
- Exception handling via `@ControllerAdvice`
