package com.onats.cardinfofinder.framework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.onats.cardinfofinder.R
import com.onats.cardinfofinder.business.interactors.GetCardDetailsUseCase
import com.onats.cardinfofinder.framework.di.AppComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private val appComponent: AppComponent get() = (application as CardInfoFinderApp).appComponent

    @Inject
    lateinit var getCardDetailsUseCase: GetCardDetailsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        lifecycleScope.launch {
            getCardDetailsUseCase.getCardDetails("1234567").collect { data ->
                Log.e("result", "$data")
            }
        }
    }

}