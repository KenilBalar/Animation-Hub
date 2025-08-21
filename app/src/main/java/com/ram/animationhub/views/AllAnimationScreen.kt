package com.ram.animationhub.views

import AnimatableBounded
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
                    title = { Text(title) },
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

            BackHandler(enabled = title != "Animation HUB") {
                title = "Animation HUB"
            }

            AnimatedContent(
                targetState = title,
                transitionSpec = {
                    if (targetState != "Animation HUB") {
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

@Composable
fun AnimationList(onClick: (AnimationId) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
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
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Card(shape = CardDefaults.elevatedShape, onClick = onClick) {
            SectionTitle(title = animation.title, description = animation.description)
        }
    }
}

@Composable
fun SectionTitle(title: String, description: String) {
    Text(
        title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        description,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 2.dp, end = 16.dp, bottom = 10.dp),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun AnimationDetail(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        when (true) {
            (title == AnimationId.ValueBased.name) -> ValueBased()
            (title == AnimationId.Animatable.name) -> AnimatableBounded()
            (title == AnimationId.Visibility.name) -> Visibility()
            (title == AnimationId.Transition.name) -> Transition()
            (title == AnimationId.Infinite.name) -> Infinite()
            (title == AnimationId.AnimateContentSize.name) -> AnimateContentSize()
            (title == AnimationId.AnimatedContent.name) -> AnimatedContent()
            (title == AnimationId.Draggable.name) -> Draggable()
            (title == AnimationId.AnimateItemPlacement.name) -> AnimateItemPlacement()
            (title == AnimationId.GraphicsLayer.name) -> GraphicsLayer()
            else -> ValueBased()
        }
    }
}

val animations = listOf(
    Animation(AnimationId.ValueBased, "Value based animation", "Color and Size animation"),
    Animation(AnimationId.Animatable, "Animatable bounded", "Drag and leave view at specific position"),
    Animation(AnimationId.Visibility, "Visibility animation", "Show/Hide view with animation"),
    Animation(AnimationId.Transition, "Transition animation", "Collapsing/Expanding with multiple props"),
    Animation(AnimationId.Infinite, "Infinite animation", "Looping pulse/breathing effects"),
    Animation(AnimationId.AnimateContentSize, "Layout change animation", "Smoothly resize on content change"),
    Animation(AnimationId.AnimatedContent, "AnimatedContent", "Swap content with directional slide + fade"),
    Animation(AnimationId.Draggable, "Gesture/physics animation", "Drag with spring back"),
    Animation(AnimationId.AnimateItemPlacement, "LazyList item placement", "Animate when list order changes"),
    Animation(AnimationId.GraphicsLayer, "Graphics layer props", "Rotation + scale animation")
)