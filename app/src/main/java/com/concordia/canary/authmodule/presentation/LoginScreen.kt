package com.concordia.canary.authmodule.presentation

import androidx.compose.runtime.Composable
import com.concordia.canary.authmodule.ui.theme.AppTheme
import com.concordia.canary.authmodule.ui.theme.Orientation

@Composable
fun LoginScreen(
    onLoginSuccessNavigation: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit
) {
    if (AppTheme.ortMode == Orientation.LANDSCAPE) {
        LoginScreenWide(
            onLoginSuccessNavigation = { onLoginSuccessNavigation() },
            onNavigateToRegisterScreen = { onNavigateToRegisterScreen() }
        )
    } else {
        LoginScreenNarrow(
            onLoginSuccessNavigation = { onLoginSuccessNavigation() },
            onNavigateToRegisterScreen = { onNavigateToRegisterScreen() })
    }
}