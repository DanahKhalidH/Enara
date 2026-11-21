package com.example.enarah.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.data.SampleData
import com.example.enarah.data.School
import com.example.enarah.navigation.ScreenRoutes
import com.example.enarah.ui.components.*
import com.example.enarah.ui.theme.*

@Composable
fun HomeScreen(
    userName: String,
    schools: List<School>,
    onNavigate: (String) -> Unit,
    onEditProfile: () -> Unit = {},
    onQuickLevelSelect: (String) -> Unit,
    onOpenLevelsGuide: () -> Unit,
    onOpenSchoolDetails: (String) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    val favoriteCount = schools.count { it.isFavorite }
    val displayName = userName.ifBlank { "Parent" }.substringBefore(" ")

    Scaffold(
        containerColor = BackgroundLight,
        bottomBar = { EnarahBottomNavBar(currentRoute = ScreenRoutes.Home.route, onNavigate = onNavigate) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // الترويسة مع اللوقو المصغر وإمكانية تعديل الملف الشخصي عند الضغط
            item {
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEditProfile() },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Good morning • Tap to edit profile",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                        Text(
                            text = "Hello, $displayName",
                            style = MaterialTheme.typography.headlineLarge,
                            color = TextPrimary
                        )
                    }
                    EnarahMiniLogo(size = 46.dp)
                }
            }

            // شريط البحث السريع
            item {
                Surface(
                    onClick = { onNavigate(ScreenRoutes.SearchSchools.route) },
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceWhite,
                    border = BorderStroke(1.dp, BorderSubtle),
                    shadowElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.Search, contentDescription = null, tint = TealPrimary)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Search school by name...", color = TextSecondary, fontSize = 15.sp)
                    }
                }
            }

            // البحث السريع بالمستوى + زر Search Schools
            item {
                Text("Quick search by level", style = MaterialTheme.typography.titleMedium, color = TextPrimary)
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("Level 1", "Level 2", "Level 3").forEach { level ->
                        FilterChip(
                            selected = false,
                            onClick = { onQuickLevelSelect(level) },
                            label = { Text(level, fontWeight = FontWeight.Medium) },
                            shape = RoundedCornerShape(50),
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = SurfaceWhite,
                                labelColor = TextPrimary
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                EnarahPrimaryButton(
                    text = "Search Schools",
                    onClick = { onNavigate(ScreenRoutes.SearchSchools.route) }
                )
            }

            // بطاقتي Levels guide و Support needs
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickGuideCard(
                        title = "Levels guide",
                        subtitle = "Understand 1–3",
                        icon = Icons.Outlined.MenuBook,
                        onClick = onOpenLevelsGuide,
                        modifier = Modifier.weight(1f)
                    )
                    QuickGuideCard(
                        title = "Support needs",
                        subtitle = "Therapies & care",
                        icon = Icons.Outlined.SupportAgent,
                        onClick = onOpenLevelsGuide,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // الأعلى تقييماً
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Top rated schools", style = MaterialTheme.typography.titleLarge, color = TextPrimary)
                    TextButton(onClick = { onNavigate(ScreenRoutes.SearchResults.route) }) {
                        Text("See all", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            items(schools.take(2), key = { it.id }) { school ->
                SchoolCard(
                    school = school,
                    onClick = { onOpenSchoolDetails(school.id) },
                    onFavoriteClick = { onToggleFavorite(school.id) }
                )
            }

            // بطاقة المفضلة المختصرة
            item {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = TealLightTint,
                    border = BorderStroke(1.dp, TealPrimary.copy(alpha = 0.2f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigate(ScreenRoutes.Favorites.route) }
                ) {
                    Row(
                        modifier = Modifier.padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Favorites", fontWeight = FontWeight.Bold, color = TealPrimary, fontSize = 16.sp)
                            Text("$favoriteCount schools saved", fontSize = 13.sp, color = TextSecondary)
                        }
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(SurfaceWhite),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = null,
                                tint = TealPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun QuickGuideCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        color = SurfaceWhite,
        border = BorderStroke(1.dp, BorderSubtle),
        shadowElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(TealLightTint),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = TealPrimary, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextPrimary)
            Text(subtitle, fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    EnarahTheme {
        HomeScreen(
            userName = "Danah Khalid",
            schools = SampleData.schools,
            onNavigate = {},
            onEditProfile = {},
            onQuickLevelSelect = {},
            onOpenLevelsGuide = {},
            onOpenSchoolDetails = {},
            onToggleFavorite = {}
        )
    }
}