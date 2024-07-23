package com.concordia.canary.authmodule.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.concordia.canary.authmodule.ui.theme.gray
import com.concordia.canary.authmodule.ui.theme.orange

@Composable
fun BubbleAnimation(
    bubbleColorStart: Color,
    bubbleColorEnd: Color,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")


    Box(modifier = modifier) {
        val xValueBubble1 by infiniteTransition.animateFloat(
            initialValue = 100f,
            targetValue = 1340f,
            animationSpec = infiniteRepeatable(
                animation = tween(7000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "x val animation"
        )

        val yValueBubble1 by infiniteTransition.animateFloat(
            initialValue = 100f,
            targetValue = 700f,
            animationSpec = infiniteRepeatable(
                animation = tween(6000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "x val animation"
        )

        val xValueBubble2 by infiniteTransition.animateFloat(
            initialValue = 1340f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(8000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "x2 val animation"
        )

        val yValueBubble2 by infiniteTransition.animateFloat(
            initialValue = 400f,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                animation = tween(7000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "y2 val animation"
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(
                        bubbleColorStart,
                        bubbleColorEnd
                    ),
                    start = Offset(xValueBubble1 - 90, yValueBubble1),
                    end = Offset(xValueBubble1 + 90, yValueBubble1)
                ),
                radius = 100f,
                center = Offset(xValueBubble1, yValueBubble1)
            )

            drawCircle(
                brush = Brush.linearGradient(
                    listOf(
                        bubbleColorStart,
                        bubbleColorEnd
                    ),
                    start = Offset(xValueBubble2 - 90, yValueBubble2),
                    end = Offset(xValueBubble2 + 90, yValueBubble2)
                ),
                radius = 70f,
                center = Offset(xValueBubble2, yValueBubble2)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BubbleAnimationPreview() {
    BubbleAnimation(
        bubbleColorStart = orange,
        bubbleColorEnd = gray,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
    )
}