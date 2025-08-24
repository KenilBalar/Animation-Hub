package com.ram.animationhub.views


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(20.dp))
        Card(shape = RoundedCornerShape(30)) {
            Row(
                Modifier
                    .padding(16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
git sta
                Text("-", fontSize = 36.sp, textAlign = TextAlign.Center, modifier = Modifier
                    .size(44.dp)
                    .clip(shape = RoundedCornerShape(50))
                    .clickable {
                        lastCount = count; count--
                    })

                AnimatedContent(
                    targetState = count,
                    transitionSpec = {
                        (slideInVertically { if (count > lastCount) it else -it } + fadeIn()).togetherWith(
                            slideOutVertically { if (count > lastCount) -it else it } + fadeOut()
                        )
                    }, label = "counter"
                ) { value ->
                    Box(modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center) {
                        Text("$value", fontSize = 128.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Text("+", fontSize = 36.sp, textAlign = TextAlign.Center, modifier = Modifier
                    .size(44.dp)
                    .clip(shape = RoundedCornerShape(50))
                    .clickable {
                        lastCount = count; count++
                    })
            }
        }
    }

}