package com.ram.animationhub.views


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun AnimateItemPlacement() {
    var reversed by remember { mutableStateOf(false) }
    val itemsList = remember(reversed) { if (reversed) (1..8).toList().reversed() else (1..8).toList() }

    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(itemsList, key = { it }) { n ->
                    Box(
                        Modifier
                            .size(56.dp)
                            .animateItemPlacement()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF26C6DA)),
                        contentAlignment = Alignment.Center
                    ) { Text("$n", color = Color.White) }
                }
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { reversed = !reversed }) { Text("Reorder") }
        }
    }
}
