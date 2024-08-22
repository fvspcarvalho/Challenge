package com.example.aptoidedemo.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.aptoidedemo.R

val OpenSans = FontFamily(
    Font(R.font.open_sans, weight = FontWeight.Normal),
    Font(R.font.open_sans_semibold, weight = FontWeight.SemiBold),
    Font(R.font.open_sans_bold, weight = FontWeight.Bold)
)

val mainTypography: Typography
    @Composable
    get() = Typography(
        displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = OpenSans),
        displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = OpenSans),
        displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = OpenSans),
        headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = OpenSans),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = OpenSans),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = OpenSans),
        titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = OpenSans),
        titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = OpenSans),
        titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = OpenSans),
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = OpenSans),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = OpenSans),
        bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = OpenSans),
        labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = OpenSans),
        labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = OpenSans),
        labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = OpenSans)
    )