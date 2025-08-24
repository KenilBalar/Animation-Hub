# 🎬 Animation Hub

A showcase of Jetpack Compose animation APIs brought together in one project.  
**Animation Hub** demonstrates practical usage of value animations, transitions, visibility, gestures, and more, all in a single hub for developers to learn and explore.

### 🛠 Tech Stack Used
- Jetpack Compose for UI
- Kotlin Coroutines for async animation handling
- Material Design 3
- MVVM-inspired modular structure
- Compose Animation APIs:
  - `animate*AsState`
  - `Animatable`
  - `updateTransition`
  - `AnimatedVisibility`
  - `AnimatedContent`
  - `animateContentSize`
  - `animateItemPlacement`
  - `rememberInfiniteTransition`
  - `graphicsLayer`

---

## 📱 Preview

| Value Based | Transition | Visibility |
|-------------|------------|------------|
| ![ValueBased](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/ValueBased.gif) | ![Transition](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/Transition.gif) | ![Visibility](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/Visibility.gif) |

| Animatable | Animated Content | Animate Content Size |
|------------|------------------|----------------------|
| ![Animatable](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/Animatable.gif) | ![AnimatedContent](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/AnimatedContent.gif) | ![AnimateContentSize](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/AnimateContentSize.gif) |

| Animate Item Placement | Draggable | Graphics Layer |
|-------------------------|-----------|----------------|
| ![AnimateItemPlacement](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/AnimateItemPlacement.gif) | ![Draggable](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/Draggable.gif) | ![GraphicsLayer](https://github.com/KenilBalar/Animation-Hub/blob/main/Preview/GraphicsLayer.gif) |

---

## ⚙️ Included Animations

- **Value Based** → Animate size, color, and corners with `animate*AsState`.  
- **Transition** → Animate multiple properties (size, radius, color) together using `updateTransition`.  
- **Visibility** → Enter/exit animations with `AnimatedVisibility` + `Crossfade`.  
- **Animatable** → Gesture-based movement, springs, and flings with `Animatable`.  
- **Animated Content** → State-based content swap with animated transitions.  
- **Animate Content Size** → Smoothly expand/collapse layouts when content changes.  
- **Animate Item Placement** → Animated list reordering with `animateItemPlacement`.  
- **Draggable** → Drag and snap back with spring physics.  
- **Graphics Layer** → Transformations (rotation, scale, alpha) via `graphicsLayer`.

---

## 🚀 Usage

Clone the repository and run the app:

```bash
git clone https://github.com/KenilBalar/Animation-Hub.git

