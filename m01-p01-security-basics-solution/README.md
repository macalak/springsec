# Example of simple Spring Boot with Security application 
![design](docs/images/LM-library-module-security-overview.jpg)

## Spring security defaults
The Spring Security protects endpoint by HTTP Basic Authorization. It generates the default user and random password.
Notice the generated password is logged during application start.
```
Using generated security password: <value>
``` 
You can tune default user/password by _spring.security.user.name_ and _spring.security.user.password_ cfg properties.

