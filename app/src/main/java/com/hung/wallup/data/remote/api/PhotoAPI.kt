package com.hung.wallup.data.remote.api

import com.hung.wallup.data.remote.model.CollectionDTO
import com.hung.wallup.data.remote.model.PhotoDTOItem
import com.hung.wallup.utils.Constants.CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoAPI {
    @Headers("Accept-Version: v1")
    @GET("photos/random?client_id=$CLIENT_ID&count=10&orientation=portrait")
    suspend fun getRandomPhotos(): List<PhotoDTOItem>

    @Headers("Accept-Version: v1")
    @GET("search/collections?client_id=$CLIENT_ID&per_page=30")
    suspend fun getCollection(@Query("query") query: String): CollectionDTO

    @Headers("Accept-Version: v1")
    @GET("photos/{id}?client_id=$CLIENT_ID")
    suspend fun getPhoto(@Path("id") id: String): PhotoDTOItem
}