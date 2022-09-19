package com.example.feature.ui.coinDetails.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.entity.Coin
import com.example.entity.CoinDetailsResponse
import com.example.feature.R
import com.example.feature.core.BaseFragment
import com.example.feature.databinding.FragmentCoinDetailsBinding
import com.example.feature.extension.loadImagesWithGlide
import com.example.feature.ui.coinDetails.contract.CoinDetailsContract
import com.example.feature.ui.coinDetails.vm.CoinDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailsFragment : BaseFragment<FragmentCoinDetailsBinding>() {

    private val viewModel: CoinDetailsViewModel by viewModels()
    private val args: CoinDetailsFragmentArgs by navArgs()
    private lateinit var coinDetailsResponse: CoinDetailsResponse

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoinDetailsBinding
        get() = FragmentCoinDetailsBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        Log.e("IdTag " , args.id)
        viewModel.setEvent(CoinDetailsContract.Event.GetCoinLDetails(args.id))
        initObservers()
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {
       viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.uiState.collect {
                        when (val state = it.coinDetailsState) {
                            is CoinDetailsContract.CoinDetailsState.Idle -> {
                                binding.loadingPb.isVisible = false
                            }
                            is CoinDetailsContract.CoinDetailsState.Loading -> {
                                binding.loadingPb.isVisible = true
                            }
                            is CoinDetailsContract.CoinDetailsState.Success -> {
                                coinDetailsResponse = state.result
                                bindDataToView(coinDetailsResponse)
                                binding.loadingPb.isVisible = false
                            }
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when (it) {
                    is CoinDetailsContract.Effect.ShowError -> {
                        binding.loadingPb.isVisible = false
                        val msg = it.message
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun bindDataToView(data: CoinDetailsResponse) {
        binding.tvCoinName.text = data.name
        binding.tvHashingAlgorithm.text = data.hashing_algorithm
        binding.tvCurrentPrice.text = "$" + data.market_data.current_price.usd
        data.market_data.price_change_percentage_24h?.let { it -> setIncreaseDecrease(binding.imgIncreasDec, it) }
        binding.tvPriceChangePercentageIn24h.text = data.market_data.price_change_percentage_24h.toString()
        binding.tvDescription.text = data.description.en
        binding.imgCoin.loadImagesWithGlide(data.image.large)
    }

    private fun setIncreaseDecrease(imageView: ImageView, price: Double) {
        if (price.toString().contains("-")) imageView.setImageResource(R.drawable.ic_decrease)
        else imageView.setImageResource(R.drawable.ic_increase)
    }
}