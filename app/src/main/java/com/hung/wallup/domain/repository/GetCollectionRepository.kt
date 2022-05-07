package com.hung.wallup.domain.repository

import com.hung.wallup.data.remote.model.CollectionDTO

interface GetCollectionRepository {
    suspend fun getListPhoto(query:String):CollectionDTO
}