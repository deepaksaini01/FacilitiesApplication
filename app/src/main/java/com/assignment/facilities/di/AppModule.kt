package com.assignment.facilities.di

import android.app.Application
import androidx.room.Room
import com.assignment.facilities.data.datasource.local.MyDao
import com.assignment.facilities.data.datasource.local.MyDatabase
import com.assignment.facilities.data.datasource.preferences.PreferencesDataSource
import com.google.gson.GsonBuilder
import com.assignment.facilities.data.datasource.remote.ApiService
import com.assignment.facilities.data.repository.FacilitiesRepositoryImpl
import com.assignment.facilities.utils.Constants.BASE_URL
import com.assignment.facilities.domain.repository.FacilitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val gson = GsonBuilder().create()

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesMyDao(application: Application): MyDao {
        return Room.databaseBuilder(
            application,
            MyDatabase::class.java,
            "bharat.db"
        ).build().myDao
    }

    @Singleton
    @Provides
    fun providesRepository(
        apiService: ApiService,
        myDao: MyDao,
        preferencesDataSource: PreferencesDataSource
    ): FacilitiesRepository {
        return FacilitiesRepositoryImpl(
            apiService = apiService,
            myDao = myDao,
            preferencesDataSource =preferencesDataSource
        )
    }


}