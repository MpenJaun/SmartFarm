package com.smartfarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.smartfarm.ui.SmartFarmApp
import com.smartfarm.ui.theme.SmartFarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartFarmTheme { SmartFarmApp() }
        }
    }
}
