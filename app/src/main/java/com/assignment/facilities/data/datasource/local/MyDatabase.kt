package com.assignment.facilities.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.facilities.data.dto.FacilitiesDataDto
import com.assignment.facilities.parser.Converters

@Database(
    entities = [FacilitiesDataDto::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract val myDao: MyDao
}