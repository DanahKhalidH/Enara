package com.example.enarah.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.enarah.ui.theme.*

@Composable
fun MapScreen(
    schools: List<School>,
    onOpenSchoolDetails: (String) -> Unit,
    onNavigate: (String) -> Unit,
    initialSchoolId: String
) {
    val context = LocalContext.current
    var locationQuery by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(0) }

    val filteredSchools = remember(locationQuery, schools) {
        if (locationQuery.isBlank()) schools
        else schools.filter {
            it.name.contains(locationQuery, ignoreCase = true) ||
                    it.city.contains(locationQuery, ignoreCase = true)
        }.ifEmpty { schools }
    }

    val safeIndex = selectedIndex.coerceIn(0, (filteredSchools.size - 1).coerceAtLeast(0))
    val activeSchool = filteredSchools.getOrElse(safeIndex) { schools.first() }

    val pinAlignments = listOf(
        Alignment.TopCenter,
        Alignment.CenterStart,
        Alignment.CenterEnd,
        Alignment.BottomCenter
    )

    Scaffold(
        bottomBar = { EnarahBottomNavBar(currentRoute = ScreenRoutes.Map.route, onNavigate = onNavigate) }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFE6EFEF))
        ) {
            // رسم شبكة شوارع ومنطقة مائية مطابقة لتصميم الخريطة في Figma (صفحة 14)
            Canvas(modifier = Modifier.fillMaxSize()) {
                val roadColor = Color.White.copy(alpha = 0.85f)
                val parkColor = Color(0xFFD9E8DF)
                val waterColor = Color(0xFFCDE2EB)

                // منطقة خضراء وبحرية
                drawRect(
                    color = parkColor,
                    topLeft = Offset(size.width * 0.34f, 0f),
                    size = Size(size.width * 0.32f, size.height * 0.32f)
                )
                drawRect(
                    color = waterColor,
                    topLeft = Offset(size.width * 0.68f, size.height * 0.58f),
                    size = Size(size.width * 0.32f, size.height * 0.42f)
                )

                // شوارع أفقية وعمودية
                val roadWidth = 14.dp.toPx()
                drawRect(roadColor, Offset(size.width * 0.32f, 0f), Size(roadWidth, size.height))
                drawRect(roadColor, Offset(size.width * 0.67f, 0f), Size(roadWidth, size.height))
                drawRect(roadColor, Offset(0f, size.height * 0.31f), Size(size.width, roadWidth))
                drawRect(roadColor, Offset(0f, size.height * 0.63f), Size(size.width, roadWidth))
            }

            // شريط البحث العلوي التفاعلي
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(50),
                color = SurfaceWhite,
                shadowElevation = 6.dp
            ) {
                TextField(
                    value = locationQuery,
                    onValueChange = {
                        locationQuery = it
                        selectedIndex = 0
                    },
                    placeholder = { Text("Search location or school...", color = TextSecondary, fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null, tint = TealPrimary) },
                    trailingIcon = {
                        if (locationQuery.isNotEmpty()) {
                            IconButton(onClick = { locationQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear", tint = TextSecondary)
                            }
                        }
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // دبابيس المدارس مع اسم المدرسة النشطة
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 44.dp, vertical = 150.dp)
            ) {
                filteredSchools.take(4).forEachIndexed { index, school ->
                    val isSelected = index == safeIndex
                    Column(
                        modifier = Modifier.align(pinAlignments[index]),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (isSelected) {
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = TextPrimary,
                                shadowElevation = 4.dp,
                                modifier = Modifier.padding(bottom = 6.dp)
                            ) {
                                Text(
                                    text = school.name,
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .size(if (isSelected) 32.dp else 24.dp)
                                .shadow(6.dp, CircleShape)
                                .clip(CircleShape)
                                .background(if (isSelected) AmberAccent else TealPrimary)
                                .border(3.dp, Color.White, CircleShape)
                                .clickable { selectedIndex = index }
                        )
                    }
                }
            }

            // البطاقة السفلية العائمة للمدرسة المختارة
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
                    Text("${activeSchool.name} School", style = MaterialTheme.typography.titleLarge, color = TextPrimary)
                    Text(
                        text = "${activeSchool.city} • ${activeSchool.levels.joinToString(", ")} • ${activeSchool.type} school",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = { onOpenSchoolDetails(activeSchool.id) },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
                        ) {
                            Text("View details", fontWeight = FontWeight.SemiBold)
                        }
                        OutlinedButton(
                            onClick = {
                                val query = Uri.encode("${activeSchool.name} School ${activeSchool.city}")
                                val mapIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.google.com/maps/search/?api=1&query=$query")
                                )
                                try {
                                    context.startActivity(mapIntent)
                                } catch (_: Exception) {
                                    Toast.makeText(context, "Maps app not found", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(1.5.dp, TealPrimary)
                        ) {
                            Text("Get directions", color = TealPrimary, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MapScreenPreview() {
    EnarahTheme { MapScreen(
        SampleData.schools, {}, {},
        initialSchoolId = TODO(),
    ) }
    // أضف معامل initialSchoolId: String = "" في توقيع MapScreen في MapScreen.kt:
    @Composable
    fun MapScreen(
        schools: List<School>,
        initialSchoolId: String = "",
        onOpenSchoolDetails: (String) -> Unit,
        onNavigate: (String) -> Unit
    ) {
        val context = LocalContext.current
        var locationQuery by remember { mutableStateOf("") }
        var selectedIndex by remember(initialSchoolId) {
            val foundIndex = schools.indexOfFirst { it.id == initialSchoolId }
            mutableIntStateOf(if (foundIndex >= 0) foundIndex else 0)
        }
        // ... باقي كود الخريطة يبقى كما هو تماماً
    }
}