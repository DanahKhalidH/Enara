package com.example.enarah.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
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

@Composable
fun ContactSchoolScreen(
    school: School,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "Contact School", onBack = onBack) },
        bottomBar = { EnarahBottomNavBar(currentRoute = ScreenRoutes.Home.route, onNavigate = onNavigate) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("${school.name} Inclusive", style = MaterialTheme.typography.titleLarge, color = TealPrimary)

            // 1. اتصال هاتفي
            ContactChannelItem(
                icon = Icons.Outlined.Call,
                title = "Call School",
                subtitle = school.phone,
                onClick = {
                    val cleanPhone = school.phone.filter { it.isDigit() || it == '+' }
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$cleanPhone"))
                    launchSafeIntent(context, intent, "Opening dialer...")
                }
            )

            // 2. واتساب
            ContactChannelItem(
                icon = Icons.Outlined.Chat,
                title = "WhatsApp",
                subtitle = school.whatsapp,
                onClick = {
                    val digitsOnly = school.whatsapp.filter { it.isDigit() }
                    val url = "https://wa.me/$digitsOnly"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    launchSafeIntent(context, intent, "WhatsApp is not installed on this device")
                }
            )

            // 3. البريد الإلكتروني
            ContactChannelItem(
                icon = Icons.Outlined.Email,
                title = "Email",
                subtitle = school.email,
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:${school.email}")
                        putExtra(Intent.EXTRA_SUBJECT, "Inquiry about ${school.name}")
                    }
                    launchSafeIntent(context, intent, "No email app found on this device")
                }
            )

            Spacer(modifier = Modifier.height(6.dp))

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = SurfaceWhite,
                border = BorderStroke(1.dp, BorderSubtle),
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Contact information", style = MaterialTheme.typography.titleMedium)
                    ContactDetailRow("Phone", school.phone)
                    HorizontalDivider(color = BorderSubtle)
                    ContactDetailRow("WhatsApp", school.whatsapp)
                    HorizontalDivider(color = BorderSubtle)
                    ContactDetailRow("Email", school.email)
                }
            }
        }
    }
}

private fun launchSafeIntent(context: Context, intent: Intent, fallbackMessage: String) {
    try {
        context.startActivity(intent)
    } catch (_: Exception) {
        Toast.makeText(context, fallbackMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun ContactChannelItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        color = SurfaceWhite,
        border = BorderStroke(1.dp, BorderSubtle),
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(TealLightTint),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = TealPrimary)
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = TextPrimary)
                    Text(subtitle, fontSize = 12.sp, color = TextSecondary)
                }
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = TextSecondary)
        }
    }
}

@Composable
private fun ContactDetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = TextSecondary, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.SemiBold, color = TextPrimary, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactSchoolScreenPreview() {
    EnarahTheme { ContactSchoolScreen(SampleData.schools.first(), {}, {}) }
}