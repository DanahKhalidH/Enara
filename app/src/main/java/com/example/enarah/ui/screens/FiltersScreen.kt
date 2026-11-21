package com.example.enarah.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.ui.components.EnarahTopBar
import com.example.enarah.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FiltersScreen(
    initialCity: String,
    initialLevel: String,
    initialType: String,
    onBack: () -> Unit,
    onApplyFilters: (String, String, String) -> Unit
) {
    var city by remember { mutableStateOf(initialCity) }
    var level by remember { mutableStateOf(initialLevel) }
    var type by remember { mutableStateOf(initialType) }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "Filters", onBack = onBack) },
        bottomBar = {
            Surface(color = SurfaceWhite, shadowElevation = 8.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            city = "Any"
                            level = "Any"
                            type = "Any"
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.5.dp, TealPrimary)
                    ) {
                        Text("Clear All", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                    }
                    Button(
                        onClick = { onApplyFilters(city, level, type) },
                        modifier = Modifier
                            .weight(1.4f)
                            .height(52.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
                    ) {
                        Text("Apply Filters", fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            FilterChipGroup("City", listOf("Any", "Dammam", "Khobar"), city) { city = it }
            FilterChipGroup("Autism level", listOf("Any", "Level 1", "Level 2", "Level 3"), level) { level = it }
            FilterChipGroup("School type", listOf("Any", "Public", "Private"), type) { type = it }

            Text(
                text = "Tap a chip to select it. \"Any\" includes every option.",
                fontSize = 13.sp,
                color = TextSecondary
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterChipGroup(
    title: String,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    Column {
        Text(title, style = MaterialTheme.typography.titleMedium, color = TextPrimary)
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            options.forEach { option ->
                val isSelected = option == selected
                FilterChip(
                    selected = isSelected,
                    onClick = { onSelect(option) },
                    label = { Text(if (option == "Any") "Any $title" else option) },
                    shape = RoundedCornerShape(50),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TealPrimary,
                        selectedLabelColor = Color.White,
                        containerColor = SurfaceWhite,
                        labelColor = TextPrimary
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FiltersScreenPreview() {
    EnarahTheme {
        FiltersScreen("Dammam", "Level 2", "Any", onBack = {}, onApplyFilters = { _, _, _ -> })
    }
}