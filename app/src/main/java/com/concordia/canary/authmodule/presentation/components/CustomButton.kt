package com.concordia.canary.authmodule.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.concordia.canary.authmodule.ui.theme.orange
import com.concordia.canary.authmodule.ui.theme.white

@Composable
fun CustomButton(
    text: String,
    btnBackGroundColor: Color,
    contentColor: Color,
    isEnabled: Boolean = true,
    onButtonClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {
            onButtonClick()
        },
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = btnBackGroundColor,
            contentColor = contentColor,
            disabledContainerColor = btnBackGroundColor,
            disabledContentColor = contentColor
        ),
        enabled = isEnabled
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(text = text, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun buttonPreview() {
    CustomButton(
        text = "Login",
        btnBackGroundColor = orange,
        contentColor = white,
        onButtonClick = { /*TODO*/ },
        isLoading = false,
        modifier = Modifier.fillMaxWidth()

    )
}