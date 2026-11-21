package com.example.enarah.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.data.SampleData
import com.example.enarah.data.School
import com.example.enarah.ui.components.*
import com.example.enarah.ui.theme.*

@Composable
fun SearchResultsScreen(
    schools: List<School>,
    query: String,
    selectedCity: String,
    selectedLevel: String,
    selectedType: String,
    onClearCity: () -> Unit,
    onClearLevel: () -> Unit,
    onClearType: () -> Unit,
    onBack: () -> Unit,
    onModifyFilters: () -> Unit,
    onClearFilters: () -> Unit,
    onOpenSchoolDetails: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onNavigate: (String) -> Unit
) {
    val filtered = schools.filter { school ->
        val matchesName = query.isBlank() || school.name.contains(query, ignoreCase = true)
        val matchesCity = selectedCity == "Any" || school.city.equals(selectedCity, ignoreCase = true)
        val matchesLevel = selectedLevel == "Any" || school.levels.contains(selectedLevel)
        val matchesType = selectedType == "Any" || school.type.equals(selectedType, ignoreCase = true)
        matchesName && matchesCity && matchesLevel && matchesType
    }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "Search results", onBack = onBack) },
        bottomBar = { EnarahBottomNavBar(currentRoute = "", onNavigate = onNavigate) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                if (selectedLevel != "Any") RemovableFilterChip(selectedLevel, onClearLevel)
                if (selectedCity != "Any") RemovableFilterChip(selectedCity, onClearCity)
                if (selectedType != "Any") RemovableFilterChip(selectedType, onClearType)
            }

            Spacer(modifier = Modifier.height(14.dp))

            if (filtered.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.SearchOff,
                        contentDescription = null,
                        tint = TealPrimary,
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("No schools found", style = MaterialTheme.typography.headlineMedium, color = TextPrimary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "We couldn't find a school that matches these filters.\nTry another level or school type.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    EnarahPrimaryButton(text = "Modify Filters", onClick = onModifyFilters)
                    TextButton(onClick = onClearFilters) {
                        Text("Clear Filters", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${filtered.size} schools found", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    TextButton(onClick = onModifyFilters) {
                        Text("Modify", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                    }
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(filtered, key = { it.id }) { school ->
                        SchoolCard(
                            school = school,
                            onClick = { onOpenSchoolDetails(school.id) },
                            onFavoriteClick = { onToggleFavorite(school.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RemovableFilterChip(text: String, onRemove: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(50),
        color = TealPrimary,
        modifier = Modifier.clickable { onRemove() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(6.dp))
            Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.White, modifier = Modifier.size(14.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchResultsScreenPreview() {
    EnarahTheme {
        SearchResultsScreen(SampleData.schools, "", "Dammam", "Level 2", "Any", {}, {}, {}, {}, {}, {}, {}, {}, {})
    }
}