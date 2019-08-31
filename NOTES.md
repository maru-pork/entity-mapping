## Entity Associations 
- References from one entity to another
- Mapped to underlying database tables that support the relationship
- Source Object/Target Object
- Bidirectional/Unidirectional
- ***When using bidirectional mapping, we need to manage both side of the relationships.***

## Types of Entity Associations
- One to One i.e. One account is associated with one set of credentials
- One to Many i.e. One account is associated with many transactions
- Many to One i.e. Many transactions are associated with one account
- Many to Many i.e. Many users can be associated with many accounts
![entity-mapping](https://github.com/rufomaryann/entity-mapping/blob/master/src/main/resources/script/entity-mapping.jpg)

## CrudRepository vs PagingAndSortingRepository vs JpaRepository
```
CrudRepository
PagingAndSortingRepository extends CrudRepository
JpaRepository extends PagingAndSortingRepository
```
- The CrudRepository interface provides methods for CRUD operations, so it allows you to create, read, update and delete records without having to define your own methods.
- The PagingAndSortingRepository provides additional methods to retrieve entities using pagination and sorting.
- Finally the JpaRepository add some more functionality that is specific to JPA.

## Database Setup
```
  DROP DATABASE one_to_one;
  DROP DATABASE one_to_many;
  DROP DATABASE many_to_many;
  CREATE DATABASE one_to_one;
  CREATE DATABASE one_to_many;
  CREATE DATABASE many_to_many;
```
