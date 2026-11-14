# Enara – Entities at a Glance

### Overview

The Enara application uses several entities to organize and manage school information, autism levels, favorites, reviews, services, and specialists. The database design supports the application's main features, including searching for schools, filtering results, viewing school details, and saving favorite schools.

The system includes 10 main entities and junction tables to manage relationships between schools and other entities.

### Main Entities

**User**

Stores user information, including name, email, and preferred city.

**School**

Stores school information, including name, city, address, school type, and contact details.

**AutismLevel**

Stores the three autism levels (Level 1, Level 2, and Level 3) and their descriptions.

**Review**

Stores parent ratings and comments about schools.

**Favorite**

Stores schools saved by users for later access.

**Service**

Stores information about the services and therapies offered by schools.

**Specialist**

Stores information about specialists and their specialties.

### Junction Entities

**SchoolAutismLevel**

Connects schools with the autism levels they accept.

**SchoolService**

Connects schools with the services they offer.

**SchoolSpecialist**

Connects schools with their associated specialists.

### Data Storage

The Enara application uses Room (SQLite) to store school information and favorites locally. DataStore Preferences is used to store user profile information, including name, email, and preferred city. Firebase Firestore is used to store parent reviews and ratings.

### Summary

The Enara database design includes 10 entities that support the application's main features. These entities organize school information, autism levels, services, specialists, favorites, and reviews. Junction tables manage many-to-many relationships, while the different data storage technologies support local data access and shared information.
