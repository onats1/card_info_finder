package com.onats.cardinfofinder.business.data

import com.onats.cardinfofinder.business.domain.models.CardInformationNetworkErrorModel
import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.business.domain.state.DataState
import com.onats.cardinfofinder.data.FakeCardInformationRemoteDataSource
import com.onats.cardinfofinder.data.TestDataSets
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@InternalCoroutinesApi
class CardInformationRepositoryImplTest {

    private val cardInfoRepo = CardInformationRepositoryImpl(FakeCardInformationRemoteDataSource())

    @Test
    fun testFetchCardDetails_validRequest_returnsValidResponse() = runBlocking {

        val response = cardInfoRepo.fetchCardDetails(TestDataSets.ValidCardNumberInput)

        response.collect(object :
            FlowCollector<DataState<CardInformationResponse, CardInformationNetworkErrorModel>> {

            override suspend fun emit(value: DataState<CardInformationResponse, CardInformationNetworkErrorModel>) {
                assertEquals(
                    value,
                    DataState.data<CardInformationResponse, CardInformationNetworkErrorModel>(
                        TestDataSets.FakeApiSuccessResponse
                    )
                )
            }
        })
    }

    @Test
    fun testFetchCardDetails_invalidRequest_returnsErrorResponse() = runBlocking {

        val response = cardInfoRepo.fetchCardDetails(TestDataSets.InvalidCardNumberInput)

        response.collect( object :
            FlowCollector<DataState<CardInformationResponse, CardInformationNetworkErrorModel>> {

            override suspend fun emit(value: DataState<CardInformationResponse, CardInformationNetworkErrorModel>) {
                assertEquals(
                    value,
                    DataState.error<CardInformationResponse, CardInformationNetworkErrorModel>(
                        TestDataSets.FakeApiErrorResponse
                    )
                )
            }
        })
    }

}