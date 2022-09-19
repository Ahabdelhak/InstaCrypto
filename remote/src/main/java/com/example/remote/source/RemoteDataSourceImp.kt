package com.example.remote.source

import com.example.data.repository.RemoteDataSource
import com.example.entity.CoinDetailsResponse
import com.example.entity.CoinListResponse
import com.example.remote.api.ApiService
import com.example.remote.api.ErrorMessage
import javax.inject.Inject
import com.google.gson.Gson


class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService) : RemoteDataSource {


    override suspend fun getCoinList(): CoinListResponse {
        val response = apiService.coinList()
        if (response.code() != 200) throw Exception(response.message())
        val networkData = response.body()
        //if (networkData?.error == 404) throw Exception("Error")
        return networkData!!
    }

    override suspend fun getCoinDetails(id:String): CoinDetailsResponse {
        val response = apiService.coinDetailsById(id)
        if (response.code() != 200) throw Exception(getErrorMessage(response.errorBody()!!.string()))
        val networkData = response.body()
        //if (networkData?.errorCode == 404) throw Exception("Error")
        return networkData!!
    }

    private fun getErrorMessage(errorBody: String): String? {
        val gson = Gson()
        val errorResponse: ErrorMessage = gson.fromJson(
            errorBody,
            ErrorMessage::class.java)
        return errorResponse.message
    }
}