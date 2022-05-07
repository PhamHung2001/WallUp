package com.hung.wallup.presentation.collection

import com.hung.wallup.domain.model.Photo

data class CollectionState(
    val count: Int? = null,
    val photos: List<Photo>? =null
)
