# Spring Environment Abstraction

**Definition**: Central interface in Spring that manages:
1. **Profiles** → Conditional bean loading.
2. **Properties** → Unified access to configuration values.

**Property Sources**:
- application.properties / application.yml
- Environment variables
- JVM system properties
- Command-line arguments
- JNDI, servlet context params, etc.

**Usage**:
@Autowired Environment env → env.getProperty("key")
Profiles → @Profile("dev"), spring.profiles.active=dev