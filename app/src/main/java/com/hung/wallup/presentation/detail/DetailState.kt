package com.hung.wallup.presentation.detail

data class DetailState(val isStartDownload: Boolean=false,
                       val error:String?=null,
                       val isFavorite:Boolean=false,
                       val isSetWallpaper:Boolean=false
)