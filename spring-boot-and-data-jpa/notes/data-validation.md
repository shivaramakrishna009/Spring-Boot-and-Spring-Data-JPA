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

# @NotEmpty (Bean Validation)

**Package**: `jakarta.validation.constraints.NotEmpty`

**Purpose**: Ensures a field is not null and not empty.

**Works On**:
- Strings → must not be "" or null
- Collections/arrays/maps → must not be empty or null

**Difference**:
- @NotNull → only null check
- @NotEmpty → null + empty check
- @NotBlank → null + empty + whitespace check (Strings only)

**Example**:
```java
@NotEmpty(message = "Name must not be empty")
private String name;
```