package com.dayker.viewed.presentation.navigation.navanimations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.NavBackStackEntry
import com.dayker.viewed.presentation.navigation.utils.NavConstants.SCALE_IN_ANIMATION_DURATION
import com.dayker.viewed.presentation.navigation.utils.NavConstants.SCALE_OUT_ANIMATION_DURATION
import com.dayker.viewed.presentation.navigation.utils.NavConstants.SLIDE_ANIMATION_DURATION


fun AnimatedContentTransitionScope<NavBackStackEntry>.intoRightAnimation() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(SLIDE_ANIMATION_DURATION)
    )


fun AnimatedContentTransitionScope<NavBackStackEntry>.intoLeftAnimation() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(SLIDE_ANIMATION_DURATION)
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.outRightAnimation() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(SLIDE_ANIMATION_DURATION)
    )


fun AnimatedContentTransitionScope<NavBackStackEntry>.outLeftAnimation() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(SLIDE_ANIMATION_DURATION)
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.scaleInAnimation() =
    scaleIn(
        animationSpec = tween(
            durationMillis = SCALE_IN_ANIMATION_DURATION,
            easing = FastOutSlowInEasing
        )
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.scaleOutAnimation() =
    scaleOut(
        animationSpec = tween(
            durationMillis = SCALE_OUT_ANIMATION_DURATION,
            easing = LinearOutSlowInEasing
        )
    )



