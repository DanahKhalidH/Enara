package com.example.enarah.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.data.SampleData
import com.example.enarah.data.School
import com.example.enarah.navigation.ScreenRoutes
import com.example.enarah.ui.components.*
import com.example.enarah.ui.theme.*

@Composable
fun FavoritesScreen(
    favoriteSchools: List<School>,
    onRemoveFavorite: (String) -> Unit,
    onOpenSchoolDetails: (String) -> Unit,
    onExploreSchools: () -> Unit = {},
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = BackgroundLight,
        bottomBar = { EnarahBottomNavBar(currentRoute = ScreenRoutes.Favorites.route, onNavigate = onNavigate) }
    ) { padding ->
        if (favoriteSchools.isEmpty()) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(32.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EnarahMiniLogo(size = 64.dp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("No saved schools yet", style = MaterialTheme.typography.headlineMedium, color = TextPrimary)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tap the heart icon on any school card to save it here for quick comparison.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                EnarahPrimaryButton(text = "Explore Schools", onClick = onExploreSchools)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Favorites", style = MaterialTheme.typography.headlineLarge, color = TextPrimary)
                            Text("${favoriteSchools.size} schools saved", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                        }
                        EnarahMiniLogo(size = 44.dp)
                    }
                }

                items(favoriteSchools, key = { it.id }) { school ->
                    SchoolCard(
                        school = school,
                        onClick = { onOpenSchoolDetails(school.id) },
                        onFavoriteClick = { onRemoveFavorite(school.id) }
                    )
                }

                item {
                    Text(
                        text = "Tap the heart to remove a school",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesScreenPreview() {
    EnarahTheme { FavoritesScreen(SampleData.schools.filter { it.isFavorite }, {}, {}, {}, {}) }
}