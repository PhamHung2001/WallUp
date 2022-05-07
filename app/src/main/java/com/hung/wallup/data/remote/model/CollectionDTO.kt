package com.hung.wallup.data.remote.model

data class CollectionDTO(
    val results: List<Result?>?,
    val total: Int?, // 10000
    val total_pages: Int? // 334
)