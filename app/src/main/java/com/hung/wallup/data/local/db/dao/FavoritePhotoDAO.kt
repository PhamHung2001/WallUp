package com.hung.wallup.data.local.db.dao

import androidx.room.*
import com.hung.wallup.data.local.db.entity.FavoritePhoto

@Dao
interface FavoritePhotoDAO {
    @Query("SELECT * FROM favorite")
    fun getListFavoritePhoto():List<FavoritePhoto>?

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun isFavoritePhoto(id: String): FavoritePhoto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoritePhoto(data: FavoritePhoto)

    @Query("DELETE FROM favorite WHERE id = :id")
    fun removeFavoritePhoto(id: String)
}