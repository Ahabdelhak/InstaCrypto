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

package com.example.remote.source

import androidx.test.filters.SmallTest
import com.example.data.repository.RemoteDataSource
import com.example.remote.api.ApiService
import com.example.data.util.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {

    @MockK
    private lateinit var apiService: ApiService

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = RemoteDataSourceImp(
            apiService = apiService
        )
    }


    @Test
    fun test_get_Coin_List_success() = runBlockingTest {

        val coinListNetwork = TestDataGenerator.generateCoinListResponse()

        // Given
        coEvery { apiService.coinList() } returns Response.success(
            200,
            coinListNetwork
        )

        // When
        val result = remoteDataSource.getCoinList()

        // Then
        coVerify { apiService.coinList() }

        // Assertion
        val expected = coinListNetwork
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_Coin_List_fail() = runBlockingTest {

        // Given
        coEvery { apiService.coinList() } throws Exception()

        // When
        remoteDataSource.getCoinList()

        // Then
        coVerify { apiService.coinList()}

    }


    @Test
    fun test_get_Coin_Details_success() = runBlockingTest {

        val coinDetailsNetwork = TestDataGenerator.generateCoinDetailsResponse()

        // Given
        coEvery { apiService.coinDetailsById("1") } returns Response.success(
            200,
            coinDetailsNetwork
        )

        // When
        val result = remoteDataSource.getCoinDetails("1")

        // Then
        coVerify { apiService.coinDetailsById("1") }

        // Assertion
        val expected = coinDetailsNetwork
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_Coin_Details_fail() = runBlockingTest {

        // Given
        coEvery { apiService.coinDetailsById("1") } throws Exception()

        // When
        remoteDataSource.getCoinDetails("1")

        // Then
        coVerify { apiService.coinDetailsById("1")}

    }
}