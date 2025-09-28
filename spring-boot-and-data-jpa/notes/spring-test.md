# Spring Boot Testing

**Dependencies**: `spring-boot-starter-test`

**Test Types**:
- Unit → JUnit + Mockito
- Integration → @SpringBootTest
- Slice → @WebMvcTest, @DataJpaTest, @JsonTest
- E2E → RestAssured, Selenium

**Key Annotations**:
- @SpringBootTest → full context
- @WebMvcTest → web layer only
- @DataJpaTest → JPA layer only
- @MockBean → mock a Spring bean
- @Test → test method

**Best Practices**:
- Unit test services with mocks
- Slice test controllers/repositories
- Integration test critical flows
- Keep tests fast and isolated

# @Test (JUnit 5)

**Package**: `org.junit.jupiter.api.Test`

**Purpose**: Marks a method as a test case.

**Rules**:
- Must return void
- No arguments (unless parameterized)
- Fails if exception is thrown (unless expected)

**Example**:
```java
@Test
void testAddition() {
assertEquals(5, calculator.add(2, 3));
}
```

# @BeforeEach (JUnit 5)

**Package**: `org.junit.jupiter.api.BeforeEach`

**Purpose**: Runs setup code before each test method.

**Usage**:
- Initialize objects
- Reset state
- Prepare mocks/test data

**Example**:
```java
@BeforeEach
void setUp() {
calculator = new Calculator();
}

@Test
void testAddition() {
assertEquals(5, calculator.add(2, 3));
}
```

# @AfterEach (JUnit 5)

**Package**: `org.junit.jupiter.api.AfterEach`

**Purpose**: Runs cleanup code after each test method.

**Usage**:
- Release resources
- Reset variables
- Clear test data

**Example**:
```java
@BeforeEach
void setUp() { calculator = new Calculator(); }

@Test
void testAddition() { assertEquals(5, calculator.add(2, 3)); }

@AfterEach
void tearDown() { calculator = null; }
```

# @BeforeAll (JUnit 5)

**Package**: `org.junit.jupiter.api.BeforeAll`

**Purpose**: Runs setup code once before all tests in the class.

**Usage**:
- Initialize expensive resources (DB, server, external service).
- Must be static (unless @TestInstance(PER_CLASS)).
- Complements @AfterAll for cleanup.

**Example**:
```java
@BeforeAll
static void initAll() {
connection = DatabaseConnection.start();
}
```

# @AfterAll (JUnit 5)

**Package**: `org.junit.jupiter.api.AfterAll`

**Purpose**: Runs cleanup code once after all tests in the class.

**Usage**:
- Release expensive resources (DB, server, files).
- Must be static (unless @TestInstance(PER_CLASS)).
- Complements @BeforeAll for setup.

**Example**:
```java
@AfterAll
static void tearDownAll() {
connection.close();
}
```

