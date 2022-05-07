package com.hung.wallup.presentation.collection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hung.wallup.domain.model.Photo
import com.hung.wallup.domain.use_case.DownloadedPhotoUseCase
import com.hung.wallup.domain.use_case.FavoritePhotoUseCase
import com.hung.wallup.domain.use_case.GetCollectionUseCase
import com.hung.wallup.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val collectionUseCase: GetCollectionUseCase,
    private val downloadUseCase: DownloadedPhotoUseCase,
    private val favoritePhotoUseCase: FavoritePhotoUseCase
) :
    ViewModel() {

    private val _collectionState = MutableStateFlow(CollectionState())
    val collectionState: StateFlow<CollectionState> = _collectionState

    fun getCollection(topic: String) {
        viewModelScope.launch {
            collectionUseCase.getCollection(topic).collect {
                when (it) {
                    is Resource.Success -> {
                        for (item in it.data!!) {
                            checkDownloaded(item)
                        }
                        _collectionState.value = it.data.let { it1 ->
                            _collectionState.value.copy(
                                count = it1.size,
                                photos = it1
                            )
                        }
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                        Log.e("CollectionViewModel", "Error: ${it.message}")
                    }
                }
            }
        }
    }

    fun getFavoritePhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritePhotoUseCase.getListFavoritePhoto().collect {
                when (it) {
                    is Resource.Success -> {
                        for (item in it.data!!) {
                            checkDownloaded(item)
                        }
                        _collectionState.value = it.data.let { it1 ->
                            _collectionState.value.copy(
                                count = it1.size,
                                photos = it1
                            )
                        }
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                        Log.e("CollectionViewModel", "Error: ${it.message}")
                    }
                }
            }

        }
    }

    private fun checkDownloaded(photo: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            downloadUseCase.isDownloaded(photo.id).collect { rs ->
                when (rs) {
                    is Resource.Success -> {
                        rs.data?.let { data -> photo.isDownloaded = data }
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                        Log.e("CollectionViewModel", "Error: ${rs.message}")
                    }
                }
            }
        }
    }
}

