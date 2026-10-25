# Enarah — System Architecture

## 1. Architecture Overview

Enarah is an application designed to help parents find suitable schools and support services for children on the autism spectrum. The application follows a three-layer architecture consisting of the UI Layer, Domain Layer, and Data Layer. Each layer has a specific responsibility to improve separation of concerns, maintainability, and organization.

## 2. UI Layer

### Responsibility

The UI Layer displays the application screens, receives user input, manages navigation, and presents information to users.

### Screens

The Enarah application contains the following 15 screens:

1. Enarah Main Screen
2. My Profile
3. Home
4. Search Schools
5. Filters
6. Search Results
7. School Details
8. School Review
9. Services & Specialists
10. Maps
11. Contact School
12. Favorites
13. Autism Level Guide
14. Search Result
15. School Location

### Key Classes

- Screens and UI components
- ViewModels, if used
- UiState and UiEvent, if used
- Navigation components

### Rules

- The UI Layer should not directly access databases or remote data sources.
- Business logic should be handled by the Domain Layer.
- The UI should display information and respond to user interactions.

## 3. Domain Layer

### Responsibility

The Domain Layer handles the application’s business logic and coordinates operations related to school searching, filtering, school details, reviews, favorites, services, and locations.

### Key Classes

- SearchSchoolsUseCase
- ApplyFiltersUseCase
- GetSchoolDetailsUseCase
- GetSchoolReviewsUseCase
- ManageFavoritesUseCase
- GetServicesAndSpecialistsUseCase
- SchoolRepository
- FavoriteRepository

These are proposed class names and should be adjusted to match the actual project implementation.

### Rules

- The Domain Layer should not depend on UI components.
- Business rules should be separated from data-source implementation details.
- Repository interfaces define how the Domain Layer requests data.

## 4. Data Layer

### Responsibility

The Data Layer retrieves, organizes, and stores application data required by the Domain Layer. It handles school information and, where supported, reviews and favorite schools.

### Key Classes

- SchoolRepositoryImpl
- FavoriteRepositoryImpl
- ReviewRepositoryImpl, if required
- Data sources
- Data models and mappers, if used

These are proposed class names. The implementation should reflect the actual data sources and technologies selected by the team.

### Rules

- The Data Layer implements repository interfaces defined by the Domain Layer.
- Data-source details should remain inside the Data Layer.
- The Data Layer should not directly control UI screens.

## 5. Layer Responsibilities

| Layer | Responsibility | Key Classes | Rule |
|---|---|---|---|
| UI | Displays screens, receives user input, and handles navigation. | Screens, ViewModels, UiState, UiEvent, Navigation components | Must not directly access data sources or contain business logic. |
| Domain | Handles business rules and coordinates application operations. | Use Cases, domain models, repository interfaces | Must not depend on UI or data-source implementation details. |
| Data | Retrieves and stores data and implements repository interfaces. | Repository implementations, data sources, data models, mappers | Must not directly control UI screens. |

## 6. Relationships Between Layers

The UI Layer sends user actions and requests to the Domain Layer. The Domain Layer applies the required business logic and requests data through repository interfaces. The Data Layer retrieves or stores the required information and returns the results through the repositories. The Domain Layer returns the results to the UI Layer, which updates the displayed information.

Request direction: UI → Domain → Data

Response direction: Data → Domain → UI

## 7. Architecture Diagram

The architecture diagram is provided separately as `architecture.png`. It illustrates the three layers, their responsibilities, the planned classes, and the direction of requests and results.

## 8. Additional Data Flow Documentation

The detailed Unidirectional Data Flow explanation and the six-step feature walk-through will be added by the team member responsible for that section, in coordination with the System Architecture documentation.
