Enara – Database Schema

1. Overview

The Enara application uses Room (SQLite) as its local database. The database schema is designed to organize user information, schools, autism levels, reviews, favorites, services, and specialists.

The schema consists of 10 entities and their relationships, including junction tables to implement many-to-many relationships.

2. Database Tables

User

Stores user information.
- `userId`: Int, Primary Key, auto-generated.
- `name`: String, NOT NULL.
- `email`: String, NOT NULL.
- `preferredCity`: String, NOT NULL.

School

Stores school information.
- `schoolId`: Int, Primary Key, auto-generated.
- `name`: String, NOT NULL.
- `city`: String, NOT NULL.
- `address`: String, NOT NULL.
- `schoolType`: String, NOT NULL.
- `phone`: String, optional.
- `email`: String, optional.

AutismLevel

Stores autism level names and descriptions.
- `levelId`: Int, Primary Key, auto-generated.
- `levelName`: String, NOT NULL.
- `description`: String, NOT NULL.

Review

Stores school ratings, comments, and review creation dates.
- `reviewId`: Int, Primary Key, auto-generated.
- `userId`: Int, Foreign Key referencing User, NOT NULL.
- `schoolId`: Int, Foreign Key referencing School, NOT NULL.
- `rating`: Double, NOT NULL, CHECK between 1.0 and 5.0.
- `comment`: String, optional.
- `createdAt`: Long, NOT NULL, stored as epoch milliseconds.

Favorite

Stores schools saved by users.
- `userId`: Int, Primary Key and Foreign Key referencing User, NOT NULL.
- `schoolId`: Int, Primary Key and Foreign Key referencing School, NOT NULL.

The combination of `userId` and `schoolId` forms a composite primary key.

SchoolAutismLevel

Connects schools with the autism levels they accept.
- `schoolId`: Int, Primary Key and Foreign Key referencing School, NOT NULL.
- `levelId`: Int, Primary Key and Foreign Key referencing AutismLevel, NOT NULL.

The combination of `schoolId` and `levelId` forms a composite primary key.

Service

Stores service names and descriptions.
- `serviceId`: Int, Primary Key, auto-generated.
- `serviceName`: String, NOT NULL.
- `description`: String, NOT NULL.

SchoolService

Connects schools with the services they offer.
- `schoolId`: Int, Primary Key and Foreign Key referencing School, NOT NULL.
- `serviceId`: Int, Primary Key and Foreign Key referencing Service, NOT NULL.

The combination of `schoolId` and `serviceId` forms a composite primary key.

Specialist

Stores specialist names and specialties.
- `specialistId`: Int, Primary Key, auto-generated.
- `name`: String, NOT NULL.
- `specialty`: String, NOT NULL.

SchoolSpecialist

Connects schools with their associated specialists.
- `schoolId`: Int, Primary Key and Foreign Key referencing School, NOT NULL.
- `specialistId`: Int, Primary Key and Foreign Key referencing Specialist, NOT NULL.

The combination of `schoolId` and `specialistId` forms a composite primary key.

3. Primary Keys and Foreign Keys

- Each main entity has its own primary key.
- Foreign keys maintain references between related tables.
- Junction tables use composite primary keys to represent many-to-many relationships.
- Foreign key relationships are designed with CASCADE delete behavior.

4. Constraints

The schema uses the following constraints:
- NOT NULL: Ensures required fields contain values.
- CHECK: Restricts school ratings to values between 1.0 and 5.0.
- Auto-generated IDs: Automatically generates identifiers for main entities.
- Composite Primary Keys: Prevents duplicate combinations in junction tables.

5. Summary

The Enara database schema contains 10 tables designed to support the application's main features, including user profiles, school information, school reviews, favorites, autism levels, services, and specialists. The schema provides primary keys, foreign keys, and constraints to organize and maintain data relationships.
