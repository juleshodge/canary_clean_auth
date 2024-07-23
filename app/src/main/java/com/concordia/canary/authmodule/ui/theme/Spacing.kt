package com.concordia.canary.authmodule.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val defaultSpacing: Dp = 0.dp,
    val smallSpacing: Dp = 5.dp,
    val mediumSpacing: Dp = 10.dp,
    val largeSpacing: Dp = 15.dp,

    val mediumRadius: Dp = 15.dp,
    val largeRadius: Dp = 25.dp
)

data class Padding(
    val smallPadding: Dp = 5.dp,
    val mediumPadding: Dp = 10.dp,
)

data class Shadow(
    val smallShadow: Dp = 3.dp,
    val normalShadow: Dp = 5.dp,
)


val LocalSpacing = compositionLocalOf { Spacing() }

val LocalPadding = compositionLocalOf { Padding() }

val ShadowPadding = compositionLocalOf { Shadow() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current

val MaterialTheme.shadow: Shadow
    @Composable
    @ReadOnlyComposable
    get() = ShadowPadding.current