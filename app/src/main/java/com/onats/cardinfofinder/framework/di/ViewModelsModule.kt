package com.onats.cardinfofinder.framework.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onats.cardinfofinder.framework.ui.CardDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.InternalCoroutinesApi

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CardDetailsViewModel::class)
    abstract fun bindCardDetailsViewModel(viewModel: CardDetailsViewModel): ViewModel

}

