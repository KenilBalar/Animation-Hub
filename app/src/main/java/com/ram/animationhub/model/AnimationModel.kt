package com.ram.animationhub.model

/**
 * @author ASUS
 * @date 20-08-2025
 */
data class Animation(val id: AnimationId, val title: String, val description: String)

 enum class AnimationId {
    ValueBased,
    Animatable,
    Visibility,
    Transition,
    Infinite,
    AnimateContentSize,
    AnimatedContent,
    Draggable,
    AnimateItemPlacement,
    GraphicsLayer
}
