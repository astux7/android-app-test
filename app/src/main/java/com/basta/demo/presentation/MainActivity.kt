package com.basta.demo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.basta.demo.navigation.NavigationRoot
import com.basta.demo.presentation.ui.theme.DemoAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold { paddingValues ->
                    Surface(
                        modifier = Modifier.padding(paddingValues).fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavigationRoot()
                    }
                }
            }
        }
    }
}
