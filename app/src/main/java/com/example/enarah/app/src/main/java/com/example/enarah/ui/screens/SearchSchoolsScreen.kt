package com.example.enarah.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.navigation.ScreenRoutes
import com.example.enarah.ui.components.EnarahBottomNavBar
import com.example.enarah.ui.components.EnarahPrimaryButton
import com.example.enarah.ui.components.EnarahTopBar
import com.example.enarah.ui.theme.*

@Composable
fun SearchSchoolsScreen(
    query: String,
    selectedCity: String,
    selectedLevel: String,
    selectedType: String,
    onQueryChange: (String) -> Unit,
    onClearCity: () -> Unit,
    onClearLevel: () -> Unit,
    onClearType: () -> Unit,
    onBack: () -> Unit,
    onEditFilters: () -> Unit,
    onSearchSubmit: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "Search schools", onBack = onBack) },
        bottomBar = { EnarahBottomNavBar(currentRoute = ScreenRoutes.SearchSchools.route, onNavigate = onNavigate) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("School name", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                OutlinedTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    placeholder = { Text("Search by school name", color = TextSecondary) },
                    leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null, tint = TealPrimary) },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { onQueryChange("") }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear", tint = TextSecondary)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = SurfaceWhite,
                        focusedContainerColor = SurfaceWhite,
                        unfocusedBorderColor = BorderSubtle
                    )
                )

                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = SurfaceWhite,
                    border = BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Selected filters", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            TextButton(onClick = onEditFilters) {
                                Text("Edit filters", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                            }
                        }
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            val chips = listOf(
                                Triple("City: $selectedCity", selectedCity != "Any", onClearCity),
                                Triple(if (selectedLevel == "Any") "Level: Any" else selectedLevel, selectedLevel != "Any", onClearLevel),
                                Triple("Type: $selectedType", selectedType != "Any", onClearType)
                            )
                            items(chips) { (label, canRemove, onRemove) ->
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = if (canRemove) TealPrimary else TealLightTint,
                                    modifier = Modifier.clickable(enabled = canRemove) { onRemove() }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = label,
                                            color = if (canRemove) Color.White else TealPrimary,
                                            fontSize = 12.sp
                                        )
                                        if (canRemove) {
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.White, modifier = Modifier.size(14.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Box(modifier = Modifier.padding(bottom = 20.dp)) {
                EnarahPrimaryButton(text = "Search Schools", onClick = onSearchSubmit)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchSchoolsScreenPreview() {
    EnarahTheme {
        SearchSchoolsScreen("", "Dammam", "Level 2", "Any", {}, {}, {}, {}, {}, {}, {}, {})
    }
}