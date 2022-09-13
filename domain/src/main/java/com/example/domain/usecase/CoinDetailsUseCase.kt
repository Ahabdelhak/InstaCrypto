package com.example.domain.usecase

import com.example.common.Resource
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.repository.Repository
import com.example.domain.usecase.core.BaseUseCase
import com.example.entity.CoinDetailsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CoinDetailsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<CoinDetailsResponse, String>() {

    override suspend fun buildRequest(params: String?): Flow<Resource<CoinDetailsResponse>> {
        return repository.getCoinDetails(id = params!!).flowOn(dispatcher)
    }
}