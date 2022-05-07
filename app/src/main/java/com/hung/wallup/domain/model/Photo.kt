package com.hung.wallup.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(val id:String, val coverPhoto:String, var isDownloaded:Boolean=false): Parcelable
