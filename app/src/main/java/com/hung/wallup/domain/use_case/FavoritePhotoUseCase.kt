package com.hung.wallup.domain.use_case

import com.hung.wallup.data.local.db.entity.FavoritePhoto
import com.hung.wallup.domain.repository.FavoritePhotosRepository
import com.hung.wallup.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoritePhotoUseCase @Inject constructor(private val repository: FavoritePhotosRepository) {

    suspend fun getListFavoritePhoto() = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getListFavoritePhoto()
            val domainData = data?.map { it.toDomainPhoto() } ?: emptyList()
            emit(Resource.Success(domainData))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    suspend fun isFavoritePhoto(id: String) = flow {
        try {
            emit(Resource.Loading())
            val data = repository.isFavoritePhoto(id)
            val result = data?.let { true }?:false
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun addFavoritePhoto(data: FavoritePhoto) {
        repository.addFavoritePhoto(data)
    }

    suspend fun removeFavoritePhoto(id: String) {
        repository.removeFavoritePhoto(id)
    }
}