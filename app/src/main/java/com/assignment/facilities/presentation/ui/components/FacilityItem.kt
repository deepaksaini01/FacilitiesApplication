package com.assignment.facilities.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.assignment.facilities.domain.model.Facility
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

@Composable
fun FacilityItem(
    facility: Facility,
    onOptionSelected: (String) -> Unit,
    getIconResourceId: KFunction1<String, Int>,
    isExclusionCombination: KFunction2<String?, String?, Boolean>,
    facilityIndex: Int,
    selectedOptions: List<String>
) {
    val selectedOptionInFirstRow = selectedOptions.first()
    val selectedOptionInCurrentRow = selectedOptions[facilityIndex]
    Column {
        Text(
            text = facility.name!!,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            items(facility.options!!) { option ->
                OptionItem(
                    name = option?.name!!,
                    icon = getIconResourceId(option.icon!!),
                    isSelected = option.name == selectedOptionInCurrentRow,
                    isExclusion = isExclusionCombination(option.name, selectedOptionInFirstRow),
                    onSelected = {
                        onOptionSelected(option.name)
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}


@Composable
fun OptionItem(
    name: String,
    icon: Int,
    isSelected: Boolean,
    isExclusion: Boolean,
    onSelected: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable {
                if (!isSelected && !isExclusion) {
                    onSelected()
                }
            }
            .size(80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = name,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (isSelected && !isExclusion) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color(0x99000000),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}