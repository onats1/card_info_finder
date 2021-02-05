package com.onats.cardinfofinder.framework

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.onats.cardinfofinder.R
import com.onats.cardinfofinder.databinding.ActivityMainBinding
import com.onats.cardinfofinder.framework.di.AppComponent
import com.onats.cardinfofinder.framework.di.ViewModelFactory
import com.onats.cardinfofinder.framework.ui.CardDetailsViewModel
import com.onats.cardinfofinder.util.EspressoIdlingResource
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val appComponent: AppComponent get() = (application as CardInfoFinderApp).appComponent

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CardDetailsViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        appComponent.inject(this)
        binding.searchButton.setOnClickListener {
            val number = binding.numberInput.text.toString()
            when {
                number.isEmpty() -> {
                    binding.numberInput.error = getString(R.string.empty_field_error)
                }
                number.length < 6 -> {
                    binding.numberInput.error = getString(R.string.error_message_number_length)
                }
                else -> {
                    viewModel.getCardDetails(number)
                }
            }
            EspressoIdlingResource.increment()
        }
        binding.numberInput.doOnTextChanged { text, _, _, _ ->
            when {
                text.isNullOrEmpty() -> {
                    if (binding.cardDetailsContainer.visibility == View.VISIBLE) {
                        viewModel.resetState()
                    }
                }
            }
        }

        viewModel.viewState.observe(this) { dataState ->
            dataState.loading.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.progress = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
            dataState.data?.let { data ->
                EspressoIdlingResource.decrement()
                binding.cardDetailsContainer.visibility = View.VISIBLE
                binding.cardDetails = data
            }
            dataState.error?.let { error ->
                EspressoIdlingResource.decrement()
                Snackbar.make(binding.root, error.errorMessage, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}