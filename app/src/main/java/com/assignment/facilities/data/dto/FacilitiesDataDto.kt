package com.assignment.facilities.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FacilitiesTable")
data class FacilitiesDataDto(
    @PrimaryKey(autoGenerate = true)
    val key: Int,
    val facilities: List<FacilitiesDto?>?,
    val exclusions: List<List<ExclusionDto?>?>?
)

data class ExclusionDto(
    val facilityId: String?,
    val optionsId: String?
)

data class FacilitiesDto(
    val facilityId: String?,
    val name: String?,
    val options: List<OptionDto?>?
)

data class OptionDto(
    val icon: String?,
    val id: String?,
    val name: String?
)