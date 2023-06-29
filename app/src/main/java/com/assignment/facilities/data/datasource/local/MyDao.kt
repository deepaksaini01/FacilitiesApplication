package com.assignment.facilities.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.facilities.data.dto.FacilitiesDataDto

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacilitiesData(data: FacilitiesDataDto?)

    @Query("select * from FacilitiesTable")
    suspend fun getFacilitiesData(): FacilitiesDataDto?

    @Query("delete from FacilitiesTable")
    suspend fun clearFacilitiesDataDto()


}