 
Readme · MD
# Enara
Enara — School Finder for Children with Autism
 
## Project Overview
Enara is a mobile application designed to help parents and guardians of children with Autism Spectrum Disorder (ASD) find suitable schools in the Eastern Province of Saudi Arabia. The application provides a centralized platform where users can explore schools and compare their information based on the child's support needs and the school's available services.
 
## Problem Statement
Finding a suitable school for a child with Autism Spectrum Disorder can be challenging for parents. Information about schools, accepted autism support levels, and available services may be scattered across different sources. Parents often need to contact schools individually to determine whether they can meet their child's needs.
 
Enara aims to simplify this process by bringing relevant school information together in one accessible application.
 
## Proposed Solution
Enara allows parents to search for schools based on the child's autism support level, location, and school type. Each school listing provides useful information, available services, contact details, and location information to help parents make informed decisions.
 
The application serves as a school discovery and information platform; the official enrollment process remains the responsibility of each school.
 
## Main Features
| Feature | Priority | Description |
|---|---|---|
| Search & Filter | Must | Find schools by name, autism support level, city and school type |
| School Details | Must | School information, photo, accepted support levels, services, rating and "Last updated" |
| Contact Information | Must | Call, WhatsApp or email the school in one tap |
| Autism Level Guide | Must | Plain-language explanation of Levels 1–3 that opens pre-filtered results |
| Map and Directions | Should | View school locations and open directions in Google Maps |
| Favorites | Should | Save schools for easier access and comparison |
| Parent Reviews | Should | Read and write reviews with a star rating |
| Services & Specialists | Should | Therapies and specialists offered by each school |
| Profile & Preferred City | Could | Name, email and preferred city |
| School Data Management | Could | Support updating school information to keep listings accurate (planned later) |
 
## Target Users
- Parents and guardians of children with Autism Spectrum Disorder.
- Schools that provide educational services for children with autism.
---
 
## UI/UX Design
- **Figma prototype (15 screens):** https://www.figma.com/design/aUx91ELt3xh3LTbzbP7oL9/Untitled?node-id=0-1&t=s9wH6TjSDQCnWfvM-1
- **Style guide:** [`docs/style-guide.md`](docs/style-guide.md) — Material 3, light theme, RTL-ready
- **Navigation map:** [`docs/navigation-map.png`](docs/navigation-map.png)
- **Wireframes:** [`docs/wireframes/`](docs/wireframes/) — one image per screen
| # | Screen | Wireframe |
|---|---|---|
| 1 | Welcome | `Welcome.png` |
| 2 | My Profile | `Profile.png` |
| 3 | Home | `Home.png` |
| 4 | Search Schools | `Search.png` |
| 5 | Filters | `Filters.png` |
| 6 | Search Results | `Result.png` |
| 7 | School Details | `Details.png` |
| 8 | School Review | `Review.png` |
| 9 | Services & Specialists | `Services.png` |
| 10 | Maps | `Maps.png` |
| 11 | Contact School | `ContactSchool.png` |
| 12 | Favorites | `Favorites.png` |
| 13 | Autism Level Guide | `Levels.png` |
| 14 | No School Found (empty state) | `NoSchoolFound.png` |
| 15 | School Location | `Location.png` |
 
---
 
## Architecture
Enara follows a three-layer architecture (UI / Domain / Data) with Unidirectional Data Flow: user events go down, state comes back up.
 
```
UI      →  Compose screens · ViewModels · UiState / UiEvent · NavHost
Domain  →  Use cases (SearchSchools, ApplyFilters, GetSchoolDetails, GetSchoolReviews, ManageFavorites, ...)
           Models · Repository interfaces
Data    →  Repository implementations · SchoolRemoteDataSource · FavoritesLocalDataSource · mappers
```
 
Details: [`docs/architecture.md`](docs/architecture.md) · Diagram: [`docs/architecture.png`](docs/architecture.png)
 
## Technologies
- **Language:** Kotlin (Android)
| Layer | Libraries |
|---|---|
| UI | Jetpack Compose, Material 3, Navigation Compose, Lifecycle ViewModel, Coil, Maps Compose |
| Domain | Kotlin Coroutines & Flow |
| Data | Room, DataStore, Firebase Firestore |
| Cross-layer | Hilt (dependency injection) |
 
The reason for each library is documented in the Milestone 2 submission, section **B3**.
 
---
 
## Documentation (`/docs`)
| File | Content |
|---|---|
| [`style-guide.md`](docs/style-guide.md) | Colors, typography, spacing, Material 3 components |
| [`navigation-map.png`](docs/navigation-map.png) | Screen flow and navigation actions |
| [`architecture.md`](docs/architecture.md) | Layers, responsibilities and data flow |
| [`architecture.png`](docs/architecture.png) | Architecture diagram |
| [`ai-use-log.md`](docs/ai-use-log.md) | AI tool usage log |
| [`wireframes/`](docs/wireframes/) | One image per screen |
 
## Course Information
- **Course:** CSC 402 — Mobile Application Programming
- **Project:** Enara
- **Group:** Group 1
- **Instructor:** Dr. Nourah Alqahtani
 
 
## Disclaimer
School information and available services should be verified with the relevant schools. Enara helps families discover and compare educational options but does not guarantee admission or replace direct communication with schools.
