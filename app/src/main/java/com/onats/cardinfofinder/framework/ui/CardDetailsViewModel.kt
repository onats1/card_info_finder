package com.onats.cardinfofinder.framework.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onats.cardinfofinder.business.domain.state.DataState
import com.onats.cardinfofinder.business.interactors.GetCardDetailsUseCase
import com.onats.cardinfofinder.framework.ui.states.CardInformationErrorState
import com.onats.cardinfofinder.framework.ui.states.CardInformationViewStates
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardDetailsViewModel
@Inject
constructor(
    private val getCardDetailsUseCase: GetCardDetailsUseCase
) : ViewModel() {

    private val _viewState: MutableLiveData<DataState<CardInformationViewStates, CardInformationErrorState>> =
        MutableLiveData()

    val viewState = _viewState

    fun getCardDetails(bin: String) {
        viewModelScope.launch {
            _viewState.value = DataState.loading()
            getCardDetailsUseCase.getCardDetails(bin).collect { results ->
                _viewState.value = results
            }
        }
    }
}