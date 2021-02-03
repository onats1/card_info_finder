package com.onats.cardinfofinder.business.data

import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.framework.data.CardInformationApiService
import retrofit2.Response
import javax.inject.Inject

class CardInformationRemoteDataSourceImpl
@Inject
constructor(
    private val apiService: CardInformationApiService
) : CardInformationRemoteDataSource {
    override suspend fun fetchCardInformation(bin: String): Response<CardInformationResponse> {
        return apiService.fetchCardDetails(bin)
    }
}