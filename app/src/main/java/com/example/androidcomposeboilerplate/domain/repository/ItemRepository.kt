package com.example.androidcomposeboilerplate.domain.repository

import com.example.androidcomposeboilerplate.domain.models.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    suspend fun refreshItems()
}
