package com.onats.cardinfofinder.framework.di

import com.onats.cardinfofinder.business.data.CardInformationRemoteDataSource
import com.onats.cardinfofinder.business.data.CardInformationRemoteDataSourceImpl
import com.onats.cardinfofinder.business.data.CardInformationRepository
import com.onats.cardinfofinder.business.data.CardInformationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class BindInterfaces {

    @Binds
    abstract fun bindNetworkDataSource(cardInformationDataSource: CardInformationRemoteDataSourceImpl): CardInformationRemoteDataSource

    @Binds
    abstract fun bindCardInfoRepository(cardInformationRepositoryImpl: CardInformationRepositoryImpl): CardInformationRepository

}