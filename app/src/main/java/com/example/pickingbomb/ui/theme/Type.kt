package com.example.pickingbomb.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pickingbomb.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val FontFamily = FontFamily(
    Font(
        R.font.dm_sans_medium,
        weight = FontWeight.Normal)
)

val normal = TextStyle(
    fontSize = 100.sp,
    fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
    fontWeight = FontWeight.Normal,
    color = Color.White
)


