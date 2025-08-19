package com.ram.animationhub.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun ValueBased() {
    var big by remember { mutableStateOf(false) }
    var on by remember { mutableStateOf(false) }

    val size by animateDpAsState(if (big) 140.dp else 80.dp, label = "size")
    val tint by animateColorAsState(if (on) Color(0xFF4CAF50) else Color(0xFFEF5350), label = "color")
    val corner by animateDpAsState(if (big) 28.dp else 6.dp, label = "corner")

    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(size)
                    .clip(RoundedCornerShape(corner))
                    .background(tint),
                contentAlignment = Alignment.Center
            ) { Text(if (on) "ON" else "OFF", color = Color.White) }
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button({ big = !big }) { Text(if (big) "Shrink" else "Enlarge") }
                Button({ on = !on }) { Text("Toggle Color") }
            }
        }
    }
}