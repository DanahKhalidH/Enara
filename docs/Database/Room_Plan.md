Enara– Room Database Plan

1. Overview

This section describes the Room Database design for the Enarah application. Room (SQLite) is used as the local database to organize and manage application data, including users, schools, reviews, favorites, autism levels, services, and specialists.

The database structure follows the entities and relationships defined in the relational schema. DAO interfaces provide the required database operations, while ViewModels use these operations to retrieve and manage data displayed in the application.

2. DAO Interfaces and Key Queries

The following DAO interfaces define the main database operations. Each query includes its purpose and the ViewModel responsible for using it.

1. UserDao

* getUserById(userId): Retrieves the information of a specific user.
* insertUser(user): Inserts a new user into the database.
* updateUser(user): Updates the existing user’s information.

Used by: ProfileViewModel.

2. SchoolDao

* getAllSchools(): Retrieves all available schools and educational centers.
* getSchoolById(schoolId): Retrieves the details of a selected school.
* searchSchools(query): Searches for schools by name or relevant keywords.
* filterSchools(city, schoolType, autismLevelId): Filters schools based on the selected city, school type (public or private), and accepted autism level.

Used by: SchoolViewModel.

3. ReviewDao

* getReviewsBySchool(schoolId): Retrieves all reviews associated with a selected school.
* insertReview(review): Adds a new review to the database.
* deleteReview(reviewId): Deletes a specific review.
* getAverageRating(schoolId): Calculates the average rating of a selected school.
* getReviewCount(schoolId): Retrieves the total number of reviews for a selected school.

Used by: ReviewViewModel and SchoolViewModel.

4. FavoriteDao

* getFavoritesByUser(userId): Retrieves all favorite schools saved by a specific user.
* addFavorite(favorite): Adds a school to the user’s favorites.
* removeFavorite(userId, schoolId): Removes a school from favorites using the composite primary key (userId, schoolId).

Used by: FavoritesViewModel.

5. AutismLevelDao

* getAllLevels(): Retrieves all available autism levels (Level 1, Level 2, and Level 3).

Used by: FilterViewModel.

6. SchoolAutismLevelDao

* getLevelsBySchool(schoolId): Retrieves the autism levels accepted by a specific school.
* getSchoolsByLevel(levelId): Retrieves schools that accept a selected autism level.

Used by: SchoolViewModel and FilterViewModel.

7. ServiceDao

* getAllServices(): Retrieves all available services offered by schools and educational centers.

Used by: SchoolDetailsViewModel.

8. SchoolServiceDao

* getServicesBySchool(schoolId): Retrieves all services associated with a selected school.
* getSchoolsByService(serviceId): Retrieves schools that offer a specific service.

Used by: SchoolDetailsViewModel.

9. SpecialistDao

* getAllSpecialists(): Retrieves all specialists registered in the database.

Used by: SchoolDetailsViewModel.

10. SchoolSpecialistDao

* getSpecialistsBySchool(schoolId): Retrieves all specialists associated with a selected school.
* getSchoolsBySpecialist(specialistId): Retrieves schools associated with a specific specialist.

Used by: SchoolDetailsViewModel.

3. Entity Classes (Kotlin Names)

The Room database contains the following Entity classes corresponding to the relational database tables:

1. UserEntity
2. SchoolEntity
3. ReviewEntity
4. FavoriteEntity
5. AutismLevelEntity
6. SchoolAutismLevelEntity
7. ServiceEntity
8. SchoolServiceEntity
9. SpecialistEntity
10. SchoolSpecialistEntity

Each Entity represents a database table and defines its fields, primary keys, foreign keys, and constraints.

The FavoriteEntity uses a composite primary key consisting of (userId, schoolId). The junction entities SchoolAutismLevelEntity, SchoolServiceEntity, and SchoolSpecialistEntity represent the many-to-many relationships between schools and their related data.

4. Type Converters / Enums Needed

AutismLevelType

The autism level can be represented using an enum with the following values:

* LEVEL_1
* LEVEL_2
* LEVEL_3

DateConverter

A DateConverter is only required if the application uses Kotlin Date objects in its Entity classes. It converts Date objects into Long timestamps and converts timestamps back into Date objects.

If date fields such as createdAt are already stored as Long values, no DateConverter is required for those fields.

5. Sample (Seed) Data: What and Where From

The initial sample data includes:

* Schools and educational centers.
* Autism levels (Level 1, Level 2, and Level 3).
* Available services.
* Specialists.
* Sample user and review records for testing.

Data Source: Manually prepared sample data based on the Enarah project information collected by the team.

The sample data can be inserted into the Room database using RoomDatabase.Callback during initial database creation.

6. Remote Source / Mock API

Firebase Cloud Firestore serves as the proposed remote data source for storing and retrieving school information, services, specialists, and other application data.

Room (SQLite) acts as the local database and stores a cached copy of the required data to support offline access.

Data Flow and Synchronization:

1. Firebase Cloud Firestore acts as the primary remote data source for school-related information.
2. The Repository retrieves data from Firebase.
3. Retrieved data is saved or updated in the local Room database.
4. ViewModels observe Room data and provide it to the application screens.
5. When updated information is available from Firebase, the Repository updates the corresponding Room records.
6. The application can display previously cached data when an internet connection is unavailable.

This approach separates remote data management from local data access and supports the application’s data requirements.

7. Database Responsibilities

The database layer is responsible for:

* Storing and organizing application data.
* Retrieving school information and details.
* Searching and filtering schools by city, school type, and autism level.
* Managing user favorites and reviews.
* Calculating school average ratings and review counts.
* Retrieving available services and specialists.
* Maintaining relationships between database entities.
* Supporting local data access through Room.


The proposed Room Database structure provides an organized approach to managing Enarah application data. The ten Entity classes represent the database tables, while the ten DAO interfaces provide the required queries and database operations.

The Repository coordinates data access between Firebase Cloud Firestore and the local Room database, while ViewModels connect database operations to the application screens.

This design supports the main Enarah features, including school searching, filtering, reviews, favorites, services, and specialist information.

