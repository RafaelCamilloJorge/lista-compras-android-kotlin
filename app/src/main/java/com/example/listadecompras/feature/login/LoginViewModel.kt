package com.example.listadecompras.feature.login

import com.example.listadecompras.repositories.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) {
    fun login(username: String, password: String) {
        var result = loginRepository.login(username, password)
    }
}