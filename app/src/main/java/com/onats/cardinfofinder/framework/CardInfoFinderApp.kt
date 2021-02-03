package com.onats.cardinfofinder.framework

import android.app.Application
import com.onats.cardinfofinder.framework.di.DaggerAppComponent

class CardInfoFinderApp: Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}