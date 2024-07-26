package com.concordia.canary.authmodule.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.concordia.canary.authmodule.ui.theme.Dimensions
import com.concordia.canary.authmodule.ui.theme.Orientation
import com.concordia.canary.authmodule.ui.theme.smallDimensions

@Composable
fun ProvideThemUtil(
    dimensions: Dimensions,
    orientation: Orientation,
    content: @Composable () -> Unit
) {

    val dimSet = remember { dimensions }
    val ortSet = remember { orientation }

    CompositionLocalProvider(
        LocalAppDimens provides dimSet,
        LocalOrtMode provides ortSet,
        content = content
    )
}

val LocalAppDimens = compositionLocalOf {
    smallDimensions
}

val LocalOrtMode = compositionLocalOf {
    Orientation.PORTRAIT
}

