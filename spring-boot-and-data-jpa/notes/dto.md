# DTO Patterns in Spring Boot

**Definition**: DTO (Data Transfer Object) is a plain object used to transfer data between layers, decoupling entities from API contracts.

**Benefits**:
- Security: Hide sensitive fields
- Decoupling: Entities evolve independently
- Performance: Lighter payloads
- Flexibility: Different DTOs for different views
- Validation: Separate request validation

**Patterns**:
1. Simple DTO → Flat object
2. Request vs Response DTOs → Separate input/output
3. Nested DTOs → Contain other DTOs
4. Projection DTOs → Optimized for queries
5. Mapper Pattern → Entity ↔ DTO conversion (manual, ModelMapper, MapStruct)

**Example**:
```java
public class UserRequestDTO { String name; String email; }
public class UserResponseDTO { Long id; String name; String email; }
```