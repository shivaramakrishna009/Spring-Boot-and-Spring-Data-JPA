# @ExceptionHandler (Spring MVC)

**Package**: `org.springframework.web.bind.annotation.ExceptionHandler`

**Purpose**: Defines methods to handle exceptions thrown in controllers.

**Usage**:
- Local (inside a controller) → handles only that controller’s exceptions.
- Global (with @RestControllerAdvice) → handles exceptions across all controllers.

**Method Signature**:
```java
@ExceptionHandler(ExceptionType.class)
public ResponseEntity<?> handleException(ExceptionType ex) { ... }
```

**Return Types**:
- Custom object (auto-serialized)
- ResponseEntity (status + body)
- ProblemDetail (Spring 6+)

**Example**:
```java
@ExceptionHandler(UserNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public Map<String, String> handleUserNotFound(UserNotFoundException ex) {
return Map.of("error", ex.getMessage());
}
```