package com.example.listadecompras.feature.register

import OnResult
import com.example.listadecompras.repositories.LoginRepository

class RegisterViewModel(private val _repository: LoginRepository) {
    fun register(username: String, password: String): OnResult<Boolean> {
        return _repository.register(username, password)
    }
}