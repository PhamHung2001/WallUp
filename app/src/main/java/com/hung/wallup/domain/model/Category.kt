package com.hung.wallup.domain.model

import androidx.annotation.DrawableRes

data class Category(
    val title: String = "",
    @DrawableRes val imageRes: Int = -1,
)
