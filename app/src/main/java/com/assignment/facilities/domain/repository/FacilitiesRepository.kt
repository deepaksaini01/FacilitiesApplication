package com.assignment.facilities.domain.repository

import com.assignment.facilities.domain.model.FacilitiesResponse
import com.tootl.screensaver.utils.Resource
import kotlinx.coroutines.flow.Flow


interface FacilitiesRepository {
    suspend fun getFacilitiesData(): Flow<Resource<FacilitiesResponse?>>
}