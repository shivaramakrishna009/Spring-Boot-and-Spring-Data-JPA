# Spring Profiles

**Definition**: Named groups of bean definitions/configurations loaded only when the profile is active.

**Benefits**:
- Environment-specific beans/configs
- Feature toggling
- Cleaner configuration management

**Usage**:
- Annotate beans/config classes with @Profile("profileName")
- Activate via:
    - application.properties → spring.profiles.active=dev
    - JVM arg → -Dspring.profiles.active=prod
    - Programmatically via Environment API
- Use profile-specific property files (application-{profile}.properties/yml)

**Advanced**:
- !profile → NOT
- profile1 & profile2 → AND
- profile1 | profile2 → OR

# @Profile Annotation

**Purpose**: Conditionally register beans/configs based on active profiles.

**Usage**:
- @Profile("profileName") on @Component, @Configuration, or @Bean.
- Activate profiles via:
    - application.properties → spring.profiles.active=dev
    - Command line → --spring.profiles.active=prod
    - Programmatically via Environment API