package com.onats.cardinfofinder.framework.data

import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CardInformationApiService {

    @GET("{bin}")
    suspend fun fetchCardDetails(@Path("bin") bin: String): Response<CardInformationResponse>
}