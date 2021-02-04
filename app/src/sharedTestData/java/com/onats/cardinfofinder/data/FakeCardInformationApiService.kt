package com.onats.cardinfofinder.data

import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.data.TestDataSets.FakeApiSuccessResponse
import com.onats.cardinfofinder.data.TestDataSets.ValidCardNumberInput
import com.onats.cardinfofinder.framework.data.CardInformationApiService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okio.BufferedSource
import retrofit2.Response

class FakeCardInformationApiService : CardInformationApiService {

    override suspend fun fetchCardDetails(bin: String): Response<CardInformationResponse> {
        return if (bin == ValidCardNumberInput) {
            Response.success(FakeApiSuccessResponse)
        } else {
            Response.error(
                404, ResponseBody.Companion.create(
                    "application/json".toMediaTypeOrNull(),
                    ""
                )
            )
        }
    }
}