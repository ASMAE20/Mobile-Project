package com.example.nextprojet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextprojet.repository.Poste_Repository


class MainViewModelFactory(private val repository: Poste_Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}