package com.hung.wallup.domain.use_case

import com.hung.wallup.data.remote.model.toDomainPhoto
import com.hung.wallup.domain.model.Photo
import com.hung.wallup.domain.repository.DownloadedPhotoRepository
import com.hung.wallup.domain.repository.GetCollectionRepository
import com.hung.wallup.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCollectionUseCase @Inject constructor(private val repository: GetCollectionRepository) {
    suspend fun getCollection(query: String): Flow<Resource<List<Photo>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getListPhoto(query)
            val domainData = response.results?.map { it!!.toDomainPhoto() } ?: emptyList()
            emit(Resource.Success(domainData))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}