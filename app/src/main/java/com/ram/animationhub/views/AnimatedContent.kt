package com.ram.animationhub.views


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author ASUS
 * @date 20-08-2025
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContent() {
    var count by remember { mutableIntStateOf(0) }
    var lastCount by remember { mutableIntStateOf(0) }
    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    (slideInVertically { if (count > lastCount) it else -it } + fadeIn()).togetherWith(
                        slideOutVertically { if (count > lastCount) -it else it } + fadeOut()
                    )
                }, label = "counter"
            ) { value ->
                Text("$value", fontSize = 36.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button({ lastCount = count; count-- }) { Text("-1") }
                Button({ lastCount = count; count++ }) { Text("+1") }
            }
        }
    }
}