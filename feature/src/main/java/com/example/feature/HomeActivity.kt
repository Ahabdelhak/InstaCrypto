package com.example.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.feature.data.DataProvider
import com.example.feature.data.model.Crypto
import com.example.feature.ui.theme.InstaCryptoTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp {
                        startActivity(DetailsActivity.newIntent(this, it))
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navigateToDetails: (Crypto) -> Unit) {
    val cryptos = remember { DataProvider.cryptoList }
    Scaffold(
        content = {
            CryptoHomeContent(navigateToDetails = navigateToDetails,cryptos)
        }
    )
}