package com.ram.animationhub.views

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ram.animationhub.model.Animation
import com.ram.animationhub.model.AnimationId

/**
 * @author ASUS
 * @date 20-08-2025
 */


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnimationMasterApp() {
    var current by remember { mutableStateOf<AnimationId?>(null) } // null = list screen
    MaterialTheme(colorScheme = lightColorScheme()) {

        var title = Text(if (current == null) "Animation HUB" else animations.first { it.id == current }.title)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { title },
                    navigationIcon = {
                        if (current != null) {
                            IconButton(onClick = { current = null }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        } else null
                    }
                )
            }
        ) { padding ->
            AnimatedContent(
                targetState = current,
                transitionSpec = {
                    if (targetState != null && initialState == null) {
                        // List → Detail: slide in from right
                        (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it / 4 } + fadeOut())
                    } else if (targetState == null && initialState != null) {
                        // Detail → List: slide in from left
                        (slideInHorizontally { -it / 4 } + fadeIn()).togetherWith(slideOutHorizontally { it } + fadeOut())
                    } else {
                        fadeIn().togetherWith(fadeOut())
                    }
                }, label = "screenSwitch",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) { screen ->
                if (screen == null) {
                    AnimationList(onClick = { current = it })
                } else {
                    AnimationDetail(screen)
                }
            }
        }
    }
}

// ----- List Screen ----------------------------------------------------------
@Composable
fun AnimationList(onClick: (AnimationId) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(animations) { animation ->
            AnimationListItem(animation = animation) { onClick(animation.id) }
        }
    }
}

@Composable
fun AnimationListItem(animation: Animation, onClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp)
    ) {
        SectionTitle(title = animation.title, description = animation.description)
        Divider(thickness = 0.5.dp, color = Color.LightGray.copy(alpha = 0.6f))
    }
}

@Composable
fun SectionTitle(title: String, description: String) {
    Text(
        title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        description,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 16.dp, bottom = 12.dp),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun AnimationDetail(id: AnimationId) {
    // Single scrollable container per screen
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            when (id) {
                AnimationId.ValueBased -> ValueBased()
                AnimationId.Animatable -> Animatable()
                AnimationId.Visibility -> Visibility()
                AnimationId.Transition -> Transition()
                AnimationId.Infinite -> Infinite()
            }
        }

    }
}


val animations = listOf(
    Animation(AnimationId.ValueBased, "Value based animation", "Color and Size animation"),
    Animation(AnimationId.Animatable, "Animatable (suspend control)", "Animate arbitrary floats with coroutines"),
    Animation(AnimationId.Visibility, "Visibility animation", "Show/Hide view with animation"),
    Animation(AnimationId.Transition, "Transition animation", "Collapsing/Expanding with multiple props"),
    Animation(AnimationId.Infinite, "Infinite animation", "Looping pulse/breathing effects"),

    )