package com.example.androidcomposeboilerplate.data.remote

import com.example.androidcomposeboilerplate.data.local.ItemEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "price") val price: Double
) {
    fun toEntity(): ItemEntity = ItemEntity(id, name, price)
}
