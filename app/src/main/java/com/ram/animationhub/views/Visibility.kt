package com.ram.animationhub.views


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * @author ASUS
 * @date 20-08-2025
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Visibility() {
    var visible by remember { mutableStateOf(true) }
    var state by remember { mutableStateOf(false) }

    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(
                visible = visible,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF90CAF9)), contentAlignment = Alignment.Center
                ) {
                    Text("Hello Visibility")
                }
            }
            Spacer(Modifier.height(12.dp))
            Crossfade(targetState = state, label = "crossfade") { on ->
                Box(
                    Modifier
                        .height(60.dp)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Text(if (on) "State: ON" else "State: OFF", fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button({ visible = !visible }) { Text(if (visible) "Hide" else "Show") }
                Button({ state = !state }) { Text("Crossfade") }
            }
        }
    }
}
