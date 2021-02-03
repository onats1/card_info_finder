package com.onats.cardinfofinder.business.data

import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import retrofit2.Response


interface CardInformationRemoteDataSource {

    suspend fun fetchCardInformation(bin: String): Response<CardInformationResponse>
}