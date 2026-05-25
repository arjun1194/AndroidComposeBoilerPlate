package com.example.androidcomposeboilerplate.presentation.feature

import com.example.androidcomposeboilerplate.domain.models.Item
import com.example.androidcomposeboilerplate.presentation.base.UiEffect
import com.example.androidcomposeboilerplate.presentation.base.UiIntent
import com.example.androidcomposeboilerplate.presentation.base.UiState

class ItemContract {
    sealed class Intent : UiIntent {
        object LoadItems : Intent()
        object RefreshItems : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val items: List<Item> = emptyList(),
        val error: String? = null
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }
}
