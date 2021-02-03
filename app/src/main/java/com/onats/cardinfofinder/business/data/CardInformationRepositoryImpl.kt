package com.onats.cardinfofinder.business.data

import com.onats.cardinfofinder.business.domain.models.CardInformationNetworkErrorModel
import com.onats.cardinfofinder.business.domain.models.CardInformationResponse
import com.onats.cardinfofinder.business.domain.state.DataState
import com.onats.cardinfofinder.business.util.errorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CardInformationRepositoryImpl
@Inject
constructor(
    private val cardInformationRemoteDataSource: CardInformationRemoteDataSource
) : CardInformationRepository {


    override suspend fun fetchCardDetails(bin: String): Flow<DataState<CardInformationResponse, CardInformationNetworkErrorModel>> =
        flow {

            val cardInformationResponse = cardInformationRemoteDataSource.fetchCardInformation(bin)
            if (cardInformationResponse.isSuccessful) {
                cardInformationResponse.body()?.let {
                    emit(
                        DataState.data<CardInformationResponse, CardInformationNetworkErrorModel>(it)
                    )
                }
            } else {
                val errorModel = cardInformationResponse.errorBody()
                    ?.errorModel<CardInformationNetworkErrorModel>()
                if (errorModel != null) {
                    emit(
                        DataState.error<CardInformationResponse, CardInformationNetworkErrorModel>(
                            errorModel
                        )
                    )
                } else {
                    val errorCode = cardInformationResponse.code()
                    emit(
                        DataState.error<CardInformationResponse, CardInformationNetworkErrorModel>(
                            CardInformationNetworkErrorModel.generateCustomErrorModel(errorCode)
                        )
                    )
                }
            }
        }.catch {
            emit(
                DataState.error<CardInformationResponse, CardInformationNetworkErrorModel>(
                    CardInformationNetworkErrorModel.defaultNetworkError
                )
            )
        }.flowOn(Dispatchers.IO)


}