package com.hung.wallup.presentation.home

import com.hung.wallup.domain.model.Category
import com.hung.wallup.domain.model.ColorItem
import com.hung.wallup.domain.model.Photo

data class HomeState(
    val colorList: List<ColorItem> = emptyList(),
    val categories: List<Category> = emptyList(),
    val suggestPhotos: List<Photo>? = null
)