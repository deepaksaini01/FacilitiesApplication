package com.assignment.facilities.parser

import androidx.room.TypeConverter
import com.assignment.facilities.data.dto.ExclusionDto
import com.assignment.facilities.data.dto.FacilitiesDataDto
import com.assignment.facilities.data.dto.FacilitiesDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun facilitiesDtoListToJson(facilitiesDtoList: List<FacilitiesDto>?): String? {
        return gson.toJson(facilitiesDtoList)
    }

    @TypeConverter
    fun jsonToFacilitiesDtoList(json: String?): List<FacilitiesDto>? {
        val type = object : TypeToken<List<FacilitiesDto>?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun exclusionsDtoListToJson(exclusionsDtoList: List<List<ExclusionDto>>?): String? {
        return gson.toJson(exclusionsDtoList)
    }

    @TypeConverter
    fun jsonToExclusionsDtoList(json: String?): List<List<ExclusionDto>>? {
        val type = object : TypeToken<List<List<ExclusionDto>>?>() {}.type
        return gson.fromJson(json, type)
    }
}