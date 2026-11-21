package com.example.enarah.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.data.ParentReview
import com.example.enarah.data.SampleData
import com.example.enarah.data.School
import com.example.enarah.ui.components.*
import com.example.enarah.ui.theme.*

@Composable
fun ReviewsScreen(
    school: School,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    var reviews by remember { mutableStateOf(SampleData.reviews) }
    var showAddDialog by remember { mutableStateOf(false) }
    var newComment by remember { mutableStateOf("") }
    var selectedStars by remember { mutableIntStateOf(5) }
    var selectedChildLevel by remember { mutableStateOf("Level 1") }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "Reviews", onBack = onBack) },
        bottomBar = { EnarahBottomNavBar(currentRoute = "", onNavigate = onNavigate) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = SurfaceWhite,
                    border = BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("${school.name} Inclusive School", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Based on ${reviews.size + 10} parent reviews", fontSize = 13.sp, color = TextSecondary)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Star, contentDescription = null, tint = AmberAccent)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(school.rating, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = TealPrimary)
                        }
                    }
                }
            }

            items(reviews, key = { it.id }) { review ->
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = SurfaceWhite,
                    border = BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        LevelBadge(review.childLevelLabel)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(review.comment, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(review.authorName, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TealPrimary)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(6.dp))
                EnarahPrimaryButton(text = "Write a Review", onClick = { showAddDialog = true })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Write a Review") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        (1..5).forEach { star ->
                            IconButton(onClick = { selectedStars = star }, modifier = Modifier.size(36.dp)) {
                                Icon(
                                    imageVector = if (star <= selectedStars) Icons.Filled.Star else Icons.Outlined.StarOutline,
                                    contentDescription = "$star stars",
                                    tint = AmberAccent
                                )
                            }
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("Level 1", "Level 2", "Level 3").forEach { lvl ->
                            FilterChip(
                                selected = selectedChildLevel == lvl,
                                onClick = { selectedChildLevel = lvl },
                                label = { Text(lvl, fontSize = 12.sp) }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = newComment,
                        onValueChange = { newComment = it },
                        placeholder = { Text("Share your experience with ${school.name}...") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    if (newComment.isNotBlank()) {
                        reviews = reviews + ParentReview(
                            id = "r_${reviews.size + 1}",
                            schoolId = school.id,
                            authorName = "Verified Parent • ${"★".repeat(selectedStars)}",
                            childLevelLabel = "Parent of a ${selectedChildLevel.lowercase()} child",
                            comment = newComment.trim()
                        )
                        newComment = ""
                    }
                    showAddDialog = false
                }) {
                    Text("Submit", color = TealPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) { Text("Cancel") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewsScreenPreview() {
    EnarahTheme { ReviewsScreen(SampleData.schools.first(), {}, {}) }
}