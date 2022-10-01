/*
 * Copyright 2022 AHMED ABDELHAK. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.feature.ui.coinHome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.feature.core.BaseFragment
import com.example.feature.databinding.FragmentCoinResultBinding
import com.example.feature.ui.coinHome.contract.CoinListContract
import com.example.feature.ui.coinHome.vm.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinResultFragment : BaseFragment<FragmentCoinResultBinding>() {

    private val viewModel: CoinListViewModel by viewModels()

    private val adapter: CoinAdapter by lazy {
        CoinAdapter(emptyList()) { item ->
            viewModel.setEvent(CoinListContract.Event.OnCoinItemClicked(item.id!!))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            //Do something
            activity?.finish()
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoinResultBinding
        get() = FragmentCoinResultBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvCoins.adapter = adapter
        initObservers()
    }


    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (val state = it.coinListState) {
                    is CoinListContract.CoinListState.Idle -> {
                        binding.loadingPb.isVisible = false
                    }
                    is CoinListContract.CoinListState.Loading -> {
                        binding.loadingPb.isVisible = true
                    }
                    is CoinListContract.CoinListState.Success -> {
                        val data = state.result
                        adapter.appendList(data)
                        binding.loadingPb.isVisible = false
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when (it) {
                    is CoinListContract.Effect.ShowError -> {
                        val msg = it.message
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    }
                    is CoinListContract.Effect.Navigate -> {

                        val action =
                            CoinResultFragmentDirections.actionCoinResultFragmentToCoinDetailsFragment(
                                it.id
                            )
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }
}