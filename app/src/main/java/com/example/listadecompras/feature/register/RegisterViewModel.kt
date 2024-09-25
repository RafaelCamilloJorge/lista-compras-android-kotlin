package com.example.listadecompras.feature.register

import OnResult
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.LoginRepository

class RegisterViewModel(private val repository: LoginRepository) : ViewModel() {
    fun register(username: String, password: String): OnResult<Boolean> {
        return repository.register(username, password)
    }
}