# Possible approaches to secure your data
You should divide business logic from security logic as much as possible, which
means entities from security domain should not be mixed with application domain entities
(separation of concerns).

## AccessDecisionVoter
You can use custom implementation of the AccessDecisionVoter. It requires the
custom Granted Authorities which are related to domain objects (customer_write_to_books).
* You can end with Roles explosion problem
* A lot of metadata in Authorization token   

## Spring Security ACL
Spring Security provides the ACL (Access Control List) module, you van use to
implement data level security. Every domain object instance in your system should
have its own ACL, and the ACL records details of who can and can't work with that domain object.

* Works with JDBC/JPA only (NoSQL is not supported)
* Entities should have int/long IDs
* Each entity must have ACL records
* ACL requires cache

## Custom Method security expression

## Your own custom model and implementation

## Spring Security Data

* exposes Spring Security as SpEL expressions for creating Spring Data queries
* you can use the prePost like expressions
