package com.assignment.facilities.mapping

import com.assignment.facilities.data.dto.ExclusionDto
import com.assignment.facilities.data.dto.FacilitiesDataDto
import com.assignment.facilities.data.dto.FacilitiesDto
import com.assignment.facilities.data.dto.OptionDto
import com.assignment.facilities.domain.model.Exclusion
import com.assignment.facilities.domain.model.FacilitiesResponse
import com.assignment.facilities.domain.model.Facility
import com.assignment.facilities.domain.model.Option

object Mapping {

    fun FacilitiesDataDto.toFacilities(): FacilitiesResponse {
        return FacilitiesResponse(
            // exclusions = exclusions?.map { it?.map { it?.toExclusion() } },
            facilities = facilities?.map { it?.toFacility() }
        )
    }

    fun ExclusionDto.toExclusion(): Exclusion {
        return Exclusion(
            facilityId = facilityId,
            optionsId = optionsId
        )
    }

    private fun FacilitiesDto.toFacility(): Facility {
        return Facility(
            //facilityId = facilityId,
            name = name,
            options = options?.map { it?.toOption() }
        )
    }

    private fun OptionDto.toOption(): Option {
        return Option(
            icon = icon,
            //id = id,
            name = name,
        )
    }

}