package com.example.androidcomposeboilerplate.domain.usecases

import com.example.androidcomposeboilerplate.domain.models.Item
import com.example.androidcomposeboilerplate.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    operator fun invoke(): Flow<List<Item>> {
        return itemRepository.getItems()
    }
}
