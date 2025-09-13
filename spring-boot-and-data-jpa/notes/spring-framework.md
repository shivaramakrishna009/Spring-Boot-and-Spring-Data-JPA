**Spring Framework**: A lightweight, modular Java framework that provides comprehensive infrastructure support
for developing enterprise applications. It promotes loose coupling through Dependency Injection, 
separates cross-cutting concerns with AOP, and offers modules for web, data, security, and integration.

**Core Features of Spring**: IoC and DI for loose coupling, AOP for cross-cutting concerns, transaction management,
MVC for web apps, integration with multiple technologies, security, data access abstraction, batch processing,
and a modular architecture that lets you pick only what you need.

**Spring Bean**: A Java object managed by the Spring IoC container.
- Created, configured, and destroyed by Spring.
- Defined via annotations (`@Component`, `@Service`, `@Repository`, `@Controller`), Java config (`@Bean`), or XML.
- Lifecycle: Instantiation → Dependency Injection → Initialization → Ready → Destruction.
- Default scope: Singleton.
# Spring IoC Container Lifecycle

1. **Bootstrap**
    - Create ApplicationContext
    - Load environment, profiles, properties

2. **Bean Definition Loading**
    - Scan classpath or read config
    - Register BeanDefinitions

3. **Instantiation & DI**
    - Create bean instances
    - Inject dependencies

4. **Bean Post-Processing**
    - Apply BeanPostProcessors
    - Create proxies (AOP, transactions)

5. **Initialization**
    - Call @PostConstruct, afterPropertiesSet(), init-method

6. **Application Ready**
    - ContextRefreshedEvent published
    - Beans ready for use

7. **Runtime**
    - Beans serve requests
    - Events handled

8. **Shutdown**
    - ContextClosedEvent published
    - Call @PreDestroy, destroy(), destroy-method

# Spring Bean Lifecycle

1. **Container Startup**
    - ApplicationContext created
    - BeanDefinitions loaded

2. **Instantiation**
    - Bean object created

3. **Dependency Injection**
    - Dependencies injected

4. **Aware Interfaces (Optional)**
    - BeanNameAware, BeanFactoryAware, ApplicationContextAware

5. **BeanPostProcessor (Before Init)**
    - postProcessBeforeInitialization()

6. **Initialization**
    - @PostConstruct
    - afterPropertiesSet()
    - init-method

7. **BeanPostProcessor (After Init)**
    - postProcessAfterInitialization()

8. **Bean Ready**
    - Available for use

9. **Destruction**
    - @PreDestroy
    - destroy()
    - destroy-method



# Spring Java-based Configuration

- **@Configuration**: Marks a class as a source of bean definitions.
- **@Bean**: Declares a bean to be managed by Spring.
- **AnnotationConfigApplicationContext**: Loads Java-based configuration.

**Example:**
```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```


# Mixed Spring Configuration

- **@Configuration** → Defines beans explicitly.
- **@ComponentScan** → Auto-detects beans in specified packages.
- **@Bean** → Registers beans that can't be autodetected.

**Example:**
```java
@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {
    @Bean
    public ExternalLibrary externalLibraryBean() {
        return new ExternalLibrary();
    }
}
```

# Spring Components

- **@Component**: Marks a class as a Spring-managed bean.
- **Specializations**:
    - @Service → Business logic layer
    - @Repository → Data access layer (adds exception translation)
    - @Controller → MVC controller
    - @RestController → REST API controller
- **Detection**: Works with @ComponentScan (implicit in Spring Boot).
- **Best Practice**: Use the specialized annotation that matches the class role.

# @ComponentScan in Spring

- **Purpose**: Tells Spring where to search for @Component and its specializations.
- **Default**: Scans the package of the annotated class and all subpackages.
- **Usage**:
  @Configuration
  @ComponentScan(basePackages = "com.example")
  public class AppConfig {}

- **Filters**:
    - includeFilters → Add extra matches
    - excludeFilters → Remove unwanted matches


# Dependency Injection in Spring

**Definition**: A design pattern where the IoC container provides an object's dependencies instead of the object creating them.

**Benefits**:
- Loose coupling
- Easier testing
- Better maintainability

**Types**:
1. **Constructor Injection** → Dependencies passed via constructor. Preferred for immutability and required dependencies.
2. **Setter Injection** → Dependencies set via setter methods. Good for optional or changeable dependencies.
3. **Field Injection** → Dependencies injected directly into fields. Avoid in production; use for quick prototypes.

**Best Practice**: Prefer Constructor Injection → Setter Injection → Field Injection (last resort).

# Spring Bean Scopes

**Definition**: Scope defines the lifecycle and visibility of a bean in the Spring container.

**Types**:
1. **singleton** (default) → One shared instance per container.
2. **prototype** → New instance for every request.
3. **request** → One instance per HTTP request (web only).
4. **session** → One instance per HTTP session (web only).
5. **application** → One instance per ServletContext (web only).
6. **websocket** → One instance per WebSocket session (web only).

**Best Practice**:
- Use singleton for stateless beans.
- Use prototype for stateful or per-use beans.
- Use web scopes only in web-aware contexts.

# Pitfalls of Mixing Bean Scopes in Spring

**Problem**: Injecting a shorter-lived bean (prototype, request, session) into a longer-lived bean (singleton) can cause:
- Stale state (prototype injected once at startup)
- Thread-safety issues
- Scope resolution errors in non-web contexts

**Solutions**:
1. **@Lookup** → Spring overrides method to return fresh instance.
2. **ObjectFactory / Provider** → Lazily fetch new instance on demand.
3. **Redesign** → Keep beans of the same scope together.

**Best Practice**: Avoid storing shorter-lived beans as fields in longer-lived beans.

# Spring Bean Profiles

**Definition**: Named groups of bean definitions loaded only when the profile is active.

**Benefits**:
- Environment-specific beans
- Feature toggling
- Cleaner configuration management

**Usage**:
- Annotate beans/config classes with @Profile("profileName")
- Activate via:
    - application.properties → spring.profiles.active=dev
    - JVM arg → -Dspring.profiles.active=prod
    - Programmatically via Environment API

**Advanced**:
- !profile → NOT
- profile1 & profile2 → AND
- profile1 | profile2 → OR

# @Value Annotation in Spring

**Purpose**: Injects values into Spring-managed beans from external sources.

**Sources**:
- application.properties / application.yml
- System environment variables
- JVM args
- Literal values
- SpEL expressions

**Usage**:
- @Value("${key}") → Inject from properties
- @Value("${key:default}") → Inject with fallback
- @Value("literal") → Inject hardcoded value
- @Value("#{expression}") → Inject SpEL result

**Example**:
```java
@Value("${app.name}") private String name;
@Value("${app.version:1.0}") private String version;
```

# @ConfigurationProperties in Spring Boot

**Purpose**: Bind entire sections of application.properties/yml into type-safe POJOs.

**Benefits**:
- Groups related config
- Type-safe binding
- Supports nested objects, lists, maps
- Easy validation with JSR-303

**Usage**:
1. Define config in application.yml/properties.
2. Create a POJO with @ConfigurationProperties(prefix = "prefix").
3. Add @Component or register via @EnableConfigurationProperties.
4. Inject the POJO where needed.

**Example**:
```java
@ConfigurationProperties(prefix = "app")
public class AppProperties {
private String name;
private String version;
private Security security;
// getters/setters
}
```