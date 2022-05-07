package com.hung.wallup.domain.use_case

import com.hung.wallup.data.local.db.entity.DownloadedPhoto
import com.hung.wallup.domain.repository.DownloadedPhotoRepository
import com.hung.wallup.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DownloadedPhotoUseCase @Inject constructor(private val repository: DownloadedPhotoRepository) {

    suspend fun isDownloaded(id: String) = flow {
        try {
            emit(Resource.Loading())
            val data = repository.isDownloaded(id)
            val result = data?.let { true } ?: false
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun addDownloadedPhoto(data: DownloadedPhoto) {
        repository.addDownloadedPhoto(data)
    }
}

