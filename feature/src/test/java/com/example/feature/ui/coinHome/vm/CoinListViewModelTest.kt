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

package com.example.feature.ui.coinHome.vm

import androidx.lifecycle.SavedStateHandle
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.Resource
import com.example.domain.usecase.CoinsListUseCase
import com.example.feature.ui.coinHome.contract.CoinListContract
import com.example.feature.ui.util.MainCoroutineRule
import com.example.feature.ui.util.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class CoinListViewModelTest{

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var coinsListUseCase: CoinsListUseCase


    private lateinit var mainViewModel: CoinListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create  CoinListViewModel every test
        mainViewModel = CoinListViewModel(
            savedStateHandle = savedStateHandle, getCoinUseCase = coinsListUseCase
        )
    }

    @Test
    fun test_get_CoinList_success() = runBlockingTest {

        val coinListResult = TestDataGenerator.generateCoinListResponse()
        val coinListFlow = flowOf(Resource.Success(coinListResult))

        // Given
        coEvery { coinsListUseCase.execute(any()) } returns coinListFlow

        // When && Assertions
        mainViewModel.uiState.test {
            mainViewModel.setEvent(CoinListContract.Event.GetCoinList)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                CoinListContract.State(
                    coinListState = CoinListContract.CoinListState.Idle
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                CoinListContract.State(
                    coinListState = CoinListContract.CoinListState.Loading
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.coinListState as CoinListContract.CoinListState.Success).result
            Truth.assertThat(expected).isEqualTo(
                CoinListContract.State(
                    coinListState = CoinListContract.CoinListState.Success(coinListResult)
                )
            )

            Truth.assertThat(expectedData).isEqualTo(coinListResult)

            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { coinsListUseCase.execute(any()) }
    }

    @Test
    fun test_get_CoinList_fail() = runBlockingTest {

        val errorFlow = flowOf(Resource.Error("error string"))

        // Given
        coEvery { coinsListUseCase.execute(any()) } returns errorFlow

        // When && Assertions (UiState)
        mainViewModel.uiState.test {
            // Call method inside of this scope
            mainViewModel.setEvent(CoinListContract.Event.GetCoinList)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                CoinListContract.State(
                    coinListState = CoinListContract.CoinListState.Idle
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                CoinListContract.State(
                    coinListState = CoinListContract.CoinListState.Loading
                )
            )
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // When && Assertions (UiEffect)
        mainViewModel.effect.test {
            // Expect ShowError Effect
            val expected = expectItem()
            val expectedData = (expected as CoinListContract.Effect.ShowError).message
            Truth.assertThat(expected).isEqualTo(
                CoinListContract.Effect.ShowError("error string")
            )
            Truth.assertThat(expectedData).isEqualTo("error string")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { coinsListUseCase.execute(any()) }
    }
}