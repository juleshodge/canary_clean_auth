package com.concordia.canary.authmodule.presentation.components


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.concordia.canary.authmodule.ui.theme.orange
import com.concordia.canary.authmodule.ui.theme.shadow
import com.concordia.canary.authmodule.ui.theme.spacing
import com.concordia.canary.authmodule.ui.theme.white

@Composable
fun TextEntryModule(
    description: String,
    hint: String,
    leadingIcon: ImageVector,
    textValue: String,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textColor: Color,
    cursorColor: Color,
    onValueChanged: (String) -> Unit,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = description,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.shadow.smallShadow)
                .border(0.5.dp, textColor, RoundedCornerShape(MaterialTheme.spacing.largeRadius))

                .shadow(
                    MaterialTheme.shadow.smallShadow,
                    RoundedCornerShape(MaterialTheme.spacing.largeRadius)
                ),
            value = textValue,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = white,
                cursorColor = cursorColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(MaterialTheme.spacing.largeRadius),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = cursorColor
                )
            },
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = cursorColor,
                        modifier = Modifier.clickable {
                            if (onTrailingIconClick != null) onTrailingIconClick()
                        }
                    )
                }
            },
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start
                )
            },
            textStyle = MaterialTheme.typography.bodySmall,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation

        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextEntryModulePreview() {
    TextEntryModule(
        description = "Email Address",
        hint = "jmack@gmail.com",
        leadingIcon = Icons.Default.Email,
        textValue = "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 10.dp, 5.dp),
        textColor = Color.Black,
        cursorColor = orange,
        onValueChanged = {},
        trailingIcon = Icons.Filled.Face,
        onTrailingIconClick = { })
}