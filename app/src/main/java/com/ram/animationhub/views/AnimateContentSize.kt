package com.ram.animationhub.views


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
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
fun AnimateContentSize() {
    var expanded by remember { mutableStateOf(false) }
    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text("Expandable Card", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .animateContentSize()
                    .padding(12.dp)
            ) {
                Column {
                    Text("Summary: This resizes smoothly when content changes.")
                    if (expanded) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Details: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere, nisl non commodo volutpat, nunc mi efficitur urna, eu consequat arcu urna non augue."
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { expanded = !expanded }) { Text(if (expanded) "Show Less" else "Show More") }
        }
    }
}