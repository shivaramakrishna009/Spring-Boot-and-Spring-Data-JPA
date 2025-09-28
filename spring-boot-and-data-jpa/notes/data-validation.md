# Data Validation in Spring Boot

**Definition**: Ensures only valid data enters the system using Bean Validation (JSR-380) with Hibernate Validator.

**Annotations**:
- @NotNull, @NotEmpty, @NotBlank
- @Size(min, max)
- @Min, @Max
- @Email
- @Pattern(regexp)
- @Positive, @Negative
- @Past, @Future

**Usage**:
```java
@PostMapping
public ResponseEntity<String> create(@Valid @RequestBody UserRequestDTO dto) { ... }
```
**Error Handling**:

Use `@RestControllerAdvice + @ExceptionHandler(MethodArgumentNotValidException.class)`
to return structured error messages.

