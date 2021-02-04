package com.onats.cardinfofinder.business.data

import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.data.FakeCardInformationApiService
import com.onats.cardinfofinder.data.TestDataSets
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.Test
import retrofit2.Response


class CardInformationRemoteDataSourceImplTest {

    private val testApiService = FakeCardInformationApiService()
    //component in test
    private val cardInfoDataSource = CardInformationRemoteDataSourceImpl(testApiService)

    @Test
    fun test_validCardNumberRequest_returnsValidResponse() = runBlocking {

        val result = cardInfoDataSource.fetchCardInformation(TestDataSets.ValidCardNumberInput)

        assertEquals(result.body(), Response.success(TestDataSets.FakeApiSuccessResponse).body())
    }

    @Test
    fun test_invalidCardNumberRequest_returnsErorResponse() = runBlocking {

        val result = cardInfoDataSource.fetchCardInformation(TestDataSets.InvalidCardNumberInput)

        assertEquals(result.code(), 404)
    }
}