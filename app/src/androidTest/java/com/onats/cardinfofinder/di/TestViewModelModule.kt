package com.onats.cardinfofinder.di

import androidx.lifecycle.ViewModelProvider
import com.onats.cardinfofinder.business.interactors.GetCardDetailsUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@Module
object TestViewModelModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideViewModelFactory(
        useCase: GetCardDetailsUseCase
    ): ViewModelProvider.Factory{
        return FakeCardViewModelFactory(
            useCase
        )
    }
}













