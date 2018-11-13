# Lab for the example of custom Spring Security components

## Custom UserDetailService
The Spring Security framework comes with several implementations of the UserDetailService.
Spring Security provides for instance InMemory, Jdbc and Ldap implementations.
The UserDetailService implementation is responsible for loading user-specific data.
You can implement your own strategy, how to store users.  

## Custom AuthenticationProvider
You can implement your own AuthenticationProvider responsible for Authentication object 
validation. If the Authentication is instance of UsernamePasswordAuthenticationToken 
created by standard login form, you simply validate provided password credential.

## Custom Authentication object
You can implement additional attribute/factor to password credential. You can introduce
custom Authentication object where the additional factor is included.

## Instructions

1. In first step you will implement custom UserDetailsService, which is used by Spring Security
framework to retrieve user details as credentials and granted authorities during the Authentication 
and Authorization process.

2. There is [AppUserDetailsService](src/main/java/ite/librarymaster/application/service/AppUserDetailsService.java) 
class prepared for you. Your task is to turn this component into the UserDetailsService. So this class should implement 
the UserDetailService interface. You require to implement the loadUserByUsername() method.

3. There is [ApplicationUserRepository](src/main/java/ite/librarymaster/infrastructure/ApplicationUserRepository.java) class
which implements the simple Ldap repository to retrieve the ApplicationUser domain object. Now, autowire this
repository to AppUserDetailsService.    

4. The Spring Security accesses user details via the UserDetails interface. So your [ApplicationUser](src/main/java/ite/librarymaster/domain/model/ApplicationUser.java)
should implements the UserDetails interface as well. 

5. Now, implement the AppUserDetailsService.loadUserByUsername() method using repository.
```java
ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
```  

6. The custom UserDetailsService should be plugged into Spring Security. Security context configuration is provided
by the [SecurityConfig](/src/main/java/ite/librarymaster/application/configuration/SecurityConfig.java) class. Just add
following code to _configure(AuthenticationManagerBuilder auth)_ method body. 

```java
auth.userDetailsService(userDetailsService)
  .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
```

7. Try to run application and use Postman to login via login form.

8. The AuthenticationManager uses one or more chained AuthenticationProvider(s) during Authentication process. You can plug-in
your custom AuthenticationProvider and configure the Spring Security to use it. 

9. There is [MyAuthenticationProvider](/src/main/java/ite/librarymaster/infrastructure/security/MyAuthenticationProvider.java)
class prepared for you. This class should implement the AuthenticationProvider interface, which defines _supports()_ and _authenticate()_
methods. The custom Auth provider should be able to authenticate standard UsernamePasswordAuthenticationToken and should
utilise the ApplicationUserRepository.

10. The _supports()_ method is already implemented. You should fix the _authenticate()_ method. Notice, on successful 
credentials verification, the _authenticate()_ returns new token with populated password and authorities. 

11. Now open the [SecurityConfig](/src/main/java/ite/librarymaster/application/configuration/SecurityConfig.java) class. Just add
following code to _configure(AuthenticationManagerBuilder auth)_ method body.  
```java
auth.authenticationProvider(myAuthenticationProvider);
```
Do not forget to delete code which configures custom UserDetailsService. It is not required anymore.
Now restart the application and verify the functionality.

12. If you for example require add additional factor (besides username and password) to Authentication, you can
implement custom Authentication Object. In this example, you introduce PIN, which user should provide along username
and password during login.

13. Spring Security Core provides several implementations of the Authentication interface. For example UsernamePasswordAuthenticationToken,
or JaasAuthenticationToken. More implementations come with other Spring Security modules. Now introduce the new Token
as Authentication implementation which extends the UsernamePasswordAuthenticationToken and adds PIN attribute. There is 
[MyAuthenticationWithPinToken](/src/main/java/ite/librarymaster/infrastructure/security/MyAuthenticationWithPinToken.java)
class already prepared. Just check TODOs and implement missing stuff.

14. If the MyAuthenticationWithPinToken is ready, you cen implement custom Authentication provider, which is able 
to authenticate newly introduced MyAuthenticationWithPinToken. The [MyAuthenticationWithPinProvider](/src/main/java/ite/librarymaster/infrastructure/security/MyAuthenticationWithPinProvider.java)
class skeleton is already prepared for you.    

15. Now open the [SecurityConfig](/src/main/java/ite/librarymaster/application/configuration/SecurityConfig.java) class. Just add
    following code to _configure(AuthenticationManagerBuilder auth)_ method body.
```java
auth.authenticationProvider(myAuthenticationWithPinProvider);
```
Do not forget to delete code you introduced in step 11

16. You need to introduce custom Filter into Filter chain to grab PIN attribute from a login request. In this filter
you need to build the custom Authentication object with PIN. There is [MyCustomAuthenticationFilter](/src/main/java/ite/librarymaster/infrastructure/security/MyCustomAuthenticationFilter.java),
class which requires implementation.

```java
MyAuthenticationWithPinToken myAuthenticationToken =new MyAuthenticationWithPinToken(username, password, pin);
super.setDetails(request, myAuthenticationToken);
```

17. Now open the [SecurityConfig](/src/main/java/ite/librarymaster/application/configuration/SecurityConfig.java) class and
configure the MyCustomAuthenticationFilter. Code goes into _configure(HttpSecurity http)_ method.
```java
 .and()
  .addFilterAt(new MyCustomAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
 .formLogin();
```
Now restart the application and verify the functionality.
 
