package com.onats.cardinfofinder

import com.onats.cardinfofinder.di.DaggerTestAppComponent
import com.onats.cardinfofinder.framework.CardInfoFinderApp

class TestCardInfoApp: CardInfoFinderApp() {

    val testAppComponent by lazy {
        DaggerTestAppComponent.builder()
            .application(this)
            .build()
    }
}