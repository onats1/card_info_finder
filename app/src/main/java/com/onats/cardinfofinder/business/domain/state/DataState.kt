package com.onats.cardinfofinder.business.domain.state

/**
 * This acts as a wrapper to aid management of transient data in fragments.
 * */
data class DataState<T, E>(
    var data: T? = null,
    var loading: Boolean = false,
    var error: E? = null
) {

    companion object {

        fun <T, E> error(
            errorBody: E
        ): DataState<T, E> {
            return DataState(
                data = null,
                loading = false,
                error = errorBody
            )
        }

        fun <T, E> loading(): DataState<T, E> {
            return DataState(
                data = null,
                error = null,
                loading = true
            )
        }

        fun <T, E> data(
            data: T? = null
        ): DataState<T, E> {
            return DataState(
                data = data,
                loading = false,
                error = null
            )
        }
    }
}