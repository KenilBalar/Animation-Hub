import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Preview(showBackground = true)
@Composable
fun AnimatableBounded() {
    val scope = rememberCoroutineScope()
    val x = remember { Animatable(0f) }   // px
    val y = remember { Animatable(0f) }
    val boxSize = 96.dp
    val density = LocalDensity.current
    val decay: DecayAnimationSpec<Float> = rememberSplineBasedDecay()

    // container size (in px)
    var containerW by remember { mutableStateOf(0f) }
    var containerH by remember { mutableStateOf(0f) }
    val boxSizePx = with(density) { boxSize.toPx() }

    fun maxX() = (containerW - boxSizePx).coerceAtLeast(0f)
    fun maxY() = (containerH - boxSizePx).coerceAtLeast(0f)
    fun clampX(v: Float) = v.coerceIn(0f, maxX())
    fun clampY(v: Float) = v.coerceIn(0f, maxY())

    Box(
        Modifier
            .fillMaxSize()
            // measure the real bounds of the drag area
            .onGloballyPositioned { layout ->
                containerW = layout.size.width.toFloat()
                containerH = layout.size.height.toFloat()

                // keep current offsets inside new bounds
                scope.launch { x.snapTo(clampX(x.value)) }
                scope.launch { y.snapTo(clampY(y.value)) }
            }
            .pointerInput(Unit) {
                val tracker = VelocityTracker()
                detectDragGestures(
                    onDragStart = { tracker.resetTracking() },
                    onDrag = { change, drag ->
                        tracker.addPosition(change.uptimeMillis, change.position)
                        change.consume()
                        scope.launch { x.snapTo(clampX(x.value + drag.x)) }
                        scope.launch { y.snapTo(clampY(y.value + drag.y)) }
                    },
                    onDragEnd = {
                        // limit fling to container by updating bounds first
                        x.updateBounds(0f, maxX())
                        y.updateBounds(0f, maxY())
                        val v = tracker.calculateVelocity()
                        scope.launch { x.animateDecay(v.x, decay) }
                        scope.launch { y.animateDecay(v.y, decay) }
                    }
                )
            }
    ) {
        Box(
            Modifier
                .offset { IntOffset(x.value.roundToInt(), y.value.roundToInt()) }
                .size(boxSize)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF2196F3)),
            contentAlignment = Alignment.Center
        ) { Text("Drag me", color = Color.White) }
    }
}