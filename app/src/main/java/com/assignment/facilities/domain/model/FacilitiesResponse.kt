package com.assignment.facilities.domain.model


data class FacilitiesResponse(
    //val exclusions: List<List<Exclusion?>?>?,
    val facilities: List<Facility?>?
)

data class Exclusion(
    val facilityId: String?,
    val optionsId: String?
)

data class Facility(
    //  val facilityId: String?,
    val name: String?,
    val options: List<Option?>?
)

data class Option(
    val icon: String?,
    // val id: String?,
    val name: String?
)