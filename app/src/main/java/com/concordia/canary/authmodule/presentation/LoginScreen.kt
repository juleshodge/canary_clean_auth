package com.concordia.canary.authmodule.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.sharp.Create
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.concordia.canary.authmodule.presentation.components.BubbleAnimation
import com.concordia.canary.authmodule.presentation.components.CustomButton
import com.concordia.canary.authmodule.presentation.components.HeaderBackground
import com.concordia.canary.authmodule.presentation.components.NavDestinationHelper
import com.concordia.canary.authmodule.presentation.components.TextEntryModule
import com.concordia.canary.authmodule.presentation.viewmodel.LoginViewModel
import com.concordia.canary.authmodule.ui.theme.gray
import com.concordia.canary.authmodule.ui.theme.orange
import com.concordia.canary.authmodule.ui.theme.white
import com.concordia.canary.authmodule.ui.theme.whiteGray
import com.concordia.canary.authmodule.ui.theme.whiteGrayOrange

@Composable
fun LoginScreen(
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
                style = MaterialTheme.typography.headlineLarge,
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
                .padding(top = 200.dp)
                .fillMaxWidth()
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp, 15.dp, 10.dp, 5.dp)
                .align(Alignment.TopCenter)
        )


        BubbleAnimation(
            bubbleColorStart = whiteGrayOrange,
            bubbleColorEnd = orange,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
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
                    .padding(start = 5.dp)
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


@Composable
fun LoginContainer(
    emailVal: () -> String,
    passwordVal: () -> String,
    buttonEnabled: () -> Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    isPasswordShown: () -> Boolean,
    onTrailingPasswordIconClicked: () -> Unit,
    errorHint: () -> String?,
    isLoading: () -> Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = "Email Address",
            hint = "jh@email.com",
            leadingIcon = Icons.Default.Email,
            textValue = emailVal(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onEmailChanged,
            onTrailingIconClick = null
        )

        var passwordTransformation: VisualTransformation = PasswordVisualTransformation()

        if (isPasswordShown()) {
            passwordTransformation = VisualTransformation.None;
        }

        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = "Password",
            hint = "Enter password",
            leadingIcon = Icons.Default.Check,
            textValue = passwordVal(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onPasswordChanged,
            onTrailingIconClick = {
                onTrailingPasswordIconClicked()
            },
            trailingIcon = Icons.Sharp.Create,
            visualTransformation = passwordTransformation,
            keyboardType = KeyboardType.Password
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            CustomButton(
                text = "Login",
                btnBackGroundColor = orange,
                contentColor = white,
                isEnabled = buttonEnabled(),
                isLoading = isLoading(),
                onButtonClick = onLoginButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp))

            )
            Text(
                text = errorHint() ?: "",
                color = orange,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}