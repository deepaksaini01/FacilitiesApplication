package com.assignment.facilities.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.assignment.facilities.presentation.theme.FacilitiesTheme
import com.assignment.facilities.presentation.ui.screens.RootScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacilitiesTheme {
                RootScreen()
            }
        }
    }
}

