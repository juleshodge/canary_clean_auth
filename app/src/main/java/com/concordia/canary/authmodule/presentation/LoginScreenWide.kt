package com.concordia.canary.authmodule.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.concordia.canary.authmodule.presentation.components.HeaderBackground
import com.concordia.canary.authmodule.presentation.components.LoginContainer
import com.concordia.canary.authmodule.presentation.viewmodel.LoginViewModel
import com.concordia.canary.authmodule.ui.theme.AppTheme
import com.concordia.canary.authmodule.ui.theme.orange
import com.concordia.canary.authmodule.ui.theme.shadow
import com.concordia.canary.authmodule.ui.theme.spacing
import com.concordia.canary.authmodule.ui.theme.white
import com.concordia.canary.authmodule.ui.theme.whiteGray
import com.concordia.canary.authmodule.ui.theme.whiteGrayOrange
import com.concordia.canary.authmodule.util.UiText

@Composable
fun LoginScreenWide(
    onLoginSuccessNavigation: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    loginVieModel: LoginViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.3f),
                contentAlignment = Alignment.TopStart
            ) {
                HeaderBackground(
                    leftColor = orange,
                    rightColor = whiteGrayOrange,
                    modifier = Modifier
                        .fillMaxSize()
                )

                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineLarge,
                    color = white,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = AppTheme.dimens.large)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .align(Alignment.CenterVertically)
            ) {
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
                        .padding(top = 200.dp)
                        .fillMaxWidth()
                        .shadow(
                            MaterialTheme.shadow.normalShadow,
                            RoundedCornerShape(MaterialTheme.spacing.mediumRadius)
                        )
                        .background(
                            whiteGray,
                            RoundedCornerShape(MaterialTheme.spacing.mediumRadius)
                        )
                        .padding(
                            MaterialTheme.spacing.mediumSpacing,
                            MaterialTheme.spacing.largeSpacing,
                            MaterialTheme.spacing.mediumSpacing,
                            MaterialTheme.spacing.smallSpacing
                        )
                        .align(Alignment.TopCenter)
                )

            }
        }
    }
}


@Composable
@Preview(device = "spec:width=411dp,height=891dp,dpi=560,isRound=false,chinSize=0dp,orientation=landscape")
fun LoginScreenWidePreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.3f),
                contentAlignment = Alignment.TopStart
            ) {

                HeaderBackground(
                    leftColor = orange,
                    rightColor = whiteGrayOrange,
                    modifier = Modifier
                        .fillMaxSize()
                )

                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineLarge,
                    color = white,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = AppTheme.dimens.large)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .align(Alignment.CenterVertically)
            ) {
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
                        .background(
                            whiteGray,
                            RoundedCornerShape(MaterialTheme.spacing.mediumRadius)
                        )
                        .padding(
                            MaterialTheme.spacing.mediumSpacing,
                            MaterialTheme.spacing.largeSpacing,
                            MaterialTheme.spacing.mediumSpacing,
                            MaterialTheme.spacing.smallSpacing
                        )

                )
            }
        }
    }
}