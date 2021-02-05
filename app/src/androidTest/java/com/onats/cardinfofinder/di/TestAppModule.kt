package com.onats.cardinfofinder.di

import com.onats.cardinfofinder.data.FakeCardInformationApiService
import com.onats.cardinfofinder.framework.data.CardInformationApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object TestAppModule {

    @Provides
    @Singleton
    fun provideFakeApiService(): CardInformationApiService {
        return FakeCardInformationApiService()
    }

}