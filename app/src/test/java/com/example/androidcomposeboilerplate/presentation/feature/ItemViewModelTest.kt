package com.example.androidcomposeboilerplate.presentation.feature

import app.cash.turbine.test
import com.example.androidcomposeboilerplate.domain.models.Item
import com.example.androidcomposeboilerplate.domain.repository.ItemRepository
import com.example.androidcomposeboilerplate.domain.usecases.GetItemsUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ItemViewModelTest {

    private val getItemsUseCase = mockk<GetItemsUseCase>()
    private val itemRepository = mockk<ItemRepository>()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `LoadItems intent should update state with items`() = runTest {
        // Arrange
        val mockItems = listOf(Item("1", "Item 1", 10.0))
        every { getItemsUseCase() } returns flowOf(mockItems)

        // Act & Assert
        val viewModel = ItemViewModel(getItemsUseCase, itemRepository)
        
        viewModel.state.test {
            // Initial state (or loading if fast enough)
            val initialState = awaitItem()
            
            // Depends on how fast the flow emits, we can just assert the final state contains items
            // Skip loading state if present
            val finalState = awaitItem()
            assertEquals(false, finalState.isLoading)
            assertEquals(mockItems, finalState.items)
            
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `RefreshItems intent should show toast on error`() = runTest {
        // Arrange
        every { getItemsUseCase() } returns flowOf(emptyList())
        val exceptionMessage = "Network Error"
        coEvery { itemRepository.refreshItems() } throws Exception(exceptionMessage)

        val viewModel = ItemViewModel(getItemsUseCase, itemRepository)

        // Act & Assert
        viewModel.effect.test {
            viewModel.handleIntent(ItemContract.Intent.RefreshItems)
            val effect = awaitItem()
            assert(effect is ItemContract.Effect.ShowToast)
            assertEquals(exceptionMessage, (effect as ItemContract.Effect.ShowToast).message)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
