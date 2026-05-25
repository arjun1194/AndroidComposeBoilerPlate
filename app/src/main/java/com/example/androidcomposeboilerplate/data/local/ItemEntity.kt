package com.example.androidcomposeboilerplate.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidcomposeboilerplate.domain.models.Item

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val price: Double
) {
    fun toDomain(): Item = Item(id, name, price)
}

fun Item.toEntity(): ItemEntity = ItemEntity(id, name, price)
