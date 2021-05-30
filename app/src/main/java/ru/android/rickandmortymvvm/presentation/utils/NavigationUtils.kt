package ru.android.rickandmortymvvm.presentation.utils

import androidx.navigation.NavOptions
import ru.android.rickandmortymvvm.R

object NavigationUtils {

    fun createDefaultAnimations(): NavOptions.Builder = NavOptions.Builder()
        .setEnterAnim(R.anim.anim_nav_enter)
        .setExitAnim(R.anim.anim_nav_exit)
        .setPopEnterAnim(R.anim.anim_nav_enter_pop)
        .setPopExitAnim(R.anim.anim_nav_exit_pop)

    fun createBackwardAnimations(): NavOptions.Builder = NavOptions.Builder()
        .setEnterAnim(R.anim.anim_nav_enter_pop)
        .setExitAnim(R.anim.anim_nav_exit_pop)
}