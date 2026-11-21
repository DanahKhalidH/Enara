package com.example.enarah.navigation

sealed class ScreenRoutes(val route: String) {
    data object Welcome : ScreenRoutes("welcome")
    data object ProfileSetup : ScreenRoutes("profile_setup")
    data object Home : ScreenRoutes("home")
    data object SearchSchools : ScreenRoutes("search_schools")
    data object Filters : ScreenRoutes("filters")
    data object SearchResults : ScreenRoutes("search_results")
    data object Favorites : ScreenRoutes("favorites")
    data object AutismLevelGuide : ScreenRoutes("autism_level_guide")

    data object Map : ScreenRoutes("map?schoolId={schoolId}") {
        const val baseRoute = "map"
        fun createRoute(schoolId: String = "") =
            if (schoolId.isBlank()) "map" else "map?schoolId=$schoolId"
    }

    data object SchoolDetails : ScreenRoutes("school_details/{schoolId}") {
        fun createRoute(schoolId: String) = "school_details/$schoolId"
    }

    data object Reviews : ScreenRoutes("reviews/{schoolId}") {
        fun createRoute(schoolId: String) = "reviews/$schoolId"
    }

    data object ServicesSpecialists : ScreenRoutes("services_specialists/{schoolId}") {
        fun createRoute(schoolId: String) = "services_specialists/$schoolId"
    }

    data object ContactSchool : ScreenRoutes("contact_school/{schoolId}") {
        fun createRoute(schoolId: String) = "contact_school/$schoolId"
    }
    data object SchoolLocation : ScreenRoutes("school_location/{schoolId}") {
        fun createRoute(schoolId: String) = "school_location/$schoolId"
    }
}