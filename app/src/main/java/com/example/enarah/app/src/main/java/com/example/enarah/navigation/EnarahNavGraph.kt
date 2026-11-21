package com.example.enarah.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.enarah.data.SampleData
import com.example.enarah.ui.screens.*

@Composable
fun EnarahNavGraph(navController: NavHostController) {
    var userName by remember { mutableStateOf("") }
    var userCity by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var filterCity by remember { mutableStateOf("Dammam") }
    var filterLevel by remember { mutableStateOf("Level 2") }
    var filterType by remember { mutableStateOf("Any") }
    var schoolsState by remember { mutableStateOf(SampleData.schools) }

    val toggleFavorite: (String) -> Unit = { schoolId ->
        schoolsState = schoolsState.map {
            if (it.id == schoolId) it.copy(isFavorite = !it.isFavorite) else it
        }
    }

    val safeNavigateBack: () -> Unit = {
        val popped = navController.popBackStack()
        if (!popped) {
            navController.navigate(ScreenRoutes.Home.route) {
                launchSingleTop = true
            }
        }
    }

    val navigateToTab: (String) -> Unit = { route ->
        navController.navigate(route) {
            popUpTo(ScreenRoutes.Home.route) {
                saveState = false
            }
            launchSingleTop = true
            restoreState = false
        }
    }

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Welcome.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(260)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(260)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(260)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(260)
            )
        }
    ) {
        composable(ScreenRoutes.Welcome.route) {
            WelcomeScreen(
                onGetStarted = { navController.navigate(ScreenRoutes.ProfileSetup.route) }
            )
        }

        composable(ScreenRoutes.ProfileSetup.route) {
            ProfileSetupScreen(
                initialName = userName,
                initialCity = userCity,
                onBack = safeNavigateBack,
                onSaveProfile = { name, city ->
                    userName = name
                    userCity = city
                    if (city.isNotBlank()) filterCity = city
                    navController.navigate(ScreenRoutes.Home.route) {
                        popUpTo(ScreenRoutes.Welcome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(ScreenRoutes.Home.route) {
            HomeScreen(
                userName = userName,
                schools = schoolsState,
                onNavigate = navigateToTab,
                onEditProfile = { navController.navigate(ScreenRoutes.ProfileSetup.route) },
                onQuickLevelSelect = { level ->
                    filterLevel = level
                    navController.navigate(ScreenRoutes.SearchResults.route)
                },
                onOpenLevelsGuide = { navController.navigate(ScreenRoutes.AutismLevelGuide.route) },
                onOpenSchoolDetails = { id ->
                    navController.navigate(ScreenRoutes.SchoolDetails.createRoute(id))
                },
                onToggleFavorite = toggleFavorite
            )
        }

        composable(ScreenRoutes.SearchSchools.route) {
            SearchSchoolsScreen(
                query = searchQuery,
                selectedCity = filterCity,
                selectedLevel = filterLevel,
                selectedType = filterType,
                onQueryChange = { searchQuery = it },
                onClearCity = { filterCity = "Any" },
                onClearLevel = { filterLevel = "Any" },
                onClearType = { filterType = "Any" },
                onBack = safeNavigateBack,
                onEditFilters = { navController.navigate(ScreenRoutes.Filters.route) },
                onSearchSubmit = { navController.navigate(ScreenRoutes.SearchResults.route) },
                onNavigate = navigateToTab
            )
        }

        composable(ScreenRoutes.Filters.route) {
            FiltersScreen(
                initialCity = filterCity,
                initialLevel = filterLevel,
                initialType = filterType,
                onBack = safeNavigateBack,
                onApplyFilters = { city, level, type ->
                    filterCity = city
                    filterLevel = level
                    filterType = type
                    safeNavigateBack()
                }
            )
        }

        composable(ScreenRoutes.SearchResults.route) {
            SearchResultsScreen(
                schools = schoolsState,
                query = searchQuery,
                selectedCity = filterCity,
                selectedLevel = filterLevel,
                selectedType = filterType,
                onClearCity = { filterCity = "Any" },
                onClearLevel = { filterLevel = "Any" },
                onClearType = { filterType = "Any" },
                onBack = safeNavigateBack,
                onModifyFilters = { navController.navigate(ScreenRoutes.Filters.route) },
                onClearFilters = {
                    searchQuery = ""
                    filterCity = "Any"
                    filterLevel = "Any"
                    filterType = "Any"
                },
                onOpenSchoolDetails = { id ->
                    navController.navigate(ScreenRoutes.SchoolDetails.createRoute(id))
                },
                onToggleFavorite = toggleFavorite,
                onNavigate = navigateToTab
            )
        }

        composable(
            route = ScreenRoutes.SchoolDetails.route,
            arguments = listOf(navArgument("schoolId") { type = NavType.StringType })
        ) { backStackEntry ->
            val schoolId = backStackEntry.arguments?.getString("schoolId") ?: "al_noor"
            val school = schoolsState.find { it.id == schoolId } ?: SampleData.getSchoolById(schoolId)
            SchoolDetailsScreen(
                school = school,
                onBack = safeNavigateBack,
                onToggleFavorite = { toggleFavorite(school.id) },
                onViewOnMap = {
                    navController.navigate(ScreenRoutes.SchoolLocation.createRoute(school.id))
                },
                onViewAllServices = {
                    navController.navigate(ScreenRoutes.ServicesSpecialists.createRoute(school.id))
                },
                onOpenReviews = {
                    navController.navigate(ScreenRoutes.Reviews.createRoute(school.id))
                },
                onContactSchool = {
                    navController.navigate(ScreenRoutes.ContactSchool.createRoute(school.id))
                }
            )
        }

        composable(
            route = ScreenRoutes.Reviews.route,
            arguments = listOf(navArgument("schoolId") { type = NavType.StringType })
        ) { backStackEntry ->
            val schoolId = backStackEntry.arguments?.getString("schoolId") ?: "al_noor"
            val school = SampleData.getSchoolById(schoolId)
            ReviewsScreen(
                school = school,
                onBack = safeNavigateBack,
                onNavigate = navigateToTab
            )
        }

        composable(
            route = ScreenRoutes.ServicesSpecialists.route,
            arguments = listOf(navArgument("schoolId") { type = NavType.StringType })
        ) { backStackEntry ->
            val schoolId = backStackEntry.arguments?.getString("schoolId") ?: "al_noor"
            val school = SampleData.getSchoolById(schoolId)
            ServicesSpecialistsScreen(
                school = school,
                onBack = safeNavigateBack,
                onNavigate = navigateToTab
            )
        }

        composable(
            route = ScreenRoutes.ContactSchool.route,
            arguments = listOf(navArgument("schoolId") { type = NavType.StringType })
        ) { backStackEntry ->
            val schoolId = backStackEntry.arguments?.getString("schoolId") ?: "al_noor"
            val school = SampleData.getSchoolById(schoolId)
            ContactSchoolScreen(
                school = school,
                onBack = safeNavigateBack,
                onNavigate = navigateToTab
            )
        }
        composable(
            route = ScreenRoutes.SchoolLocation.route,
            arguments = listOf(navArgument("schoolId") { type = NavType.StringType })
        ) { backStackEntry ->
            val schoolId = backStackEntry.arguments?.getString("schoolId") ?: "al_noor"
            val school = SampleData.getSchoolById(schoolId)
            SchoolLocationScreen(
                school = school,
                onBack = safeNavigateBack,
                onNavigate = navigateToTab
            )
        }

        composable(ScreenRoutes.Favorites.route) {
            FavoritesScreen(
                favoriteSchools = schoolsState.filter { it.isFavorite },
                onRemoveFavorite = toggleFavorite,
                onOpenSchoolDetails = { id ->
                    navController.navigate(ScreenRoutes.SchoolDetails.createRoute(id))
                },
                onExploreSchools = { navigateToTab(ScreenRoutes.SearchSchools.route) },
                onNavigate = navigateToTab
            )
        }

        composable(ScreenRoutes.AutismLevelGuide.route) {
            AutismLevelGuideScreen(
                onBack = safeNavigateBack,
                onFindSchoolsForLevel = { level ->
                    filterLevel = level
                    navController.navigate(ScreenRoutes.SearchResults.route)
                },
                onNavigate = navigateToTab
            )
        }

        composable(
            route = ScreenRoutes.Map.route,
            arguments = listOf(
                navArgument("schoolId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val initialSchoolId = backStackEntry.arguments?.getString("schoolId") ?: ""
            MapScreen(
                schools = schoolsState,
                initialSchoolId = initialSchoolId,
                onOpenSchoolDetails = { id ->
                    navController.navigate(ScreenRoutes.SchoolDetails.createRoute(id))
                },
                onNavigate = navigateToTab
            )
        }
    }
}