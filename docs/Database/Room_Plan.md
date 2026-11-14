Enara – Room Database Plan

CSC 402 – Milestone 3-A: Database Design

1. Overview

This section describes the Room Database plan for the Enarah application. Room (SQLite) is used as the local database to organize and manage application data, including users, schools, reviews, favorites, autism levels, services, and specialists.

2. DAO Interfaces and Key Queries

The database uses DAO interfaces to perform operations such as inserting, updating, deleting, and retrieving data.

UserDao: Retrieves user information using getUserById(), inserts new users using insertUser(), and updates user information using updateUser(). Used by ProfileViewModel.

SchoolDao: Retrieves all schools using getAllSchools(), retrieves a specific school using getSchoolById(), and searches schools using searchSchools(). Used by SchoolViewModel.

ReviewDao: Retrieves reviews using getReviewsBySchool(), adds reviews using insertReview(), and deletes reviews using deleteReview(). Used by ReviewViewModel.

FavoriteDao: Retrieves user favorites using getFavoritesByUser(), adds favorites using addFavorite(), and removes favorites using removeFavorite(). Used by FavoritesViewModel.

AutismLevelDao: Retrieves all autism levels using getAllLevels(). Used by FilterViewModel.

SchoolAutismLevelDao: Retrieves autism levels accepted by a school using getLevelsBySchool() and finds schools by autism level using getSchoolsByLevel(). Used by SchoolViewModel.

ServiceDao: Retrieves all available services using getAllServices(). Used by SchoolDetailsViewModel.

SchoolServiceDao: Retrieves services offered by a school using getServicesBySchool() and finds schools offering a specific service using getSchoolsByService(). Used by SchoolDetailsViewModel.

SpecialistDao: Retrieves all specialists using getAllSpecialists(). Used by SchoolDetailsViewModel.

SchoolSpecialistDao: Retrieves specialists working at a specific school using getSpecialistsBySchool() and finds schools associated with a specialist using getSchoolsBySpecialist(). Used by SchoolDetailsViewModel.

3. Entity Classes (Kotlin Names)

The proposed Room Entity classes correspond to the database tables:

UserEntity, SchoolEntity, ReviewEntity, FavoriteEntity, AutismLevelEntity, SchoolAutismLevelEntity, ServiceEntity, SchoolServiceEntity, SpecialistEntity, and SchoolSpecialistEntity.

Each Entity represents a database table and defines its fields, primary keys, and foreign keys where required.

4. Type Converters / Enums Needed

AutismLevelType includes three possible values: LEVEL_1, LEVEL_2, and LEVEL_3.

DateConverter converts Date objects into Long timestamps and converts timestamps back into Date objects when needed.

No additional TypeConverters are required for primitive data types.

5. Sample (Seed) Data: What and Where From

The sample data includes schools, autism levels, available services, and specialists.

The data source is manually prepared test data based on the Enarah project information collected by the team.

6. Remote Source / Mock API

Room (SQLite) is identified as the local database for the Enarah application.

If Firebase Cloud Firestore is adopted by the team, it can be used as a remote data source for storing and retrieving school information, services, and specialists. Room can then store locally cached data and support offline access.

7. Database Responsibilities

The database layer is responsible for storing and organizing application data, retrieving school information, managing user favorites and reviews, supporting autism-level filtering, and retrieving school services and specialists.

It also maintains relationships between the different database entities.
