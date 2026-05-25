package com.example.androidcomposeboilerplate.domain.usecases

import com.example.androidcomposeboilerplate.domain.models.Item
import com.example.androidcomposeboilerplate.domain.repository.ItemRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetItemsUseCaseTest {

    private val itemRepository = mockk<ItemRepository>()
    private val getItemsUseCase = GetItemsUseCase(itemRepository)

    @Test
    fun `invoke should return items from repository`() = runTest {
        // Arrange
        val mockItems = listOf(
            Item("1", "Item 1", 10.0),
            Item("2", "Item 2", 20.0)
        )
        every { itemRepository.getItems() } returns flowOf(mockItems)

        // Act
        val resultFlow = getItemsUseCase()
        val resultList = resultFlow.toList()

        // Assert
        assertEquals(1, resultList.size)
        assertEquals(mockItems, resultList.first())
    }
}
