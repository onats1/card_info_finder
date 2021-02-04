package com.onats.cardinfofinder.business.interactors

import com.onats.cardinfofinder.business.domain.state.DataState
import com.onats.cardinfofinder.data.FakeCardInformationRepositoryImpl
import com.onats.cardinfofinder.data.TestDataSets
import com.onats.cardinfofinder.framework.ui.states.CardInformationErrorState
import com.onats.cardinfofinder.framework.ui.states.CardInformationViewStates
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@InternalCoroutinesApi
class GetCardDetailsUseCaseTest {

    private val repository = FakeCardInformationRepositoryImpl()

    private val useCase = GetCardDetailsUseCase(repository)

    @Test
    fun testGetCardUseCase_validInput_returnsValidDataState() = runBlocking {
        val result = useCase.getCardDetails(TestDataSets.ValidCardNumberInput)

        result.collect(object :
            FlowCollector<DataState<CardInformationViewStates, CardInformationErrorState>> {
            override suspend fun emit(value: DataState<CardInformationViewStates, CardInformationErrorState>) {
                assertEquals(
                    value,
                    DataState.data<CardInformationViewStates, CardInformationErrorState>(
                        TestDataSets.FakeCardInfoViewState
                    )
                )
            }
        })
    }

    @Test
    fun testGetCareUseCase_invalidInput_returnErrorDataState() = runBlocking {
        val result = useCase.getCardDetails(TestDataSets.InvalidCardNumberInput)

        result.collect(object :
            FlowCollector<DataState<CardInformationViewStates, CardInformationErrorState>> {
            override suspend fun emit(value: DataState<CardInformationViewStates, CardInformationErrorState>) {
                assertEquals(
                    value,
                    DataState.error<CardInformationViewStates, CardInformationErrorState>(
                        TestDataSets.FakeCardErrorViewState
                    )
                )
            }
        })
    }
}