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
import com.concordia.canary.authmodule.presentation.viewmodel.RegisterViewModel
import com.concordia.canary.authmodule.ui.theme.gray
import com.concordia.canary.authmodule.ui.theme.orange
import com.concordia.canary.authmodule.ui.theme.padding
import com.concordia.canary.authmodule.ui.theme.spacing
import com.concordia.canary.authmodule.ui.theme.white
import com.concordia.canary.authmodule.ui.theme.whiteGray
import com.concordia.canary.authmodule.ui.theme.whiteGrayOrange
import com.concordia.canary.authmodule.util.UiText

@Composable
fun RegisterScreen(
    onRegisterSuccessNavigation: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    NavDestinationHelper(shouldNavigate = {
        registerViewModel.registerState.isSuccessfullyRegisteredIn
    }, destination = {
        onRegisterSuccessNavigation()
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
                text = "Register",
                style = MaterialTheme.typography.headlineLarge,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        RegisterContainer(
            emailVal = {
                registerViewModel.registerState.emailInput
            },
            passwordVal = {
                registerViewModel.registerState.passwordInput
            },
            repeatedPasswordVal = {
                registerViewModel.registerState.passwordRepeatedInput
            },
            buttonEnabled = {
                registerViewModel.registerState.isInputValid
            },
            onEmailChanged = registerViewModel::onEmailInputChange,
            onPasswordChanged = registerViewModel::onPasswordInputChange,
            onRepeatPasswordInputChange = registerViewModel::onRepeatPasswordInputChange,
            onRegisterClick = registerViewModel::onRegisterClick,
            isPasswordShown = {
                registerViewModel.registerState.isPasswordShow
            },
            onTrailingPasswordIconClicked = registerViewModel::onPasswordToggleView,
            errorHint = { registerViewModel.registerState.errorMessageInput },
            isLoading = { registerViewModel.registerState.isLoading },
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth()
                .shadow(
                    MaterialTheme.padding.smallPadding,
                    RoundedCornerShape(MaterialTheme.spacing.largeRadius)
                )
                .background(whiteGray, RoundedCornerShape(MaterialTheme.spacing.mediumRadius))
                .padding(
                    MaterialTheme.spacing.mediumSpacing,
                    MaterialTheme.spacing.largeSpacing,
                    MaterialTheme.spacing.mediumSpacing,
                    MaterialTheme.spacing.smallSpacing
                )
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
                .padding(bottom = MaterialTheme.padding.mediumPadding)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?",
                color = orange,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Login",
                modifier = Modifier
                    .padding(start = MaterialTheme.padding.smallPadding)
                    .clickable {
                        onNavigateToLoginScreen()
                    },
                color = orange,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


@Composable
fun RegisterContainer(
    emailVal: () -> String,
    passwordVal: () -> String,
    repeatedPasswordVal: () -> String,
    buttonEnabled: () -> Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRepeatPasswordInputChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    isPasswordShown: () -> Boolean,
    onTrailingPasswordIconClicked: () -> Unit,
    errorHint: () -> UiText?,
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

        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = "Repeat Password",
            hint = "Repeat Password",
            leadingIcon = Icons.Default.Check,
            textValue = repeatedPasswordVal(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onRepeatPasswordInputChange,
            onTrailingIconClick = {
                onTrailingPasswordIconClicked()
            },
            trailingIcon = Icons.Sharp.Create,
            visualTransformation = passwordTransformation,
            keyboardType = KeyboardType.Password
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.smallSpacing)
        ) {
            CustomButton(
                text = "Register",
                btnBackGroundColor = orange,
                contentColor = white,
                isEnabled = buttonEnabled(),
                isLoading = isLoading(),
                onButtonClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(
                        MaterialTheme.spacing.smallSpacing,
                        RoundedCornerShape(MaterialTheme.spacing.largeRadius)
                    )

            )
            Text(
                text = errorHint()?.asString() ?: "",
                color = orange,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}