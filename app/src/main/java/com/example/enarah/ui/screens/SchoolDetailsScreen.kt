package com.example.enarah.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.data.SampleData
import com.example.enarah.data.School
import com.example.enarah.ui.components.EnarahMiniLogo
import com.example.enarah.ui.components.LevelBadge
import com.example.enarah.ui.theme.*

@Composable
fun SchoolDetailsScreen(
    school: School,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit,
    onViewOnMap: () -> Unit,
    onViewAllServices: () -> Unit,
    onOpenReviews: () -> Unit,
    onContactSchool: () -> Unit
) {
    Scaffold(
        containerColor = BackgroundLight,
        bottomBar = {
            Surface(color = SurfaceWhite, shadowElevation = 10.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onOpenReviews,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.5.dp, TealPrimary)
                    ) {
                        Text("Reviews", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                    }
                    Button(
                        onClick = onContactSchool,
                        modifier = Modifier
                            .weight(1.3f)
                            .height(50.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
                    ) {
                        Text("Contact school", fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // الترويسة المتدرجة مع الدوائر الزخرفية
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Brush.linearGradient(listOf(TealPrimary, Color(0xFF1A7A76))))
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = Color.White.copy(alpha = 0.08f),
                            radius = size.width * 0.34f,
                            center = Offset(size.width * 0.88f, size.height * 0.25f)
                        )
                        drawCircle(
                            color = Color.White.copy(alpha = 0.07f),
                            radius = size.width * 0.26f,
                            center = Offset(size.width * 0.12f, size.height * 0.88f)
                        )
                    }

                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }

                    IconButton(
                        onClick = onToggleFavorite,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopEnd)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape)
                    ) {
                        Icon(
                            imageVector = if (school.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (school.isFavorite) FavoriteRed else Color.White
                        )
                    }

                    Icon(
                        imageVector = Icons.Outlined.School,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(58.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            // محتوى تفاصيل المدرسة مع اللوقو المصغر
            item {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(school.name, style = MaterialTheme.typography.headlineLarge, color = TextPrimary)
                            Text(school.tagline, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        EnarahMiniLogo(size = 46.dp)
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = SurfaceWhite,
                        border = BorderStroke(1.dp, BorderSubtle),
                        shadowElevation = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DetailStat(school.rating, "Rating")
                            VerticalDivider(modifier = Modifier.height(32.dp), color = BorderSubtle)
                            DetailStat("${school.reviewCount}", "Reviews")
                            VerticalDivider(modifier = Modifier.height(32.dp), color = BorderSubtle)
                            DetailStat(school.levels.joinToString("-") { it.removePrefix("Level ") }, "Levels")
                        }
                    }

                    Spacer(modifier = Modifier.height(22.dp))
                    Text("Accepted autism levels", style = MaterialTheme.typography.titleMedium)
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        school.levels.forEach { LevelBadge(it, isSelected = true) }
                    }

                    Spacer(modifier = Modifier.height(22.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Location", style = MaterialTheme.typography.titleMedium)
                            Text(school.city, color = TextSecondary, fontSize = 14.sp)
                        }
                        TextButton(onClick = onViewOnMap) {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = null,
                                tint = TealPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("View on map", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Services", style = MaterialTheme.typography.titleMedium)
                        TextButton(onClick = onViewAllServices) {
                            Text("View all", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(school.services) { service ->
                            AssistChip(
                                onClick = onViewAllServices,
                                label = { Text(service.removeSuffix(" Therapy")) },
                                shape = RoundedCornerShape(50)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, fontSize = 19.sp, color = TealPrimary)
        Text(label, fontSize = 12.sp, color = TextSecondary)
    }
}

@Preview(showBackground = true)
@Composable
private fun SchoolDetailsScreenPreview() {
    EnarahTheme {
        SchoolDetailsScreen(SampleData.schools.first(), {}, {}, {}, {}, {}, {})
    }
}