package com.hung.wallup.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hung.wallup.domain.model.Photo

@Entity(tableName = "favorite")
data class FavoritePhoto(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "link_photo") val linkPhoto: String
) {
    fun toDomainPhoto(): Photo {
        return Photo(
            id = this.id,
            coverPhoto = this.linkPhoto
        )
    }
}