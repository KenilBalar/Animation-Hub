package com.ram.animationhub.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author ASUS
 * @date 20-08-2025
 */

@Preview(showBackground = true)
@Composable

fun ValueBased() {
    var big by remember { mutableStateOf(false) }
    var on by remember { mutableStateOf(false) }

    val size by animateDpAsState(
        targetValue = if (big) 200.dp else 120.dp,
        animationSpec = tween(durationMillis = 300),
        label = "size"
    )

    val tint by animateColorAsState(
        targetValue = if (on) Color(0xFF4CAF50) else Color(0xFFEF5350),
        animationSpec = tween(durationMillis = 500), // custom duration
        label = "color"
    )

    val corner by animateDpAsState(
        targetValue = if (big) 64.dp else 20.dp,
        animationSpec = tween(durationMillis = 300),
        label = "corner"
    )

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier
                .size(size)
                .clip(RoundedCornerShape(corner))
                .background(tint),
            contentAlignment = Alignment.Center
        ) {
            Text(text = if (on) "ON" else "OFF", color = Color.White)
        }
        Spacer(Modifier.height(32.dp))

        Button(
            { big = !big },
            modifier = Modifier.fillMaxWidth(0.4f)
        ) { Text(if (big) "Shrink" else "Enlarge") }

        Button(
            { on = !on },
            modifier = Modifier.fillMaxWidth(0.4f)
        ) { Text("Toggle Color") }
    }
}