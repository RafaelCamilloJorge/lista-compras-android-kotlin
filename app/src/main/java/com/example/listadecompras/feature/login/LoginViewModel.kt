package com.example.listadecompras.feature.login

import OnResult
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun login(username: String, password: String): OnResult<Boolean> {
        return loginRepository.login(username, password)
    }
}