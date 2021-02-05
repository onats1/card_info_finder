package com.onats.cardinfofinder.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onats.cardinfofinder.business.interactors.GetCardDetailsUseCase
import com.onats.cardinfofinder.framework.ui.CardDetailsViewModel
import javax.inject.Inject


class FakeCardViewModelFactory
@Inject
constructor(
    private val cardDetailsUseCase: GetCardDetailsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardDetailsViewModel::class.java)) {
            return CardDetailsViewModel(cardDetailsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}
















