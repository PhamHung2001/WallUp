package com.hung.wallup.domain.use_case

import com.hung.wallup.data.remote.model.toDomainPhoto
import com.hung.wallup.domain.model.Photo
import com.hung.wallup.domain.repository.DownloadedPhotoRepository
import com.hung.wallup.domain.repository.HomeRepository
import com.hung.wallup.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val hommeRepository: HomeRepository) {
    suspend fun getSuggestPhotos(): Flow<Resource<List<Photo>>> = flow {
        try {
            emit(Resource.Loading())
            val response = hommeRepository.getRandomPhotos()
            val domainData = response.map { it.toDomainPhoto() }
            emit(Resource.Success(data = domainData))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    suspend fun getColorList() = flow {
        emit(hommeRepository.getColorList())
    }

    suspend fun getCategoryList() = flow {
        emit(hommeRepository.getCategoryList())
    }
}