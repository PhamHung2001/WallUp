package com.hung.wallup.domain.repository

import com.hung.wallup.data.remote.model.PhotoDTOItem
import com.hung.wallup.domain.model.Category
import com.hung.wallup.domain.model.ColorItem

interface HomeRepository {
    suspend fun getRandomPhotos(): List<PhotoDTOItem>

    suspend fun getColorList(): List<ColorItem>

    suspend fun getCategoryList(): List<Category>
}