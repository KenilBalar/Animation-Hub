package com.ram.animationhub.views


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

/**
 * @author ASUS
 * @date 20-08-2025
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GraphicsLayer() {
    var rotate by remember { mutableStateOf(false) }
    val angle by animateFloatAsState(if (rotate) 360f else 0f, animationSpec = tween(900), label = "angle")
    val scale by animateFloatAsState(if (rotate) 1.2f else 1f, animationSpec = tween(900), label = "scale")

    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(96.dp)
                    .graphicsLayer { rotationZ = angle; scaleX = scale; scaleY = scale }
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFF42A5F5)),
                contentAlignment = Alignment.Center
            ) { Text("Spin", color = Color.White) }
            Spacer(Modifier.height(12.dp))
            Button(onClick = { rotate = !rotate }) { Text("Animate") }
        }
    }
}