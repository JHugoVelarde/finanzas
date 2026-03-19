package com.example.finanzas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.finanzas.ui.FinanzasApp
import com.example.finanzas.ui.theme.FinanzasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanzasTheme {
                Surface {
                    FinanzasApp()
                }
            }
        }
    }
}