package com.onats.cardinfofinder.data

import com.onats.cardinfofinder.business.domain.models.*
import com.onats.cardinfofinder.framework.ui.states.CardInformationErrorState
import com.onats.cardinfofinder.framework.ui.states.CardInformationViewStates

object TestDataSets {

    val FakeApiSuccessResponse = CardInformationResponse(
        bank = Bank(
            city = "Lagos",
            name = "Zenith",
            phone = "+234145899625",
            url = "valid site"
        ),
        country = Country(
            alpha2 = "alpha 2",
            name = "Nigeria",
            currency = "NGN",
            emoji = null,
            latitude = null,
            longitude = null,
            numeric = null
        ),
        brand = "Master card",
        cardNumber = CardNumber(
            length = 16,
            luhn = true
        ),
        prepaid = false,
        scheme = "mastercard",
        type = "debit"
    )

    val FakeApiErrorResponse = CardInformationNetworkErrorModel(
        code = "404",
        message = "Card Details Not found."
    )

    val FakeCardInfoViewState = CardInformationViewStates(
        cardType = "debit",
        country = Country(
            alpha2 = "alpha 2",
            name = "Nigeria",
            currency = "NGN",
            emoji = null,
            latitude = null,
            longitude = null,
            numeric = null
        ),
        bank = Bank(
            city = "Lagos",
            name = "Zenith",
            phone = "+234145899625",
            url = "valid site"
        ),
        cardBrand = "Master card"
    )

    val FakeCardErrorViewState = CardInformationErrorState(
        errorMessage = "Card Details Not found."
    )

    const val ValidCardNumberInput = "123456"
    const val InvalidCardNumberInput = "letters"
}