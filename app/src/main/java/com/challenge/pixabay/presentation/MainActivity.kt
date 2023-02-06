package com.challenge.pixabay.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.challenge.pixabay.presentation.photos_app.PhotosAppScreen
import com.challenge.pixabay.presentation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val windowSize = calculateWindowSizeClass(activity = this)
                PhotosAppScreen(
                    windowSize = windowSize.widthSizeClass
                )
            }
        }
    }
}