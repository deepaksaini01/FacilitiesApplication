package com.assignment.facilities.presentation.view_model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.facilities.R
import com.assignment.facilities.domain.model.FacilitiesResponse
import com.assignment.facilities.domain.model.Facility
import com.assignment.facilities.domain.model.Option
import com.assignment.facilities.domain.use_case.GetFacilitiesDataUseCase
import com.tootl.screensaver.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFacilitiesDataUseCase: GetFacilitiesDataUseCase
) : ViewModel() {

    var facilitiesResponseData by mutableStateOf<FacilitiesResponse?>(null)
    var selectedOptions by mutableStateOf<List<String>>(emptyList())
    var isLoading by mutableStateOf(false)


    init {
        getFacilitiesData()
    }

    private fun getFacilitiesData() {
        viewModelScope.launch(Dispatchers.IO) {
            getFacilitiesDataUseCase().onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        facilitiesResponseData = resource.data
                        selectedOptions = List(facilitiesResponseData?.facilities?.size ?: 0) { "" }
                        isLoading = false
                    }

                    is Resource.Error -> {
                        facilitiesResponseData = resource.data
                        isLoading = false
                    }

                    is Resource.Loading -> {
                        isLoading = true
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun updateSelectedOption(index: Int, option: String) {
        val updatedOptions = selectedOptions.toMutableList()
        updatedOptions[index] = option
        selectedOptions = updatedOptions
    }

    fun clearSelectedOption() {
        selectedOptions = listOf()
        selectedOptions = List(facilitiesResponseData?.facilities?.size ?: 0) { "" }
    }

    fun getIconResourceId(iconName: String): Int {
        return when (iconName) {
            "apartment" -> R.drawable.apartment
            "condo" -> R.drawable.condo
            "boat" -> R.drawable.boat
            "land" -> R.drawable.land
            "rooms" -> R.drawable.rooms
            "no-room" -> R.drawable.no_room
            "swimming" -> R.drawable.swimming
            else -> R.drawable.garden
        }
    }

    fun isExclusionCombination(
        currentOption: String?,
        selectedOptionOfFirstRow: String?
    ): Boolean {
        if (currentOption == null || selectedOptionOfFirstRow == null) {
            return false
        }

        return when {
            selectedOptionOfFirstRow == "Land" &&
                    currentOption == "1 to 3 Rooms" -> true

            selectedOptionOfFirstRow == "Boat House" &&
                    currentOption == "Garage" -> true

            else -> false
        }
    }
}