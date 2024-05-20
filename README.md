# userservice
# Features to be implemented
- [X] User Sign Up : Check if the user already exists
- [X] User login : Check if the user exists and the password is correct. Throw a custom exception if the user does not exist or the password is incorrect.
- [X] Auth service: Return a Response DTO in login function instead of ResponseEntity

# Notes
- Filters in spring security are used to intercept the request and response. Filters are used to perform operations before and after the request is processed. Filters are used to perform operations such as logging, auditing, security etc.
- @Bean annotation tells that a method produces a bean to be managed by the Spring container. We are creating a bean of SecurityFilterChain to be used in the SecurityConfig class.
- Creating a bean of SecurittyFilterChain is necessary to overwrite the default security filter chain provided by Spring Security.