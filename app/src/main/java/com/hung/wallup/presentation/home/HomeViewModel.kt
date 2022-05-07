package com.hung.wallup.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hung.wallup.domain.use_case.DownloadedPhotoUseCase
import com.hung.wallup.domain.use_case.HomeUseCase
import com.hung.wallup.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val downloadUseCase: DownloadedPhotoUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    init {
        getHomeState()
    }

    private fun getHomeState() {
        getSuggestPhotos()
        getColors()
        getCategories()
    }

    private fun getColors() {
        viewModelScope.launch {
            homeUseCase.getColorList().collect {
                _homeState.value = _homeState.value.copy(
                    colorList = it
                )
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            homeUseCase.getCategoryList().collect {
                _homeState.value = _homeState.value.copy(
                    categories = it
                )
            }
        }
    }

    private fun getSuggestPhotos() {
        viewModelScope.launch {
            homeUseCase.getSuggestPhotos().collect {
                when (it) {
                    is Resource.Success -> {
                        for (item in it.data!!) {
                                downloadUseCase.isDownloaded(item.id).collect{ rs ->
                                    rs.data?.let {data-> item.isDownloaded=data }
                                }
                        }

                        _homeState.value = _homeState.value.copy(
                            suggestPhotos = it.data
                        )
                    }
                    is Resource.Error -> Log.e("HomeViewModel", it.message.toString())
                    is Resource.Loading -> {
                    }
                }
            }
        }
    }


}