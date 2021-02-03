package com.onats.cardinfofinder.framework.ui.states

import com.onats.cardinfofinder.business.domain.models.Bank
import com.onats.cardinfofinder.business.domain.models.Country

data class CardInformationViewStates(
    val cardBrand: String? = null,
    val cardType: String? = null,
    val bank: Bank? = null,
    val country: Country? = null
)

data class CardInformationErrorState(
    val errorMessage: String
)
