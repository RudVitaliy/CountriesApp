package com.example.countriesapp.presentation.screens

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.example.countriesapp.presentation.Error
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countriesapp.CountriesApp
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentMainBinding
import com.example.countriesapp.presentation.CountryResult
import com.example.countriesapp.presentation.adapter.CountryAdapter
import com.example.countriesapp.presentation.viewmodels.MainViewModel
import com.example.countriesapp.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private lateinit var countriesAdapter: CountryAdapter

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CountriesApp).component
    }


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        observeViewModel()
        binding.searchCountryButton.setOnClickListener {
            val countryName = binding.searchCountryEditText.text.toString().trim()
            hideKeyboard.invoke(view.context, view)
            viewModel.loadData(countryName)
        }
        viewModel.list.observe(viewLifecycleOwner) {
            countriesAdapter.submitList(it.asReversed())
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            binding.progressLayout.visibility = View.VISIBLE
            binding.statusLayout.visibility = View.GONE
            binding.countryInformationLayout.visibility = View.GONE
            when(it) {
                is Error -> {
                    binding.progressLayout.visibility = View.GONE
                    binding.countryInformationLayout.visibility = View.GONE
                    binding.statusLayout.visibility = View.VISIBLE
                    binding.requestTv.text = getString(R.string.error)
                    binding.statusImage.setImageResource(R.drawable.baseline_error_outline_24)
                }
                is CountryResult -> {
                    binding.progressLayout.visibility = View.GONE
                    binding.statusLayout.visibility = View.GONE
                    binding.countryInformationLayout.visibility = View.VISIBLE
                    binding.squareCountTextView.text = it.area
                    binding.populationCountTextView.text = it.population
                    binding.countryNameTextView.text = it.name
                    binding.capitalNameTextView.text = it.capital
                    loadImage(binding.flagImageView, it.pngOfFlag)
                }
            }
        }
    }

    private val hideKeyboard = fun Context.(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setUpAdapter() {
        val recyclerView = binding.recyclerView
        countriesAdapter = CountryAdapter()
        recyclerView.adapter = countriesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        setUpSwipeListener(recyclerView)
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    private fun setUpSwipeListener(rvCountriesList: RecyclerView){

        val callback = object: ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {

                return super.getSwipeDirs(recyclerView, viewHolder)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipedOperation = countriesAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCountry(swipedOperation)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvCountriesList)
    }

}