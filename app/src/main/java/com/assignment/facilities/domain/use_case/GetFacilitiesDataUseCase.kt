package com.assignment.facilities.domain.use_case

import com.assignment.facilities.domain.repository.FacilitiesRepository
import javax.inject.Inject

class GetFacilitiesDataUseCase @Inject constructor(private val facilitiesRepository: FacilitiesRepository) {

    suspend operator fun invoke() = facilitiesRepository.getFacilitiesData()

}