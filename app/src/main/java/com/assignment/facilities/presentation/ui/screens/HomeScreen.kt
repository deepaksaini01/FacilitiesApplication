package com.assignment.facilities.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignment.facilities.presentation.ui.components.FacilityItem
import com.assignment.facilities.presentation.view_model.HomeViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Scaffold{
        viewModel.facilitiesResponseData?.facilities?.let { facilities ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Select Facility Options",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                facilities.forEachIndexed { facilityIndex, facility ->
                    FacilityItem(
                        facility = facility!!,
                        facilityIndex = facilityIndex,
                        selectedOptions = viewModel.selectedOptions,
                        getIconResourceId = viewModel::getIconResourceId,
                        isExclusionCombination = viewModel::isExclusionCombination,
                        onOptionSelected = { option ->
                            viewModel.updateSelectedOption(facilityIndex, option)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    onClick = viewModel::clearSelectedOption,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Clear")
                }
            }

        }
    }
}


