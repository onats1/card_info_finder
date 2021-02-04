package com.onats.cardinfofinder.framework

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.onats.cardinfofinder.R
import com.onats.cardinfofinder.databinding.ActivityMainBinding
import com.onats.cardinfofinder.framework.di.AppComponent
import com.onats.cardinfofinder.framework.di.ViewModelFactory
import com.onats.cardinfofinder.framework.ui.CardDetailsViewModel
import com.onats.cardinfofinder.framework.ui.states.CardInformationViewStates
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private val appComponent: AppComponent get() = (application as CardInfoFinderApp).appComponent

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CardDetailsViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        appComponent.inject(this)
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { searchQuery ->
                    if (searchQuery.length >= 6) {
                        viewModel.getCardDetails(searchQuery)
                    } else {
                        Snackbar.make(binding.root, getString(R.string.error_message_number_length), Snackbar.LENGTH_SHORT).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        binding.searchView.setOnCloseListener {
            binding.cardDetails = CardInformationViewStates()
            true
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
                binding.cardDetailsContainer.visibility = View.VISIBLE
                binding.cardDetails = data
            }
            dataState.error?.let { error ->
                Snackbar.make(binding.root, error.errorMessage, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}