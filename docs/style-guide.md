# Enara — Style Guide

> CSC 402 · Milestone 2 · Group 1 · File: `docs/style-guide.md`
> Matches the Figma prototype (15 screens). Material 3, light theme, RTL-ready.

## 1. App recap

**Problem.** Parents of children with autism spectrum disorder (ASD) in the Eastern Province struggle to find a school that accepts their child's autism level (Level 1, 2 or 3). Today they must call schools one by one, with no centralized or up-to-date source, and they have no easy way to read other parents' experiences. Enara gathers this information in one app so parents can find, compare and contact suitable schools quickly.

**Target users.** Primary: parents/guardians of children with ASD. Secondary: schools that want visibility to families who fit their accepted level.

| Feature | Priority | Description |
|---|---|---|
| Search & filter schools | Must | Search by name; filter by city, autism level, school type |
| School details | Must | Photo, accepted levels, location, services, rating, "Last updated" |
| Direct contact | Must | Call / WhatsApp / email in one tap |
| Autism level guide | Must | Plain-language Level 1–3 explanation, opens pre-filtered results |
| Map & directions | Should | Map view, hand-off to Google Maps |
| Favorites | Should | Heart toggle, Favorites tab |
| Parent reviews | Should | Read and write reviews with star rating |
| Services & specialists | Should | Therapies and specialists per school |
| Profile & preferred city | Could | Name, email, preferred city |
| School data admin view | Could | Planned later; no screen in the prototype |

## 2. Colors

| Role | Name | Hex | Used for |
|---|---|---|---|
| Primary | Deep Teal | `#0B6B78` | Primary buttons, selected chips, level badges, active nav icon, Welcome background, outlined-button text |
| Accent | Warm Amber | `#F2A93B` | Welcome sun logo, rating stars |
| Accent tint | Amber Tint | `#FCEBCB` | Favorites card icon background |
| Secondary tint | Teal Tint | `#D9EEF1` | Autism Levels Guide icon background, selected-chip fill |
| Filter panel | Filter Gray-Blue | `#B9CAD1` | Filters screen panel |
| Unselected option | Mist Teal | `#80B5BB` | Unselected filter buttons |
| Background | Cloud | `#F3F6F8` | Screen background |
| Surface | White | `#FFFFFF` | Cards, text fields |
| Text | Ink | `#1F2933` | Primary text |
| Secondary text | Slate | `#5B6B76` | Hints, captions |

**Level badges.** Level 1, 2 and 3 share one color (Deep Teal) and always show the text "Level 1/2/3". No red/green alarm colors, so no level looks better or worse than another.

**Contrast (WCAG).**

| Pair | Ratio |
|---|---|
| Ink on Background | 13.6:1 |
| White on Deep Teal | 6.2:1 |
| Deep Teal on Background | 5.7:1 |
| Slate on Background | 5.1:1 |
| Ink on Warm Amber | 7.4:1 |

## 3. Typography

Noto Sans / Noto Sans Arabic (system fallback). Line height 1.4.

| Style | Size | Weight | Used for |
|---|---|---|---|
| Title | 22sp | Bold | Screen titles, school name on details |
| Heading | 18sp | SemiBold | Section headings, card titles |
| Body | 16sp | Regular | Body text (minimum size) |
| Caption | 12sp | Regular | Chip labels, helper text |

## 4. Spacing and shape

- 8dp grid; 16dp screen margins; single-column layout
- Cards: 12dp radius
- Chips and level badges: fully rounded
- Buttons: 8–12dp radius (filled = primary action, outlined = secondary)
- Touch targets: at least 48 × 48dp

## 5. Material 3 components

| Component | Where used |
|---|---|
| Bottom navigation (Home, Search, Favorites, Maps) | Top-level screens |
| Top app bar, back arrow, centered title | Drill-down screens |
| Search field | Home, Search Schools |
| Dropdown | City (Filters, Profile) |
| Filter chips | Filters, selected-filter row |
| Level badge | School cards, details, favorites |
| School card with favorite heart | Search Results, Favorites |
| Primary / outlined buttons | Search, Apply Filters, Clear All, Contact, Reviews |
| Contact button row | Contact School |
| Star rating | School Review, School Details |
| Checkbox list | Services & Specialists |
| Map view with address card | Maps, School Location |
| Empty-state message | Search Result (no school found) |

## 6. Screens (15)

Welcome · My Profile · Home · Search Schools · Filters · Search Results · School Details · School Review · Services & Specialists · Maps · Contact School · Favorites · Autism Level Guide · Search Result (empty) · School Location
