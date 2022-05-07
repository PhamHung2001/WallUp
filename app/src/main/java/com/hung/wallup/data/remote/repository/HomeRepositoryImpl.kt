package com.hung.wallup.data.remote.repository

import com.hung.wallup.R
import com.hung.wallup.data.remote.api.PhotoAPI
import com.hung.wallup.data.remote.model.PhotoDTOItem
import com.hung.wallup.domain.model.Category
import com.hung.wallup.domain.model.ColorItem
import com.hung.wallup.domain.repository.HomeRepository

class HomeRepositoryImpl (private val photoAPI: PhotoAPI) : HomeRepository {

    override suspend fun getRandomPhotos(): List<PhotoDTOItem> {
        return photoAPI.getRandomPhotos()
    }

    override suspend fun getColorList(): List<ColorItem> {
        return listOf(
            ColorItem("#FEB6B7", "Pink"),
            ColorItem("#F6F1F2", "White"),
            ColorItem("#6142E0", "Purple"),
            ColorItem("#34568B", "Classic Blue"),
            ColorItem("#FF6F61", "Living Coral"),
            ColorItem("#6B5B95", "Ultra Violet"),
            ColorItem("#88B04B", "Greenery"),
            ColorItem("#F7CAC9", "Rose Quartz"),
            ColorItem("#92A8D1", "Serenity"),
            ColorItem("#955251", "Marsala"),
            ColorItem("#B565A7", "Radiand Orchid"),
            ColorItem("#009B77", "Emerald"),
            ColorItem("#DD4124", "Tangerine Tango"),
            ColorItem("#D65076", "Honeysucle"),
            ColorItem("#45B8AC", "Turquoise"),
            ColorItem("#EFC050", "Mimosa"),
            ColorItem("#5B5EA6", "Blue Izis"),
            ColorItem("#9B2335", "Chili pepper"),
            ColorItem("#DFCFBE", "Sand Dollar"),
            ColorItem("#55B4B0", "Blue Turquoise"),
            ColorItem("#E15D44", "Tigerlily"),
            ColorItem("#7FCDCD", "Aqua Sky"),
            ColorItem("#BC243C", "True Red"),
            ColorItem("#C3447A", "Fuchsia Rose"),
            ColorItem("#98B4D4", "Cerulean Blue")
        )
    }

    override suspend fun getCategoryList(): List<Category> {
        return listOf(
            Category(title = "Abstract", imageRes = R.drawable.ic_abstract),
            Category(title = "Animals", imageRes = R.drawable.ic_animals),
            Category(title = "Anime", imageRes = R.drawable.ic_anime),
            Category(title = "Art", imageRes = R.drawable.ic_arts),
            Category(title = "Cars", imageRes = R.drawable.ic_cars),
            Category(title = "City", imageRes = R.drawable.ic_city),
            Category(title = "Dark", imageRes = R.drawable.ic_dark),
            Category(title = "Flowers", imageRes = R.drawable.ic_flowers),
            Category(title = "Food", imageRes = R.drawable.ic_food),
            Category(title = "Holidays", imageRes = R.drawable.ic_holidays),
            Category(title = "Love", imageRes = R.drawable.ic_love),
            Category(title = "Macro", imageRes = R.drawable.ic_macro),
            Category(title = "Motorcycles", imageRes = R.drawable.ic_motorcycles),
            Category(title = "Music", imageRes = R.drawable.ic_music),
            Category(title = "Nature", imageRes = R.drawable.ic_nature),
            Category(title = "Space", imageRes = R.drawable.ic_space),
            Category(title = "Sport", imageRes = R.drawable.ic_sports),
            Category(title = "Technologies", imageRes = R.drawable.ic_tech),
            Category(title = "Vector", imageRes = R.drawable.ic_vector),
            Category(title = "Words", imageRes = R.drawable.ic_words),
            Category(title = "Favorite", imageRes = R.drawable.ic_favorite)
        )
    }

}