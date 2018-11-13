# Example of OpenID connect with Keycloak IAM

## Keycloak server
Keycloak IAM server is Wildfly AS based application and can be downloaded [here](https://www.keycloak.org/downloads.html).
It acts as Authentication server and maintains User accounts, security groups, roles, and more. 

## Spring Boot application
There is Keycloak adapter for Spring Security framework available. You can utilize Spring Boot Keycloak starter to ease
bootstrap of Keycloak adapter components: _org.keycloak:keycloak-spring-boot-2-starter:4.0.0.Final_.

Check following:
* org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
* org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider
* org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken

## OpenID Connect
The [OpenID Connect](https://openid.net/connect/) is a simple identity layer on top of the OAuth 2.0 protocol.
Refer [introduction](http://www.youtube.com/watch?feature=player_embedded&v=Kb56GzQ2pSk) and 
[core specification](https://openid.net/specs/openid-connect-core-1_0.html) for more info.

## Explore JWT Token
Use [jwt.io](https://jwt.io/) site to explore JWT tokens. Paste your token issued by Keycloak and
check the token attributes.

