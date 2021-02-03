package com.onats.cardinfofinder.business.data

import com.onats.cardinfofinder.business.domain.models.CardInformationNetworkErrorModel
import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface CardInformationRepository {

    suspend fun fetchCardDetails(bin: String): Flow<DataState<CardInformationResponse, CardInformationNetworkErrorModel>>
}