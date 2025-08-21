package com.ram.animationhub.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ValueBasedFancyPreview() {
    ValueBased()
}

@Composable
fun ValueBased() {

    var big by rememberSaveable { mutableStateOf(false) }
    var on by rememberSaveable { mutableStateOf(false) }
    var rotate by rememberSaveable { mutableStateOf(false) }
    var duration by rememberSaveable { mutableIntStateOf(500) }
    var easingIndex by rememberSaveable { mutableIntStateOf(0) }

    val easingLabels = listOf("FastOutSlowIn", "LinearOutSlowIn", "FastOutLinearIn", "Linear")
    val easing = when (easingIndex) {
        0 -> FastOutSlowInEasing
        1 -> LinearOutSlowInEasing
        2 -> FastOutLinearInEasing
        else -> LinearEasing
    }

    // Animations (one spec to rule them all)
    val size by animateDpAsState(
        targetValue = if (big) 200.dp else 120.dp,
        animationSpec = tween(durationMillis = duration, easing = easing),
        label = "size"
    )
    val corner by animateDpAsState(
        targetValue = if (big) 28.dp else 12.dp,
        animationSpec = tween(durationMillis = duration, easing = easing),
        label = "corner"
    )
    val tint by animateColorAsState(
        targetValue = if (on) Color(0xFF4CAF50) else Color(0xFFEF5350),
        animationSpec = tween(durationMillis = duration, easing = easing),
        label = "tint"
    )
    val elevationDp by animateDpAsState(
        targetValue = if (big) 12.dp else 2.dp,
        animationSpec = tween(durationMillis = duration, easing = easing),
        label = "elevation"
    )
    val borderWidth by animateDpAsState(
        targetValue = if (on) 4.dp else 0.dp,
        animationSpec = tween(durationMillis = duration, easing = easing),
        label = "border"
    )
    val rotation by animateFloatAsState(
        targetValue = if (rotate) 8f else 0f,       // a subtle tilt feels “material”
        animationSpec = tween(durationMillis = duration, easing = easing),
        label = "rotation"
    )
    val alpha by animateFloatAsState(
        targetValue = if (on) 1f else 0.90f,
        animationSpec = tween(durationMillis = duration, easing = easing),
        label = "alpha"
    )

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Card adds elevation animation nicely
        Card(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable {
                    // Tap card to toggle a compound animation (great for GIFs)
                    big = !big
                    on = !on
                },
            elevation = CardDefaults.cardElevation(defaultElevation = elevationDp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6)),
            border = if (borderWidth > 0.dp) BorderStroke(borderWidth, tint.copy(alpha = 0.5f)) else null
        ) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .size(size)
                    .graphicsLayer {
                        rotationZ = rotation
                        this.alpha = alpha
                    }
                    .clip(RoundedCornerShape(corner))
                    .background(tint),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (on) "ON" else "OFF",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { big = !big }) { Text(if (big) "Shrink" else "Enlarge") }
            Button(onClick = { on = !on }) { Text("Toggle Color") }
            Button(onClick = { rotate = !rotate }) { Text(if (rotate) "Un-tilt" else "Tilt") }
        }

        Spacer(Modifier.height(18.dp))

        // Duration slider
        Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Text("Speed: ${duration}ms", fontSize = 14.sp)
            Slider(
                value = duration.toFloat(),
                onValueChange = { duration = it.toInt().coerceIn(150, 1200) },
                valueRange = 150f..1200f
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items  (easingLabels.indices.toList()) { i ->
                FilterChip(
                    selected = easingIndex == i,
                    onClick = { easingIndex = i },
                    label = { Text(easingLabels[i], fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors()
                )
            }
        }
    }
}
