package com.onats.cardinfofinder.business.interactors

import com.onats.cardinfofinder.business.data.CardInformationRepository
import com.onats.cardinfofinder.business.domain.models.CardInformationNetworkErrorModel
import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.business.domain.state.DataState
import com.onats.cardinfofinder.framework.ui.states.CardInformationErrorState
import com.onats.cardinfofinder.framework.ui.states.CardInformationViewStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCardDetailsUseCase
@Inject
constructor(
    private val repository: CardInformationRepository
) {

    fun getCardDetails(bin: String): Flow<DataState<CardInformationViewStates, CardInformationErrorState>> = flow {

        val cardDetailsResponse: Flow<DataState<CardInformationResponse, CardInformationNetworkErrorModel>> = repository.fetchCardDetails(bin)

        cardDetailsResponse.collect { cardDetails ->
            cardDetails.data?.let { cardInformationResponse ->
                emit(DataState.data(
                    CardInformationViewStates(
                        cardBrand = cardInformationResponse.brand,
                        bank = cardInformationResponse.bank,
                        cardType = cardInformationResponse.type,
                        country = cardInformationResponse.country
                    )
                ))
            }
            cardDetails.error?.let { networkError ->
                emit(DataState.error(
                    CardInformationErrorState(
                        errorMessage = networkError.message
                    )
                ))
            }
        }
    }

}