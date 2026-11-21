package com.example.enarah.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enarah.ui.components.EnarahConcentricLogo
import com.example.enarah.ui.components.EnarahPrimaryButton
import com.example.enarah.ui.theme.*

@Composable
fun WelcomeScreen(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TealPrimary)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        EnarahConcentricLogo()

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            color = SurfaceWhite
        ) {
            Column(
                modifier = Modifier.padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enarah",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = TealPrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Find the right school\nfor your child, with\nconfidence.",
                    fontSize = 16.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                EnarahPrimaryButton(text = "Get Started", onClick = onGetStarted)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Step 1 of 2", fontSize = 12.sp, color = TextSecondary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    EnarahTheme { WelcomeScreen(onGetStarted = {}) }
}