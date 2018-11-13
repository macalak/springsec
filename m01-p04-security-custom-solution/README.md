# Example of custom Spring Security components

## Custom AuthenticationProvider
You can implement your own AuthenticationProvider responsible for Authentication object 
validation. If the Authentication is instance of UsernamePasswordAuthenticationToken 
created by standard login form, you simply validate provided password credential.

## Custom Authentication object
You can implement additional attribute/factor to password credential. You can introduce
custom Authentication object where the additional factor is included.   
 
