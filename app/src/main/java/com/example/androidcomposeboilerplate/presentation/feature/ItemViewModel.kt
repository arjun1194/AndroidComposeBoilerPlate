package com.example.androidcomposeboilerplate.presentation.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcomposeboilerplate.domain.repository.ItemRepository
import com.example.androidcomposeboilerplate.domain.usecases.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ItemContract.State())
    val state: StateFlow<ItemContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ItemContract.Effect>()
    val effect: SharedFlow<ItemContract.Effect> = _effect.asSharedFlow()

    init {
        handleIntent(ItemContract.Intent.LoadItems)
    }

    fun handleIntent(intent: ItemContract.Intent) {
        when (intent) {
            is ItemContract.Intent.LoadItems -> loadItems()
            is ItemContract.Intent.RefreshItems -> refreshItems()
        }
    }

    private fun loadItems() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getItemsUseCase()
                .catch { e ->
                    _state.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { items ->
                    _state.update { it.copy(isLoading = false, items = items, error = null) }
                }
        }
    }

    private fun refreshItems() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                itemRepository.refreshItems()
            } catch (e: Exception) {
                _effect.emit(ItemContract.Effect.ShowToast(e.message ?: "Failed to refresh"))
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}
