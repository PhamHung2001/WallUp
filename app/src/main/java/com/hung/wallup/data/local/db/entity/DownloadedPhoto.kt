package com.hung.wallup.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded")
data class DownloadedPhoto(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
)
