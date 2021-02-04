package com.onats.cardinfofinder.data

import com.onats.cardinfofinder.business.data.CardInformationRepository
import com.onats.cardinfofinder.business.domain.models.CardInformationNetworkErrorModel
import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCardInformationRepositoryImpl: CardInformationRepository {

    private val dataSource = FakeCardInformationRemoteDataSource()
    override suspend fun fetchCardDetails(bin: String): Flow<DataState<CardInformationResponse, CardInformationNetworkErrorModel>> = flow {

        val response = dataSource.fetchCardInformation(bin)

        if (response.isSuccessful) {
            emit(DataState.data(
                TestDataSets.FakeApiSuccessResponse
            ))
        } else {
            emit(DataState.error(
                TestDataSets.FakeApiErrorResponse
            ))
        }
    }

}