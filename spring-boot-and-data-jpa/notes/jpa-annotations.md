# @Entity (JPA)

**Purpose**: Marks a Java class as a JPA entity mapped to a database table.

**Requirements**:
- Annotate at class level.
- Must have a no-arg constructor.
- Must have a primary key (`@Id`).
- Should not be final.
- Serializable recommended.

**Defaults**:
- Table name = class name (can override with `@Table`).
- Column names = field names (can override with `@Column`).


**Example**:
```java
@Entity
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
}
```

# @Id (JPA)

**Purpose**: Marks a field or getter as the primary key of an entity.

**Rules**:
- Required for every @Entity.
- Can be on a field or getter (defines access type).
- Often combined with @GeneratedValue for auto IDs.

**Example**:
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

**Generation Strategies**:
- IDENTITY → DB auto-increment
- SEQUENCE → DB sequence
- TABLE → Table-based ID generation
- AUTO → Let JPA decide


# @GeneratedValue (JPA)

**Purpose**: Auto-generates primary key values for entities.

**Usage**:
- Always used with @Id.
- Strategy determines how IDs are generated.

**Strategies**:
- IDENTITY → DB auto-increment.
- SEQUENCE → DB sequence object.
- TABLE → Table-based sequence simulation.
- AUTO → JPA chooses best option.
- UUID → Universally unique identifier.

**Example**:
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

# @Table (JPA)

**Purpose**: Customizes the mapping between an @Entity class and a database table.

**Package**: `jakarta.persistence.Table`

**Common Attributes**:
- `name` → Table name.
- `schema` → Schema name.
- `catalog` → Catalog name.
- `uniqueConstraints` → Unique column constraints.
- `indexes` → Define DB indexes.

**Example**:
```java
@Entity
@Table(name = "STUDENTS", schema = "school",
uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Student { ... }
```

# @Column (JPA)

**Purpose**: Customize mapping between entity fields and DB columns.

**Defaults**:
- Column name = field name
- String length = 255
- Nullable = true

**Common Attributes**:
- `name` → Custom column name
- `nullable` → Allow NULL? (default: true)
- `unique` → Unique constraint
- `length` → Max length for String
- `precision` / `scale` → For numeric types
- `insertable` / `updatable` → Include in INSERT/UPDATE
- `columnDefinition` → Exact SQL definition

**Example**:
```java
@Column(name = "student_name", nullable = false, length = 50)
private String name;
```

# JPA Repository

**Definition**: Spring Data JPA interface for CRUD, pagination, sorting, and query execution on JPA entities.

**Extends**:
- PagingAndSortingRepository
- QueryByExampleExecutor

**Features**:
- CRUD methods (`save`, `findById`, `findAll`, `delete`)
- Pagination & sorting
- Derived query methods
- Custom JPQL/native queries via @Query
- Batch operations
- Flush control
- Query by Example (QBE)

**Example**:
```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
List<Employee> findByName(String name);
}
```

# @PostMapping (Spring MVC)

**Purpose**: Maps HTTP POST requests to specific handler methods.

**Package**: `org.springframework.web.bind.annotation.PostMapping`

**Usage**:
- Shortcut for @RequestMapping(method = RequestMethod.POST)
- Commonly used for CREATE operations in REST APIs.
- Often paired with @RequestBody, @PathVariable, @RequestParam.

**Attributes**:
- `value` / `path` → Endpoint URL
- `consumes` → Accepted content type
- `produces` → Response content type
- `headers` / `params` → Request filtering

**Example**:
```java
@PostMapping("/students")
public Student create(@RequestBody Student student) { ... }
```

# @GetMapping (Spring MVC)

**Purpose**: Maps HTTP GET requests to specific handler methods.

**Package**: `org.springframework.web.bind.annotation.GetMapping`

**Usage**:
- Shortcut for @RequestMapping(method = RequestMethod.GET)
- Commonly used for READ operations in REST APIs.
- Often paired with @PathVariable and @RequestParam.

**Attributes**:
- `value` / `path` → Endpoint URL
- `produces` → Response content type
- `params` / `headers` → Request filtering

**Example**:
```java
@GetMapping("/students/{id}")
public Student getStudent(@PathVariable Long id) { ... }
```

# @DeleteMapping (Spring MVC)

**Purpose**: Maps HTTP DELETE requests to specific handler methods.

**Package**: `org.springframework.web.bind.annotation.DeleteMapping`

**Usage**:
- Shortcut for @RequestMapping(method = RequestMethod.DELETE)
- Commonly used for DELETE operations in REST APIs.
- Often paired with @PathVariable or @RequestParam.

**Attributes**:
- `value` / `path` → Endpoint URL
- `produces` → Response content type
- `consumes` → Request content type (rare)
- `params` / `headers` → Request filtering

