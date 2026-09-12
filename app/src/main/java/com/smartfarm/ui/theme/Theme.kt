package com.smartfarm.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val FarmGreen = Color(0xFF176B45)
val FarmGreenDark = Color(0xFF0E4F34)
val FarmGreenSoft = Color(0xFFE5F5ED)
val FarmMint = Color(0xFFCFEBDD)
val FarmRed = Color(0xFFC93A3A)
val FarmOrange = Color(0xFFD9872E)
val FarmBg = Color(0xFFF6F8F7)

private val LightColors = lightColorScheme(
    primary = FarmGreen,
    onPrimary = Color.White,
    primaryContainer = FarmGreenSoft,
    onPrimaryContainer = FarmGreenDark,
    secondary = Color(0xFF5C6B63),
    background = FarmBg,
    surface = Color.White,
    error = FarmRed
)

@Composable
fun SmartFarmTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColors, typography = Typography(), content = content)
}
