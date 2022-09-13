package com.example.remote.api

import com.example.entity.CoinDetailsResponse
import com.example.entity.CoinListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // TODO: 13/09/2022 Implement pagination
    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=30&page=1&sparkline=false")
    suspend fun coinList(): Response<CoinListResponse>

    @GET("coins/{id}?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false")
    suspend fun coinDetailsById(
        @Path("id") id: String): Response<CoinDetailsResponse>

}