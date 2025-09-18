# Dependency Injection in Spring

**Definition**: A design pattern where the IoC container provides an object's dependencies instead of the object creating them.

**Benefits**:
- Loose coupling
- Easier testing
- Better maintainability

**Types**:
1. **Constructor Injection** → Preferred for required dependencies and immutability.
2. **Setter Injection** → For optional or changeable dependencies.
3. **Field Injection** → Avoid in production; use for quick prototypes.

**Best Practice**:
Prefer Constructor Injection → Setter Injection → Field Injection (last resort).



# @Qualifier in Spring

**Purpose**: Resolves ambiguity when multiple beans of the same type exist.

**Usage**:
- Annotate beans with a name: @Component("beanName") or @Bean(name = "beanName")
- Use @Qualifier("beanName") with @Autowired or constructor parameters.

**Example**:
@Autowired
@Qualifier("spanishGreeting")
private GreetingService greetingService;

**@Qualifier vs @Primary**:
- @Primary → Default bean when multiple exist.
- @Qualifier → Explicit bean selection (overrides @Primary).

# @Primary in Spring

**Purpose**: Marks a bean as the default choice when multiple beans of the same type exist.

**Usage**:
- Place on @Component class or @Bean method.
- Spring will inject this bean unless a @Qualifier specifies another.

**Example**:
@Component
@Primary
public class SpanishGreetingService implements GreetingService { ... }

**@Primary vs @Qualifier**:
- @Primary → Default bean for a type.
- @Qualifier → Explicit bean selection (overrides @Primary).