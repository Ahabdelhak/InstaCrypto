package com.example.feature.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.feature.data.DataProvider
import com.example.feature.data.model.Crypto
import com.example.feature.ui.coinHome.contract.CoinListContract
import com.example.feature.ui.coinHome.vm.CoinListViewModel
import com.example.feature.compose.theme.InstaCryptoTheme

//@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    //private val viewModel: CompetitionListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //initObservers(viewModel = viewModel)
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

/**
 * Initialize Observers
 */
@Composable
private fun initObservers(viewModel:CoinListViewModel) {
    LaunchedEffect(Unit) {
        viewModel.uiState.collect {
            when (val state = it.coinListState) {
                is CoinListContract.CoinListState.Idle -> {
                    //binding.loadingPb.isVisible = false
                }
                is CoinListContract.CoinListState.Loading -> {
                    //binding.loadingPb.isVisible = true
                }
                is CoinListContract.CoinListState.Success -> {
                    val data = state.result
                   /* adapter.appendList(data.competitions)
                    binding.loadingPb.isVisible = false*/
                }
            }
        }
    }

    /*LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is CompetitionListContract.Effect.ShowError -> {
                    val msg = it.message
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                }
                is CompetitionListContract.Effect.Navigate -> {

                    val action =
                        CompetitionResultFragmentDirections.actionCompetitionResultFragmentToCompetitionDetailsFragment(
                            it.id.toString()
                        )
                    findNavController().navigate(action)
                }
            }
        }
    }*/
}
