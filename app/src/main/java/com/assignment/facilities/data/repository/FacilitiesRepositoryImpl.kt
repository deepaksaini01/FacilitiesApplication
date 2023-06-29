package com.assignment.facilities.data.repository

import com.assignment.facilities.data.datasource.local.MyDao
import com.assignment.facilities.data.datasource.preferences.PreferencesDataSource
import com.assignment.facilities.data.datasource.preferences.PreferencesDataSource.Companion.LAST_FETCH_TIME_STAMP
import com.assignment.facilities.data.datasource.remote.ApiService
import com.assignment.facilities.domain.model.FacilitiesResponse
import com.assignment.facilities.domain.repository.FacilitiesRepository
import com.assignment.facilities.mapping.Mapping.toFacilities
import com.tootl.screensaver.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

import java.util.*
import java.util.concurrent.TimeUnit

@Singleton
class FacilitiesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val myDao: MyDao,
    private val preferencesDataSource: PreferencesDataSource
) : FacilitiesRepository {

    private val ONE_DAY_MS = TimeUnit.DAYS.toMillis(1)

    override suspend fun getFacilitiesData(): Flow<Resource<FacilitiesResponse?>> = flow {
        emit(Resource.Loading())

        val localData = myDao.getFacilitiesData()?.toFacilities()

        if (localData == null) {
            // Fetch data from API if localData is null
            fetchFromApi()
        } else {
            emit(Resource.Success(data = localData))

            val lastFetchTimestamp = preferencesDataSource.getLong(LAST_FETCH_TIME_STAMP)
            val currentTimestamp = Calendar.getInstance().timeInMillis

            if (currentTimestamp - lastFetchTimestamp >= ONE_DAY_MS) {
                // Fetch data from API if it has been more than a day since the last fetch
                fetchFromApi()
            }
        }
    }

    private suspend fun FlowCollector<Resource<FacilitiesResponse?>>.fetchFromApi() {
        val remoteData = try {
            apiService.getFacilitiesData()
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
            null
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
            null
        }

        remoteData?.let { data ->
            emit(Resource.Success(data = data.toFacilities()))
            myDao.clearFacilitiesDataDto()
            myDao.insertFacilitiesData(data = data)

            // Update the last fetch timestamp
            preferencesDataSource.putLong(
                key = LAST_FETCH_TIME_STAMP,
                value = Calendar.getInstance().timeInMillis
            )
        }
    }


}
