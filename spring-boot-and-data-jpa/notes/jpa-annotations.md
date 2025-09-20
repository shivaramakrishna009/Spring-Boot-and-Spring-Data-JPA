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
