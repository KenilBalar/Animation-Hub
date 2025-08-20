package com.ram.animationhub.views


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * @author ASUS
 * @date 20-08-2025
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Draggable() {
    val scope = rememberCoroutineScope()
    val offsetX = remember { androidx.compose.animation.core.Animatable(0f) }
    val dragState = rememberDraggableState { delta ->
        scope.launch { offsetX.snapTo(offsetX.value + delta) }
    }

    Card(Modifier.padding(16.dp)) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp), contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFF7043))
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = dragState,
                        onDragStopped = {
                            scope.launch { offsetX.animateTo(0f, spring(stiffness = Spring.StiffnessLow)) }
                        }
                    ), contentAlignment = Alignment.Center
            ) { Text("Drag") }
        }
    }
}
