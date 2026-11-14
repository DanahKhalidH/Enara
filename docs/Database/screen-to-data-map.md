# 5. Screen-to-Data Map

Each screen from the Milestone 2 navigation map, with the tables it reads and writes.

| Screen | Reads (tables / fields) | Writes (insert / update / delete) |
|---|---|---|
| Welcome | — (static screen) | — |
| My Profile | User (name, email, preferredCity) | Insert / Update User (name, email, preferredCity) |
| Home | User (name); School (schoolId, name, city, schoolType); SchoolAutismLevel + AutismLevel (levelName); Review (rating) for the average; Favorite (count of saved schools, heart icon) | Insert / Delete Favorite (userId, schoolId) |
| Search Schools | School (name) for search by school name | — (search text and selected filters are kept in the ViewModel) |
| Filters | School (city, schoolType); AutismLevel (levelId, levelName) | — |
| Search Results | School (schoolId, name, city, schoolType); SchoolAutismLevel + AutismLevel (levelName); Review (rating) for the average; Favorite (schoolId) for the heart icon | Insert / Delete Favorite (userId, schoolId) |
| School Details | School (name, city, address, schoolType); Review (rating, count); SchoolAutismLevel + AutismLevel (levelName); SchoolService + Service (serviceName); Favorite (schoolId) | Insert / Delete Favorite (userId, schoolId) |
| School Review | Review (rating, comment, createdAt) + User (name, childLevelId*) + AutismLevel (levelName) for "Parent of a Level 1 child" | Insert Review (userId, schoolId, rating, comment, createdAt) |
| Services & Specialists | SchoolService + Service (serviceName, description); SchoolSpecialist + Specialist (name, specialty) | — |
| Contact School | School (name, phone, email); WhatsApp uses the same phone number | — (Call, WhatsApp and Email open the phone's apps) |
| Favorites | Favorite (userId, schoolId) + School (name, city, schoolType); Review (rating) for the average | Delete Favorite (userId, schoolId) |
| Autism Level Guide | AutismLevel (levelId, levelName, description) | — |
| No Results | Same query as Search Results, returns 0 rows; shows the selected filters from the ViewModel | — |
| Maps | School (schoolId, name, city, address, schoolType); SchoolAutismLevel + AutismLevel (levelName); each pin is placed from the school address | — |
| School Location | School (name, city, address, schoolType); SchoolAutismLevel + AutismLevel (levelName); the pin is placed from the school address | — (Get Directions / Open in Maps open the phone's maps app) |

* User.childLevelId (Int, FK to AutismLevel, nullable) must be added to the schema so School Review can show "Parent of a Level X child".
