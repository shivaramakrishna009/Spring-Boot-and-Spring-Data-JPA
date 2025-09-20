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


# @JsonProperty (Jackson)

**Purpose**: Maps a Java field/method to a specific JSON property name for serialization/deserialization.

**Package**: `com.fasterxml.jackson.annotation.JsonProperty`

**Usage**:
- Rename JSON keys without changing Java field names.
- Map JSON keys with different naming conventions.
- Ensure mapping for non-public fields.

**Example**:
```java
@JsonProperty("full_name")
private String fullName;
```

# Records vs POJOs

**POJO**:
- General-purpose Java class.
- Mutable by default.
- Can extend classes and implement interfaces.
- Needs manual boilerplate or Lombok.
- Best for JPA entities, mutable models, legacy integration.

**Record**:
- Special immutable data carrier (Java 16+).
- All fields `private final`.
- Auto-generates constructor, accessors, equals, hashCode, toString.
- Cannot extend classes, can implement interfaces.
- Best for DTOs, value objects, config data.

**Rule of Thumb**:
- Use **Record** for simple, immutable data holders.
- Use **POJO** when mutability, inheritance, or ORM compatibility is required.

# @PathVariable

**Purpose**: Extracts values from the URI path and binds them to method parameters.

**Usage**:
- Define placeholders in the mapping: `/users/{id}`
- Annotate method parameters with `@PathVariable`
- Spring auto-converts to the parameter type

**Examples**:
```java
@GetMapping("/{id}")
public User getUser(@PathVariable Long id) { ... }

@GetMapping("/by-username/{uname}")
public User getUser(@PathVariable("uname") String username) { ... }
```

# @RequestParam

**Purpose**: Binds HTTP request parameters (query/form) to method parameters.

**Usage**:
- `@RequestParam String name` → Required by default.
- `@RequestParam("paramName")` → Map to a different request parameter name.
- `@RequestParam(required = false)` → Optional parameter.
- `@RequestParam(defaultValue = "value")` → Default if missing.
- `@RequestParam List<String>` → Multiple values for same parameter.
- `@RequestParam Map<String, String>` → All parameters as a map.

**Example**:
```java
@GetMapping("/search")
public String search(@RequestParam(defaultValue = "all") String keyword) { ... }
```