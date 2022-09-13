package com.example.data.repository

import com.example.entity.CoinDetailsResponse
import com.example.entity.CoinListResponse


/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {

    suspend fun getCoinList(): CoinListResponse
    suspend fun getCoinDetails(id:String): CoinDetailsResponse

}