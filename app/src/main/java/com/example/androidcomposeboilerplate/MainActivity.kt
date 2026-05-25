package com.example.androidcomposeboilerplate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidcomposeboilerplate.presentation.feature.ItemScreen
import com.example.androidcomposeboilerplate.presentation.feature.ItemViewModel
import com.example.androidcomposeboilerplate.theme.AndroidComposeBoilerPlateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AndroidComposeBoilerPlateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: ItemViewModel = hiltViewModel()
                    ItemScreen(
                        viewModel = viewModel,
                        onShowToast = { message ->
                            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
