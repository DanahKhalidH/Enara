package com.example.enarah.ui.screens

import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.AutoFixHigh
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.ui.components.EnarahPrimaryButton
import com.example.enarah.ui.components.EnarahTopBar
import com.example.enarah.ui.theme.*

@Composable
fun ProfileSetupScreen(
    initialName: String,
    initialCity: String,
    onBack: () -> Unit,
    onSaveProfile: (String, String) -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var email by remember { mutableStateOf("") }
    var city by remember { mutableStateOf(initialCity) }

    val isEmailValid = email.isBlank() || Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val showEmailError = email.isNotEmpty() && !isEmailValid
    val suggestedEmail = remember(email) { suggestCorrectedEmail(email) }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = { EnarahTopBar(title = "My Profile", onBack = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Tell us a little about you so we can personalize your search.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(22.dp))

                Text("Name *", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Name", color = TextSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Email", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { input ->
                        // حذف المسافات تلقائياً أثناء الكتابة
                        email = input.replace(" ", "")
                    },
                    placeholder = { Text("name@example.com", color = TextSecondary) },
                    isError = showEmailError,
                    trailingIcon = {
                        when {
                            showEmailError -> {
                                Icon(
                                    imageVector = Icons.Outlined.ErrorOutline,
                                    contentDescription = "Invalid email",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                            email.isNotEmpty() && isEmailValid && suggestedEmail == null -> {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Valid email",
                                    tint = TealPrimary
                                )
                            }
                        }
                    },
                    supportingText = {
                        if (showEmailError && suggestedEmail == null) {
                            Text(
                                text = "Invalid email format (e.g. name@example.com)",
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 12.sp
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                // شريط التصحيح التلقائي الذكي عند وجود خطأ إملائي أو نقص في البريد
                if (suggestedEmail != null && suggestedEmail != email) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        onClick = { email = suggestedEmail },
                        shape = RoundedCornerShape(12.dp),
                        color = TealLightTint,
                        border = BorderStroke(1.dp, TealPrimary.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.AutoFixHigh,
                                    contentDescription = null,
                                    tint = TealPrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Did you mean $suggestedEmail?",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = TealDark
                                )
                            }
                            Text(
                                text = "Fix",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = TealPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text("Preferred city", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    placeholder = { Text("Dammam", color = TextSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
                Text(
                    text = "You can change this anytime.",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            Column(modifier = Modifier.padding(bottom = 24.dp)) {
                EnarahPrimaryButton(
                    text = "Save Profile",
                    enabled = name.isNotBlank() && isEmailValid,
                    onClick = { onSaveProfile(name.trim(), city.trim()) }
                )
                Text(
                    text = "* Name is required",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// دالة ذكية لاكتشاف الأخطاء الإملائية واقتراح التصحيح التلقائي
private fun suggestCorrectedEmail(input: String): String? {
    val trimmed = input.trim().lowercase()
    if (trimmed.isEmpty()) return null

    // 1. إذا كتب الاسم فقط بدون @ يقترح إكماله بـ @gmail.com
    if (!trimmed.contains("@")) {
        return if (trimmed.length >= 3 && trimmed.all { it.isLetterOrDigit() || it == '.' || it == '_' }) {
            "$trimmed@gmail.com"
        } else null
    }

    val parts = trimmed.split("@")
    if (parts.size != 2 || parts[0].isEmpty()) return null
    val localPart = parts[0]
    val domainPart = parts[1]

    // 2. تصحيح الأخطاء الإملائية الشائعة في النطاقات
    val commonTypos = mapOf(
        "gmial.com" to "gmail.com",
        "gmal.com" to "gmail.com",
        "gmil.com" to "gmail.com",
        "gmail.con" to "gmail.com",
        "gmail.co" to "gmail.com",
        "gmail.cm" to "gmail.com",
        "gmail" to "gmail.com",
        "hotmial.com" to "hotmail.com",
        "hotmal.com" to "hotmail.com",
        "hotmail.con" to "hotmail.com",
        "hotmail" to "hotmail.com",
        "outlok.com" to "outlook.com",
        "outlook.con" to "outlook.com",
        "outlook" to "outlook.com",
        "yaho.com" to "yahoo.com",
        "yahoo.con" to "yahoo.com",
        "yahoo" to "yahoo.com",
        "icoud.com" to "icloud.com",
        "icloud.con" to "icloud.com",
        "icloud" to "icloud.com"
    )

    val fixedDomain = commonTypos[domainPart]
    if (fixedDomain != null) {
        return "$localPart@$fixedDomain"
    }

    // 3. إذا نسي النقطة والامتداد مثل name@example
    if (domainPart.isNotEmpty() && !domainPart.contains(".")) {
        return "$localPart@$domainPart.com"
    }

    return null
}

@Preview(showBackground = true)
@Composable
private fun ProfileSetupScreenPreview() {
    EnarahTheme {
        ProfileSetupScreen("", "", onBack = {}, onSaveProfile = { _, _ -> })
    }
}