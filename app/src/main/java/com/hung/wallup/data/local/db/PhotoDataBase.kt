package com.hung.wallup.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hung.wallup.data.local.db.dao.DownloadedPhotoDAO
import com.hung.wallup.data.local.db.dao.FavoritePhotoDAO
import com.hung.wallup.data.local.db.entity.DownloadedPhoto
import com.hung.wallup.data.local.db.entity.FavoritePhoto

@Database(entities = [DownloadedPhoto::class,FavoritePhoto::class], version = 2)
abstract class PhotoDataBase:RoomDatabase() {
    abstract fun downloadedPhotoDAO(): DownloadedPhotoDAO
    abstract fun favoritePhotoDAO(): FavoritePhotoDAO
}