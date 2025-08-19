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
import com.ram.animationhub.ui.theme.AnimationHubTheme

/**
 * @author ASUS
 * @date 20-08-2025
 */


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnimationMasterApp() {
    var title by remember { mutableStateOf<String>("Animation HUB") } // null = list screen
    AnimationHubTheme {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(title)},
                    navigationIcon = {
                        if (title != "Animation HUB") {
                            IconButton(onClick = { title = "Animation HUB" }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        } else null
                    }
                )
            }
        ) { padding ->
            AnimatedContent(
                targetState = title,
                transitionSpec = {
                    if (targetState != "Animation HUB" ) {
                        // List → Detail: slide in from right
                        (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it / 4 } + fadeOut())
                    } else if (targetState == "Animation HUB") {
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
                if (screen == "Animation HUB") {
                    AnimationList(onClick = { title = it.toString() })
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
fun AnimationDetail(title: String) {
    // Single scrollable container per screen
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            when (true) {
                (title == AnimationId.ValueBased.name) -> ValueBased()
                (title == AnimationId.Animatable.name) -> Animatable()
                (title == AnimationId.Visibility.name) -> Visibility()
                (title == AnimationId.Transition.name) -> Transition()
                (title == AnimationId.Infinite.name) -> Infinite()

                else -> ValueBased()
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