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


# Using @Value with a Custom Property File

**Steps**:
1. Create a `.properties` file in `src/main/resources` (e.g., `custom.properties`).
2. Load it with `@PropertySource("classpath:custom.properties")` in a `@Configuration` class.
3. Use `@Value("${key}")` to inject values.

**Example**:
@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {
@Value("${mail.host}") private String host;
@Value("${mail.port}") private int port;
}