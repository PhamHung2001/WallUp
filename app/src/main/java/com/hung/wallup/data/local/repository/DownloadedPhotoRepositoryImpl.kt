package com.hung.wallup.data.local.repository

import com.hung.wallup.data.local.db.PhotoDataBase
import com.hung.wallup.data.local.db.dao.DownloadedPhotoDAO
import com.hung.wallup.data.local.db.entity.DownloadedPhoto
import com.hung.wallup.domain.repository.DownloadedPhotoRepository

class DownloadedPhotoRepositoryImpl(private val dao: DownloadedPhotoDAO) :
    DownloadedPhotoRepository {
    override suspend fun isDownloaded(id: String): DownloadedPhoto? {
        return dao.isDownloadedPhoto(id)
    }

    override suspend fun addDownloadedPhoto(data: DownloadedPhoto) {
        dao.addDownloadedPhoto(data)
    }
}