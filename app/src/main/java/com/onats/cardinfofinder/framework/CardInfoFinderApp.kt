package com.onats.cardinfofinder.framework

import android.app.Application
import com.onats.cardinfofinder.framework.di.DaggerAppComponent

open class CardInfoFinderApp: Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}