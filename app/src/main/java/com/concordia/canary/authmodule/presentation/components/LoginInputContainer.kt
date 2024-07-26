package com.concordia.canary.authmodule.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.sharp.Create
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.concordia.canary.authmodule.ui.theme.gray
import com.concordia.canary.authmodule.ui.theme.orange
import com.concordia.canary.authmodule.ui.theme.shadow
import com.concordia.canary.authmodule.ui.theme.spacing
import com.concordia.canary.authmodule.ui.theme.white
import com.concordia.canary.authmodule.util.UiText

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
    errorHint: () -> UiText?,
    isLoading: () -> Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.largeSpacing)
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
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.smallSpacing)
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
                    .shadow(
                        MaterialTheme.shadow.normalShadow,
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
