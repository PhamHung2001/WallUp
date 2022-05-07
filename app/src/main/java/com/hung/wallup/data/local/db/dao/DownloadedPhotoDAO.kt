package com.hung.wallup.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hung.wallup.data.local.db.entity.DownloadedPhoto
import com.hung.wallup.data.local.db.entity.FavoritePhoto

@Dao
interface DownloadedPhotoDAO {
    @Query("SELECT * FROM downloaded WHERE id = :id")
    fun isDownloadedPhoto(id: String): DownloadedPhoto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDownloadedPhoto(data: DownloadedPhoto)
}