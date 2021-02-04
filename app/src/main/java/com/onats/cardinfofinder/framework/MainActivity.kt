package com.onats.cardinfofinder.framework

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.onats.cardinfofinder.R
import com.onats.cardinfofinder.business.interactors.GetCardDetailsUseCase
import com.onats.cardinfofinder.framework.di.AppComponent
import com.onats.cardinfofinder.framework.di.ViewModelFactory
import com.onats.cardinfofinder.framework.di.ViewModelModule
import com.onats.cardinfofinder.framework.ui.CardDetailsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private val appComponent: AppComponent get() = (application as CardInfoFinderApp).appComponent

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CardDetailsViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        lifecycleScope.launch {

        }
    }

}