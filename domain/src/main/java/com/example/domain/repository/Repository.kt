package com.example.domain.repository

import com.example.common.Resource
import com.example.entity.CoinDetailsResponse
import com.example.entity.CoinListResponse
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Repository
 */
interface Repository {

    suspend fun getCoinList() : Flow<Resource<CoinListResponse>>
    suspend fun getCoinDetails(id:String) : Flow<Resource<CoinDetailsResponse>>

}