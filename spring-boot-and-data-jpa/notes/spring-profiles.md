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