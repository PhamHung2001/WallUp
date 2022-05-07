package com.hung.wallup.domain.repository

import com.hung.wallup.data.local.db.entity.DownloadedPhoto

interface DownloadedPhotoRepository {
    suspend fun isDownloaded(id:String): DownloadedPhoto?
    suspend fun addDownloadedPhoto(data: DownloadedPhoto)
}