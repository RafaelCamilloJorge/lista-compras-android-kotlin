package com.example.listadecompras.feature.register

import OnResult
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.LoginRepository

class RegisterViewModel(private val repository: LoginRepository) : ViewModel() {
    fun register(email: String, username: String, password: String): OnResult<Boolean> {
        return repository.register(email, username, password)
    }
}