package com.hung.wallup.presentation.detail

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hung.wallup.data.local.db.entity.DownloadedPhoto
import com.hung.wallup.data.local.db.entity.FavoritePhoto
import com.hung.wallup.domain.model.Photo
import com.hung.wallup.domain.use_case.DownloadedPhotoUseCase
import com.hung.wallup.domain.use_case.FavoritePhotoUseCase
import com.hung.wallup.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val downloadManager: DownloadManager,
    private val wallpaperManager: WallpaperManager,
    private val usecaseFavorite: FavoritePhotoUseCase,
    private val usecaseDownload: DownloadedPhotoUseCase
) : ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState
    var mDownloadId: Long? = null
    private var photo: Photo? = null

    fun checkIsFavorite() {
        viewModelScope.launch {
            usecaseFavorite.isFavoritePhoto(photo!!.id).collect {
                when (it) {
                    is Resource.Success -> {
                        _detailState.value = _detailState.value.copy(isFavorite = it.data!!)
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                        Log.e("DetailViewModel", it.message.toString())
                    }
                }
            }
        }
    }

    fun setPhoto(photo: Photo) {
        this.photo = photo
    }

    fun addToFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            usecaseFavorite.addFavoritePhoto(FavoritePhoto(photo!!.id, photo!!.coverPhoto))
        }
    }

    fun removeFromFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            usecaseFavorite.removeFavoritePhoto(photo!!.id)
        }
    }

    fun downloadImage() {
        val req = DownloadManager.Request(Uri.parse(photo!!.coverPhoto))
            .setTitle("Wall Up")
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "${photo!!.id}.jpg"
            )

        val downloadId = downloadManager.enqueue(req)
        mDownloadId = downloadId
        viewModelScope.launch {
            _detailState.value = detailState.value.copy(isStartDownload = true)
            launch(Dispatchers.IO) {
                usecaseDownload.addDownloadedPhoto(DownloadedPhoto(photo!!.id))
            }
        }

    }

    fun setWallpaper(context: Context, drawable: Drawable?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bitmap = (drawable as? BitmapDrawable)?.bitmap
                val bytes = ByteArrayOutputStream()
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                val path = MediaStore.Images.Media.insertImage(
                    context.contentResolver, bitmap, System.currentTimeMillis().toString(), null
                )

                startActivity(
                    context,
                    wallpaperManager.getCropAndSetWallpaperIntent(Uri.parse(path)),
                    null
                )
                _detailState.value = detailState.value.copy(isSetWallpaper = true)
            } catch (e: Exception) {
                e.printStackTrace()
                _detailState.value = detailState.value.copy(error = "Unsuccessful Set Wallpaper")
            }
        }
    }

}