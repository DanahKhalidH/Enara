package com.example.enarah.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.R
import com.example.enarah.data.School
import com.example.enarah.navigation.ScreenRoutes
import com.example.enarah.ui.theme.*

// 1. اللوقو الكبير لشاشة البداية (WelcomeScreen)
@Composable
fun EnarahConcentricLogo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.enarah_logo),
        contentDescription = "Enarah Logo",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(210.dp)
            .clip(CircleShape)
    )
}

// 2. اللوقو المصغر لبقية الشاشات
@Composable
fun EnarahMiniLogo(
    modifier: Modifier = Modifier,
    size: Dp = 38.dp
) {
    Surface(
        modifier = modifier.size(size),
        shape = CircleShape,
        border = BorderStroke(1.5.dp, BorderSubtle),
        shadowElevation = 2.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.enarah_logo),
            contentDescription = "Enarah Mini Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

// 3. الشريط العلوي الموحد مع حماية من شريط الحالة العلوي وسهولة الضغط على زر الرجوع
@Composable
fun EnarahTopBar(
    title: String,
    onBack: (() -> Unit)? = null,
    showLogo: Boolean = true,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            if (onBack != null) {
                Surface(
                    onClick = onBack,
                    modifier = Modifier.size(44.dp),
                    shape = CircleShape,
                    color = SurfaceWhite,
                    border = BorderStroke(1.dp, BorderSubtle),
                    shadowElevation = 2.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary
            )
        }

        if (trailingContent != null) {
            trailingContent()
        } else if (showLogo) {
            EnarahMiniLogo(size = 38.dp)
        }
    }
}

@Composable
fun EnarahPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = TealPrimary,
            contentColor = Color.White
        )
    ) {
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun LevelBadge(
    text: String,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = if (isSelected) TealPrimary else TealLightTint,
        modifier = modifier.padding(end = 6.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else TealPrimary,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun SchoolCard(
    school: School,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = BorderStroke(1.dp, BorderSubtle),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(TealLightTint),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = school.initials,
                    color = TealPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = school.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )
                Text(
                    text = "${school.city} • ${school.type} school",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    school.levels.forEach { level ->
                        LevelBadge(text = level)
                    }
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (school.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (school.isFavorite) FavoriteRed else TextSecondary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = AmberAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = school.rating,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun EnarahBottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = SurfaceWhite,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            Triple(ScreenRoutes.Home.route, "Home", Pair(Icons.Filled.Home, Icons.Outlined.Home)),
            Triple(ScreenRoutes.SearchSchools.route, "Search", Pair(Icons.Filled.Search, Icons.Outlined.Search)),
            Triple(ScreenRoutes.Favorites.route, "Saved", Pair(Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder)),
            Triple(ScreenRoutes.Map.baseRoute, "Map", Pair(Icons.Filled.LocationOn, Icons.Outlined.LocationOn))
        )
        items.forEach { (route, label, icons) ->
            val selected = currentRoute == route ||
                    (route == ScreenRoutes.Map.baseRoute && currentRoute.startsWith(ScreenRoutes.Map.baseRoute))
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) onNavigate(route)
                },
                icon = {
                    Icon(
                        imageVector = if (selected) icons.first else icons.second,
                        contentDescription = label
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TealPrimary,
                    selectedTextColor = TealPrimary,
                    indicatorColor = TealLightTint,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary
                )
            )
        }
    }
}