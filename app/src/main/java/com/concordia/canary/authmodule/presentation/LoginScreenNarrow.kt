package com.concordia.canary.authmodule.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.concordia.canary.authmodule.presentation.components.BubbleAnimation
import com.concordia.canary.authmodule.presentation.components.HeaderBackground
import com.concordia.canary.authmodule.presentation.components.LoginContainer
import com.concordia.canary.authmodule.presentation.components.NavDestinationHelper
import com.concordia.canary.authmodule.presentation.viewmodel.LoginViewModel
import com.concordia.canary.authmodule.ui.theme.AppTheme
import com.concordia.canary.authmodule.ui.theme.orange
import com.concordia.canary.authmodule.ui.theme.padding
import com.concordia.canary.authmodule.ui.theme.shadow
import com.concordia.canary.authmodule.ui.theme.spacing
import com.concordia.canary.authmodule.ui.theme.white
import com.concordia.canary.authmodule.ui.theme.whiteGray
import com.concordia.canary.authmodule.ui.theme.whiteGrayOrange
import com.concordia.canary.authmodule.util.UiText

@Composable
fun LoginScreenNarrow(
    onLoginSuccessNavigation: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    loginVieModel: LoginViewModel = hiltViewModel(),
) {
    NavDestinationHelper(shouldNavigate = {
        loginVieModel.loginState.isSuccessfullyLoggedIn
    }, destination = {
        onLoginSuccessNavigation()
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), contentAlignment = Alignment.Center
        ) {
            HeaderBackground(
                leftColor = orange,
                rightColor = whiteGrayOrange,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyLarge,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        LoginContainer(
            emailVal = {
                loginVieModel.loginState.emailInput
            },
            passwordVal = {
                loginVieModel.loginState.passwordInput
            },
            buttonEnabled = {
                loginVieModel.loginState.isInputValid
            },
            onEmailChanged = loginVieModel::onEmailInputChange,
            onPasswordChanged = loginVieModel::onPasswordInputChange,
            onLoginButtonClick = loginVieModel::onLoginClick,
            isPasswordShown = {
                loginVieModel.loginState.isPasswordShow
            },
            onTrailingPasswordIconClicked = loginVieModel::onPasswordToggleView,
            errorHint = { loginVieModel.loginState.errorMessageInput },
            isLoading = { loginVieModel.loginState.isLoading },
            modifier = Modifier

                .fillMaxWidth()
                .shadow(
                    MaterialTheme.shadow.normalShadow,
                    RoundedCornerShape(MaterialTheme.spacing.mediumRadius)
                )
                .background(whiteGray, RoundedCornerShape(MaterialTheme.spacing.mediumRadius))
                .padding(
                    MaterialTheme.spacing.mediumSpacing,
                    MaterialTheme.spacing.largeSpacing,
                    MaterialTheme.spacing.mediumSpacing,
                    MaterialTheme.spacing.smallSpacing
                )
                .align(Alignment.Center)
        )


        BubbleAnimation(
            bubbleColorStart = whiteGrayOrange,
            bubbleColorEnd = orange,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = AppTheme.dimens.mediumLarge)
        )

        Row(
            modifier = Modifier
                .padding(bottom = MaterialTheme.padding.smallPadding)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No account yet?",
                color = orange,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Register!",
                modifier = Modifier
                    .padding(start = MaterialTheme.padding.smallPadding)
                    .clickable {
                        onNavigateToRegisterScreen()
                    },
                color = orange,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


//@Preview(device = "spec:width=800dp,height=1280dp,dpi=320,isRound=false,chinSize=0dp,orientation=portrait")
@Composable
@Preview
fun LoginScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), contentAlignment = Alignment.Center
        ) {
            HeaderBackground(
                leftColor = orange,
                rightColor = whiteGrayOrange,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }

        LoginContainer(
            emailVal = {
                ""
            },
            passwordVal = {
                ""
            },
            buttonEnabled = {
                true
            },
            onEmailChanged = { TODO() },
            onPasswordChanged = { TODO() },
            onLoginButtonClick = { TODO() },
            isPasswordShown = {
                false
            },
            onTrailingPasswordIconClicked = { TODO() },
            errorHint = { UiText.DynamicText("Fix a error") },
            isLoading = { false },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    MaterialTheme.shadow.normalShadow,
                    RoundedCornerShape(MaterialTheme.spacing.mediumRadius)
                )
                .background(whiteGray, RoundedCornerShape(MaterialTheme.spacing.mediumRadius))
                .padding(
                    MaterialTheme.spacing.mediumSpacing,
                    MaterialTheme.spacing.largeSpacing,
                    MaterialTheme.spacing.mediumSpacing,
                    MaterialTheme.spacing.smallSpacing
                )
                .align(Alignment.Center)

        )


        BubbleAnimation(
            bubbleColorStart = whiteGrayOrange,
            bubbleColorEnd = orange,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = AppTheme.dimens.mediumLarge)
        )

        Row(
            modifier = Modifier
                .padding(bottom = MaterialTheme.padding.smallPadding)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No account yet?",
                color = orange,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Register!",
                modifier = Modifier
                    .padding(start = MaterialTheme.padding.smallPadding)
                    .clickable { TODO() },
                color = orange,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}