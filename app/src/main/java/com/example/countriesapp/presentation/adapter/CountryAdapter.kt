package com.example.countriesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.countriesapp.R
import com.example.countriesapp.databinding.CountryItemBinding
import com.example.countriesapp.domain.entities.Country

class CountryAdapter: ListAdapter<Country, CountryViewHolder>(CountryItemDiffCallback()) {

    var onItemLongClick: ((Country) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layout = R.layout.country_item
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return CountryViewHolder(binding as CountryItemBinding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countryItem = getItem(position)
        val binding = holder.binding
        binding.areaTv.text = countryItem.area.toString()
        binding.populationTv.text = countryItem.population.toString()
        binding.nameOfCountry.text = countryItem.name.common
        binding.capitalName.text = countryItem.capital[0]
        loadImage(binding.flagImageView, countryItem.flags.png)
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(countryItem)
            true
        }
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}