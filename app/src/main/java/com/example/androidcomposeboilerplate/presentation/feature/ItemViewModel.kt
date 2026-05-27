package com.example.androidcomposeboilerplate.presentation.feature

import androidx.lifecycle.ViewModel
import com.example.androidcomposeboilerplate.data.repository.UnsplashRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: UnsplashRepositoryImpl
) : ViewModel() {



}
