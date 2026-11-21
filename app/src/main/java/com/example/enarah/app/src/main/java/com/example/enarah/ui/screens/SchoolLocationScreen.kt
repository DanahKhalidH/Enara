package com.example.enarah.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.data.SampleData
import com.example.enarah.data.School
import com.example.enarah.navigation.ScreenRoutes
import com.example.enarah.ui.components.EnarahBottomNavBar
import com.example.enarah.ui.components.EnarahTopBar
import com.example.enarah.ui.theme.*

// School Location: shows the location of one school, opened from School Details ("View on map")
@Composable
fun SchoolLocationScreen(
    school: School,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    val query = Uri.encode("${school.name} School ${school.city}")

    fun openUrl(url: String) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (_: Exception) {
            Toast.makeText(context, "Maps app not found", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "School Location", onBack = onBack) },
        bottomBar = { EnarahBottomNavBar(currentRoute = ScreenRoutes.Home.route, onNavigate = onNavigate) }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFE6EFEF))
        ) {
            // Simple map background (streets, park and water), same style as the Maps screen
            Canvas(modifier = Modifier.fillMaxSize()) {
                val roadColor = Color.White.copy(alpha = 0.85f)
                val roadWidth = 14.dp.toPx()
                drawRect(Color(0xFFD9E8DF), Offset(0f, 0f), Size(size.width * 0.30f, size.height * 0.28f))
                drawRect(Color(0xFFCDE2EB), Offset(size.width * 0.70f, size.height * 0.55f), Size(size.width * 0.30f, size.height * 0.45f))
                drawRect(roadColor, Offset(size.width * 0.30f, 0f), Size(roadWidth, size.height))
                drawRect(roadColor, Offset(size.width * 0.68f, 0f), Size(roadWidth, size.height))
                drawRect(roadColor, Offset(0f, size.height * 0.28f), Size(size.width, roadWidth))
                drawRect(roadColor, Offset(0f, size.height * 0.60f), Size(size.width, roadWidth))
            }

            // One pin for this school, with its name above it
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 140.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = TextPrimary,
                    shadowElevation = 4.dp,
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Text(
                        text = school.name,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .shadow(6.dp, CircleShape)
                        .clip(CircleShape)
                        .background(AmberAccent)
                        .border(3.dp, Color.White, CircleShape)
                )
            }

            // School card with Get Directions and Open in Maps
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(22.dp),
                color = SurfaceWhite,
                shadowElevation = 10.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("${school.name} School", style = MaterialTheme.typography.titleLarge, color = TextPrimary)
                    Text(
                        text = "${school.city} • ${school.levels.joinToString(", ")} • ${school.type} school",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = { openUrl("https://www.google.com/maps/dir/?api=1&destination=$query") },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
                        ) {
                            Text("Get Directions", fontWeight = FontWeight.SemiBold)
                        }
                        OutlinedButton(
                            onClick = { openUrl("geo:0,0?q=$query") },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(1.5.dp, TealPrimary)
                        ) {
                            Text("Open in Maps", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SchoolLocationScreenPreview() {
    EnarahTheme { SchoolLocationScreen(SampleData.schools.first(), {}, {}) }
}
