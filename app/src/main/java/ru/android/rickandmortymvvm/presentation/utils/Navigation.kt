package ru.android.rickandmortymvvm.presentation.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.navigateWithAnimations(
    @IdRes resId: Int,
    args: Bundle? = null,
    navigatorExtras: Navigator.Extras? = null
): Unit = navigate(resId, args, NavigationUtils.createDefaultAnimations().build(), navigatorExtras)

fun NavController.navigateWithAnimations(
    destination: NavDirections
): Unit = navigate(destination, NavigationUtils.createDefaultAnimations().build())

fun NavController.navigateWithBackwardAnimations(
    @IdRes resId: Int,
    args: Bundle? = null,
    navigatorExtras: Navigator.Extras? = null,
    optionsBlock: (NavOptions.Builder.() -> NavOptions.Builder)? = null
) {
    val animations = NavigationUtils.createBackwardAnimations()
    val options = optionsBlock?.invoke(animations) ?: animations
    navigate(resId, args, options.build(), navigatorExtras)
}

fun NavController.navigateWithBackwardAnimations(
    destination: NavDirections,
    optionsBlock: (NavOptions.Builder.() -> NavOptions.Builder)? = null
) {
    val animations = NavigationUtils.createBackwardAnimations()
    val options = optionsBlock?.invoke(animations) ?: animations
    navigate(destination, options.build())
}