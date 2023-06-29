package com.assignment.facilities.data.datasource.remote

import com.assignment.facilities.data.dto.FacilitiesDataDto
import retrofit2.http.GET


interface ApiService {
    @GET("/iranjith4/ad-assignment/db")
    suspend fun getFacilitiesData(): FacilitiesDataDto
}