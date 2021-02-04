package com.onats.cardinfofinder.data

import com.onats.cardinfofinder.business.data.CardInformationRemoteDataSource
import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import retrofit2.Response

class FakeCardInformationRemoteDataSource: CardInformationRemoteDataSource {

    private val apiService = FakeCardInformationApiService()

    override suspend fun fetchCardInformation(bin: String): Response<CardInformationResponse> {
        return apiService.fetchCardDetails(bin)
    }
}