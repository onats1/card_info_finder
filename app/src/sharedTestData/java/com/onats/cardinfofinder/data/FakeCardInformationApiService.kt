package com.onats.cardinfofinder.data

import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.framework.data.CardInformationApiService
import retrofit2.Response

class FakeCardInformationApiService: CardInformationApiService {

    override suspend fun fetchCardDetails(bin: String): Response<CardInformationResponse> {
        TODO()
    }
}