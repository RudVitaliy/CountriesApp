package com.example.countriesapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.countriesapp.domain.entities.Country

class CountryItemDiffCallback: DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}