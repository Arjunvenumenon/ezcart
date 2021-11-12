package com.example.ezcart.data.db

import androidx.room.TypeConverter
import com.example.ezcart.data.model.RemoteOrderItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    var gson = Gson()

    @TypeConverter
    fun remoteOrderItemsToString(foodItems: List<RemoteOrderItems>): String {
        return gson.toJson(foodItems)
    }

    @TypeConverter
    fun stringToRemoteOrderItems(data: String): List<RemoteOrderItems> {
        val listType = object : TypeToken<List<RemoteOrderItems>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}