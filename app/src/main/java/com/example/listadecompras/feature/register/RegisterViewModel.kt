package com.example.listadecompras.feature.register

import OnResult
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.LoginRepository

class RegisterViewModel(private val repository: LoginRepository) : ViewModel(),
    RegisterContracts.ViewModel {
    override fun register(
        email: String,
        username: String,
        password: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        val result = repository.register(email, username, password)
        result.fold(
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError(it.messageError())
            })
    }
}