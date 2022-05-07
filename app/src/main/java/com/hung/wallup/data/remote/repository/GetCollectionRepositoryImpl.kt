package com.hung.wallup.data.remote.repository

import com.hung.wallup.data.remote.api.PhotoAPI
import com.hung.wallup.data.remote.model.CollectionDTO
import com.hung.wallup.domain.repository.GetCollectionRepository

class GetCollectionRepositoryImpl(
    private val photoAPI: PhotoAPI
) : GetCollectionRepository {
    override suspend fun getListPhoto(query: String): CollectionDTO {
        return photoAPI.getCollection(query)
    }
}