package com.example.enarah.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.data.SampleData
import com.example.enarah.data.School
import com.example.enarah.navigation.ScreenRoutes
import com.example.enarah.ui.components.EnarahBottomNavBar
import com.example.enarah.ui.components.EnarahTopBar
import com.example.enarah.ui.theme.*

@Composable
fun ServicesSpecialistsScreen(
    school: School,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "Services & Specialists", onBack = onBack) },
        bottomBar = { EnarahBottomNavBar(currentRoute = ScreenRoutes.Home.route, onNavigate = onNavigate) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = SurfaceWhite,
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("Available services", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    school.services.forEach { service ->
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = TealPrimary)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(service, fontSize = 15.sp, color = TextPrimary)
                        }
                    }
                }
            }

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = SurfaceWhite,
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("Specialists", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    school.specialists.forEach { specialist ->
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Outlined.Person, contentDescription = null, tint = TealPrimary)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(specialist, fontSize = 15.sp, color = TextPrimary)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ServicesSpecialistsScreenPreview() {
    EnarahTheme { ServicesSpecialistsScreen(SampleData.schools.first(), {}, {}) }
}