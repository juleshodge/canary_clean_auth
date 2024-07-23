package com.concordia.canary.authmodule


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import dagger.hilt.android.AndroidEntryPoint
import com.concordia.canary.authmodule.ui.theme.AuthModuleTheme
import com.concordia.canary.authmodule.ui.theme.gray
import com.concordia.canary.authmodule.util.Navigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = gray.toArgb()
        window.navigationBarColor = gray.toArgb()

        enableEdgeToEdge()
        setContent {
            AuthModuleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}