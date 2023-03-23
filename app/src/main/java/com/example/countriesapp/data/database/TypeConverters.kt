package com.example.countriesapp.data.database

import androidx.room.TypeConverter
import com.example.countriesapp.domain.entities.Flag
import com.example.countriesapp.domain.entities.Name

class TypeConverters {

    @TypeConverter
    fun fromNameToString(name: Name): String {
        return name.common
    }

    @TypeConverter
    fun fromStringToName(name: String): Name {
        return Name(common = name)
    }

    @TypeConverter
    fun fromFlagToString(flag: Flag): String {
        return flag.png
    }

    @TypeConverter
    fun fromStringToFlag(png: String): Flag {
        return Flag(png = png)
    }

    @TypeConverter
    fun fromCapitalListToString(capital: List<String>): String {
        return capital[0]
    }

    @TypeConverter
    fun fromStringToCapitalList(capital: String): List<String> {
        return listOf(capital)
    }



}