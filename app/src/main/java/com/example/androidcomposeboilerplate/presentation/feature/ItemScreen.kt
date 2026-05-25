package com.example.androidcomposeboilerplate.presentation.feature

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidcomposeboilerplate.domain.models.Item
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(
    viewModel: ItemViewModel,
    onShowToast: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ItemContract.Effect.ShowToast -> onShowToast(effect.message)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Items") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.handleIntent(ItemContract.Intent.RefreshItems) }) {
                Text("Refresh")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.isLoading && state.items.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null && state.items.isEmpty()) {
                Text(
                    text = state.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.items) { item ->
                        ItemRow(item)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemRow(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Price: $${item.price}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
