package com.example.enarah.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.data.SampleData
import com.example.enarah.navigation.ScreenRoutes
import com.example.enarah.ui.components.EnarahBottomNavBar
import com.example.enarah.ui.components.EnarahTopBar
import com.example.enarah.ui.components.LevelBadge
import com.example.enarah.ui.theme.*

@Composable
fun AutismLevelGuideScreen(
    onBack: () -> Unit,
    onFindSchoolsForLevel: (String) -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "Autism Level Guide", onBack = onBack) },
        bottomBar = { EnarahBottomNavBar(currentRoute = ScreenRoutes.Home.route, onNavigate = onNavigate) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(SampleData.autismLevels) { item ->
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = SurfaceWhite,
                    border = BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            LevelBadge(item.level, isSelected = true)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(item.subtitle, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextPrimary)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(item.description, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                        Spacer(modifier = Modifier.height(14.dp))
                        OutlinedButton(
                            onClick = { onFindSchoolsForLevel(item.level) },
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(1.2.dp, TealPrimary)
                        ) {
                            Text("Find schools for this level", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AutismLevelGuideScreenPreview() {
    EnarahTheme { AutismLevelGuideScreen({}, {}, {}) }
}