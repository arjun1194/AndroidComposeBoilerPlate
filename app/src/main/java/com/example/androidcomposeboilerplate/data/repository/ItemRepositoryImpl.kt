package com.example.androidcomposeboilerplate.data.repository

import com.example.androidcomposeboilerplate.data.local.ItemDao
import com.example.androidcomposeboilerplate.data.remote.ApiService
import com.example.androidcomposeboilerplate.domain.models.Item
import com.example.androidcomposeboilerplate.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: ItemDao
) : ItemRepository {

    override fun getItems(): Flow<List<Item>> {
        return itemDao.getItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun refreshItems() {
        try {
            val remoteItems = apiService.fetchItems()
            itemDao.insertItems(remoteItems.map { it.toEntity() })
        } catch (e: Exception) {
            // Error handling can be enhanced using a Result wrapper
            e.printStackTrace()
        }
    }
}
