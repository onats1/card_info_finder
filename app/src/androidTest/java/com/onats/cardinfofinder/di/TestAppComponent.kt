package com.onats.cardinfofinder.di

import android.app.Application
import com.onats.cardinfofinder.ui.MainActivityTest
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, TestViewModelModule::class])
interface TestAppComponent {

    fun inject(mainActivity: MainActivityTest)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }
}