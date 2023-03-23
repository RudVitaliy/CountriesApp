package com.example.countriesapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.countriesapp.domain.entities.Flag
import com.example.countriesapp.domain.entities.Name

@Entity(tableName = "counties_list")
data class CountryDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: Name,
    val capital: List<String>,
    val population: Long,
    val area: Long,
    val flags: Flag
    )
