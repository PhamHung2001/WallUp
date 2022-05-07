package com.hung.wallup.data.local.repository

import com.hung.wallup.data.local.db.dao.FavoritePhotoDAO
import com.hung.wallup.data.local.db.entity.FavoritePhoto
import com.hung.wallup.domain.repository.FavoritePhotosRepository

class FavoritePhotosRepositoryImpl(private val dao: FavoritePhotoDAO) :
    FavoritePhotosRepository {
    override suspend fun getListFavoritePhoto(): List<FavoritePhoto>? {
        return dao.getListFavoritePhoto()
    }

    override suspend fun isFavoritePhoto(id: String): FavoritePhoto? {
        return dao.isFavoritePhoto(id)
    }

    override suspend fun addFavoritePhoto(data: FavoritePhoto) {
        dao.addFavoritePhoto(data)
    }

    override suspend fun removeFavoritePhoto(id: String) {
        dao.removeFavoritePhoto(id)
    }
}