package com.hung.wallup.di.module

import android.content.Context
import androidx.room.Room
import com.hung.wallup.data.local.db.PhotoDataBase
import com.hung.wallup.data.local.db.dao.DownloadedPhotoDAO
import com.hung.wallup.data.local.db.dao.FavoritePhotoDAO
import com.hung.wallup.data.local.repository.DownloadedPhotoRepositoryImpl
import com.hung.wallup.data.local.repository.FavoritePhotosRepositoryImpl
import com.hung.wallup.data.remote.api.PhotoAPI
import com.hung.wallup.data.remote.repository.GetCollectionRepositoryImpl
import com.hung.wallup.data.remote.repository.HomeRepositoryImpl
import com.hung.wallup.domain.repository.DownloadedPhotoRepository
import com.hung.wallup.domain.repository.FavoritePhotosRepository
import com.hung.wallup.domain.repository.GetCollectionRepository
import com.hung.wallup.domain.repository.HomeRepository
import com.hung.wallup.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePhotoAPI(): PhotoAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().callTimeout(10, TimeUnit.SECONDS).build()).build()
            .create(PhotoAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(photoAPI: PhotoAPI): HomeRepository {
        return HomeRepositoryImpl(photoAPI)
    }

    @Provides
    @Singleton
    fun provideGetCollectionRepository(photoAPI: PhotoAPI): GetCollectionRepository {
        return GetCollectionRepositoryImpl(photoAPI)
    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PhotoDataBase::class.java, "db_photo").build()

    @Singleton
    @Provides
    fun provideDownloadedPhotoDAO(db: PhotoDataBase): DownloadedPhotoDAO = db.downloadedPhotoDAO()

    @Singleton
    @Provides
    fun provideFavoritePhotoDAO(db: PhotoDataBase): FavoritePhotoDAO = db.favoritePhotoDAO()

    @Singleton
    @Provides
    fun provideCheckDownloadedRepository(dao: DownloadedPhotoDAO): DownloadedPhotoRepository {
        return DownloadedPhotoRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideIsFavoriteRepository(dao: FavoritePhotoDAO): FavoritePhotosRepository {
        return FavoritePhotosRepositoryImpl(dao)
    }
}