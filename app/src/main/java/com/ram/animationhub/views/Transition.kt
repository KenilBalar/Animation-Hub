package com.ram.animationhub.views


import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author ASUS
 * @date 20-08-2025
 */

private enum class BoxState { Collapsed, Expanded }

@Composable
fun Transition() {
    var target by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(targetState = target, label = "boxTransition")

    val size by transition.animateDp(label = "size") { s -> if (s == BoxState.Expanded) 160.dp else 80.dp }
    val radius by transition.animateDp(label = "radius") { s -> if (s == BoxState.Expanded) 32.dp else 8.dp }
    val color by transition.animateColor(label = "color") { s -> if (s == BoxState.Expanded) Color(0xFF66BB6A) else Color(0xFFFFA726) }

    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(size)
                    .clip(RoundedCornerShape(radius))
                    .background(color)
            )
            Spacer(Modifier.height(12.dp))
            Button(onClick = { target = if (target == BoxState.Collapsed) BoxState.Expanded else BoxState.Collapsed }) {
                Text("Toggle Transition")
            }
        }
    }
}