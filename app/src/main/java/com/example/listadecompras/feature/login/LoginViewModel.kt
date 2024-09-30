package com.example.listadecompras.feature.login

import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(),
    LoginContracts.ViewModel {

    override fun login(
        email: String,
        password: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        val result = loginRepository.login(email, password)
        result.fold(
            onSuccess = { onSuccess(it) },
            onError = {
                onError(it.message ?: "Erro ao tentar logar")
            })
    }
}