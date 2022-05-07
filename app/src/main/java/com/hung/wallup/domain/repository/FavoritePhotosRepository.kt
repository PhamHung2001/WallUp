package com.hung.wallup.domain.repository

import com.hung.wallup.data.local.db.entity.FavoritePhoto

interface FavoritePhotosRepository {
    suspend fun getListFavoritePhoto():List<FavoritePhoto>?
    suspend fun isFavoritePhoto(id: String): FavoritePhoto?
    suspend fun addFavoritePhoto(data: FavoritePhoto)
    suspend fun removeFavoritePhoto(id: String)
}