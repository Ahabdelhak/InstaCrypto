package com.example.data.repository

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.Resource
import com.example.data.util.TestDataGenerator
import com.example.domain.repository.Repository
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class RepositoryImpTest {


    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = RepositoryImp(
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun test_getCoinList_success() = runBlockingTest {

        val coinList = TestDataGenerator.generateCoinListResponse()

        // Given
        coEvery { remoteDataSource.getCoinList() } returns coinList

        // When & Assertions
        val flow = repository.getCoinList()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(coinList).isEqualTo(coinList)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getCoinList() }
    }


    @Test
    fun test_getCoinList_fail() = runBlockingTest {

        // Given
        coEvery { remoteDataSource.getCoinList() } throws Exception("Error")

        // When && Assertions
        val flow = repository.getCoinList()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Error).message
            Truth.assertThat(expected).isInstanceOf(Resource.Error::class.java)
            Truth.assertThat(expectedData).isEqualTo("Error")
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getCoinList() }
    }


    @Test
    fun test_getCoinDetails_success() = runBlockingTest {

        val coinDetails = TestDataGenerator.generateCoinDetailsResponse()

        // Given
        coEvery { remoteDataSource.getCoinDetails("1") } returns coinDetails

        // When & Assertions
        val flow = repository.getCoinDetails("1")
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(coinDetails.id).isEqualTo(coinDetails.id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getCoinDetails("1") }
    }


    @Test
    fun test_getCoinDetails_fail() = runBlockingTest {

        // Given
        coEvery { remoteDataSource.getCoinDetails("1") } throws Exception("Error")

        // When && Assertions
        val flow = repository.getCoinDetails("1")
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Error).message
            Truth.assertThat(expected).isInstanceOf(Resource.Error::class.java)
            Truth.assertThat(expectedData).isEqualTo("Error")
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getCoinDetails("1") }
    }
}