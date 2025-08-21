package com.ram.animationhub.views


import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author ASUS
 * @date 20-08-2025
 */

data class ColorOption(val id: Int, val name: String, val color: Color)

@Composable
fun Transition() {
    Column(Modifier.fillMaxSize()) {
        Text(
            "Color Picker (animated)",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )
        ColorPickerDemo(
            options = listOf(
                ColorOption(1, "Forest", Color(0xFF2E7D32)),
                ColorOption(2, "Sunrise", Color(0xFFFFA726)),
                ColorOption(3, "Ocean", Color(0xFF0288D1)),
                ColorOption(4, "Lavender", Color(0xFF7E57C2)),
                ColorOption(5, "Rose", Color(0xFFE91E63)),
            )
        )
    }
}

@Composable
fun ColorPickerDemo(options: List<ColorOption>) {
    var selectedId by rememberSaveable { mutableIntStateOf(options.firstOrNull()?.id ?: -1) }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(options, key = { it.id }) { opt ->
            ColorPickerRow(
                option = opt,
                selected = opt.id == selectedId,
                onClick = { selectedId = opt.id }
            )
        }
    }
}

@Composable
private fun ColorPickerRow(
    option: ColorOption,
    selected: Boolean,
    onClick: () -> Unit
) {
    val swatchSize by animateDpAsState(if (selected) 84.dp else 44.dp, label = "swatchSize")
    val radius by animateFloatAsState(if (selected) 50f else 20f, label = "radius")
    val scale by animateFloatAsState(if (selected) 1.2f else 1f, label = "scale")
    val titleSize by animateFloatAsState(if (selected) 22f else 16f, label = "titleSize")

    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .background(if (selected) option.color.copy(alpha = 0.1f) else Color.Transparent)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .graphicsLayer { this.scaleX = scale; this.scaleY = scale }
                .size(swatchSize)
                .clip(RoundedCornerShape(radius.toInt()))
                .background(option.color)
        )
        Text(
            text = option.name,
            fontSize = titleSize.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = Color.Unspecified,
            modifier = Modifier.weight(1f).padding(start = titleSize.dp)
        )
        if (selected) {
            Text("Selected", fontSize = 12.sp, color = Color.Gray)
            Spacer(Modifier.width(16.dp))
        }
    }
}