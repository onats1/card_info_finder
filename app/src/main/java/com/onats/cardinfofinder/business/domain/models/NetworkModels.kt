package com.onats.cardinfofinder.business.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Number(
    val length: Int?,
    val luhn: Boolean?
)

@JsonClass(generateAdapter = true)
data class Country(
    val alpha2: String?,
    val currency: String?,
    val emoji: String?,
    val latitude: Int?,
    val longitude: Int?,
    val name: String?,
    val numeric: String?
)

@JsonClass(generateAdapter = true)
data class CardInformationResponse(
        val bank: Bank?,
        val brand: String?,
        val country: Country?,
        val number: Number?,
        val prepaid: Boolean?,
        val scheme: String?,
        val type: String?
)

@JsonClass(generateAdapter = true)
data class Bank(
    val city: String?,
    val name: String?,
    val phone: String?,
    val url: String?
)

data class CardInformationNetworkErrorModel(
    val code: String,
    val message: String
) {

    companion object {
        val defaultNetworkError = CardInformationNetworkErrorModel(
            code = "default",
            message = "A network error occurred. Please check your internet connection"
        )

        fun generateCustomErrorModel(code: Int): CardInformationNetworkErrorModel {
            return when (code) {
                404 -> {CardInformationNetworkErrorModel(
                    code = code.toString(),
                    message = "Card Details Not found."
                )}
                else -> defaultNetworkError
            }
        }
    }
}