**Example**:
```java
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
service.deleteById(id);
return ResponseEntity.noContent().build();
}
```

# @ResponseStatus (Spring MVC)

**Purpose**: Sets a custom HTTP status code for controller methods or exceptions.

**Package**: `org.springframework.web.bind.annotation.ResponseStatus`

**Usage**:
- On methods → For successful responses.
- On exception classes → For error responses.

**Attributes**:
- `value` / `code` → HTTP status (e.g., HttpStatus.NOT_FOUND)
- `reason` → Optional error message (HTML by default)

**Examples**:
```java
@ResponseStatus(HttpStatus.CREATED)
public void createUser(...) { ... }

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException { ... }
```

# @OneToOne (JPA)

**Purpose**: Defines a one-to-one relationship between two entities.

**Key Points**:
- One entity instance ↔ one other entity instance.
- Owning side holds the foreign key (`@JoinColumn`).
- Inverse side uses `mappedBy`.
- Default fetch = EAGER.
- Often combined with `cascade` for persistence.

**Examples**:
1. Unidirectional:
```java
   @OneToOne
   @JoinColumn(name = "profile_id")
   private Profile profile;
```

2. Bidirectional:
```java
   @OneToOne(mappedBy = "profile")
   private User user;
```

3. Shared Primary Key:
```java
   @OneToOne
   @MapsId
   @JoinColumn(name = "id")
   private Person person;
```

# Shared Primary Key @OneToOne Mapping

**Definition**: A one-to-one relationship where the child entity’s primary key is also a foreign key referencing the parent’s primary key.

**Annotations**:
- `@OneToOne` → Defines the relationship.
- `@MapsId` → In child entity, reuses parent’s ID as its own.
- `@JoinColumn` → Specifies the foreign key column.

**Example**:
```java
@Entity
public class Passport {
@Id
private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Person person;
}
```

# @OneToMany (JPA)

**Purpose**: Defines a one-to-many relationship between two entities.

**Key Points**:
- Parent ↔ multiple children.
- Child table holds the foreign key.
- Use `mappedBy` in parent to indicate child’s owning field.
- Default fetch = LAZY.
- Often combined with `cascade` and `orphanRemoval`.

**Examples**:
1. Bidirectional:
```java
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
```
2. Unidirectional (Join Table):
```java
   @OneToMany
   @JoinTable(name = "post_comments",
   joinColumns = @JoinColumn(name = "post_id"),
   inverseJoinColumns = @JoinColumn(name = "comment_id"))
   private List<Comment> comments;
```

# @ManyToOne (JPA)

**Purpose**: Defines a many-to-one relationship (many children → one parent).

**Key Points**:
- Child entity owns the relationship.
- Foreign key is in the child table.
- Default fetch = EAGER (best practice: use LAZY).
- Use @JoinColumn to define FK column.

**Example**:
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "department_id")
private Department department;


# JPA Relationship Mapping Cheat Sheet

| Mapping       | Definition                     | DB Structure             | Example                  | Owning Side | Best Use Case |
|---------------|--------------------------------|--------------------------|--------------------------|-------------|---------------|
| @OneToOne     | One ↔ One                      | Foreign key or shared PK | Person ↔ Passport        | Entity with @JoinColumn | Strict 1:1 relationships |
| @OneToMany    | One ↔ Many                     | FK in child table        | Department ↔ Employees   | Child owns FK | Parent with multiple children |
| @ManyToOne    | Many ↔ One                     | FK in child table        | Employees ↔ Department   | Child entity | Many children reference one parent |
| @ManyToMany   | Many ↔ Many                    | Join table with 2 FKs    | Students ↔ Courses       | Either side  | Complex many-to-many associations |


# @JsonBackReference (Jackson)

**Purpose**: Prevents infinite recursion in bidirectional relationships during JSON serialization.

**Package**: `com.fasterxml.jackson.annotation.JsonBackReference`

**Usage**:
- Use with `@JsonManagedReference`.
- `@JsonManagedReference` → forward side (serialized).
- `@JsonBackReference` → back side (ignored in JSON).

**Example**:
```java
public class User {
@JsonManagedReference
private List<Item> items;
}

public class Item {
@JsonBackReference
private User owner;
}
```

# @JsonManagedReference (Jackson)

**Purpose**: Marks the forward side of a bidirectional relationship for JSON serialization.

**Package**: `com.fasterxml.jackson.annotation.JsonManagedReference`

**Usage**:
- Use with `@JsonBackReference`.
- `@JsonManagedReference` → forward side (serialized).
- `@JsonBackReference` → back side (ignored).

**Example**:
```java
public class User {
@JsonManagedReference
private List<Item> items;
}

public class Item {
@JsonBackReference
private User owner;
}
```