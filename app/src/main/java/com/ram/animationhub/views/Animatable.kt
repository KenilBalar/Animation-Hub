package com.ram.animationhub.views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * @author ASUS
 * @date 20-08-2025
 */

@Composable
fun Animatable() {
    val scope = rememberCoroutineScope()
    val x = remember { Animatable(0f) }
    val y = remember { Animatable(0f) }
    val boxSize = 56.dp

    Card(Modifier.padding(16.dp)) {
        Box(
            Modifier
                .height(180.dp)
                .fillMaxWidth()
        ) {
            val density = LocalDensity.current

            Box(
                Modifier
                    .offset { IntOffset(x.value.roundToInt(), y.value.roundToInt()) }
                    .size(boxSize)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF2196F3))
                    .clickable {
                        scope.launch {
                            // Animate to a random point inside the card bounds
                            val maxX = 1f * (density.run { (300.dp).toPx() } - density.run { boxSize.toPx() })
                            val maxY = 1f * (density.run { (120.dp).toPx() } - density.run { boxSize.toPx() })
                            x.animateTo((0..maxX.toInt()).random().toFloat(), spring(stiffness = Spring.StiffnessMedium))
                            y.animateTo((0..maxY.toInt()).random().toFloat(), spring(stiffness = Spring.StiffnessMedium))
                        }
                    },
                contentAlignment = Alignment.Center
            ) { Text("Tap", color = Color.White) }
        }
        Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            val springBack = spring<Float>(stiffness = Spring.StiffnessLow)
            Button(onClick = { scope.launch { x.animateTo(0f, springBack); y.animateTo(0f, springBack) } }) { Text("Center") }
        }
    }
}
