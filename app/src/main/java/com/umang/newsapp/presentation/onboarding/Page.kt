package com.umang.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.umang.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int

)

val pages = listOf(
    Page(
        title = "Khabar",
        description = "Welcome to the App",
        image = R.drawable.onboarding1
    ),

    Page(
        title = "Khabar",
        description = "Daily News from across India",
        image = R.drawable.onboarding2
    ),

    Page(
        title = "Khabar",
        description = "Get Started",
        image = R.drawable.onboarding3
    )
)