package com.example.listadecompras.feature.login

import CustomError
import OnResult

class LoginViewModel {
    fun login(username: String, password: String): OnResult<Boolean> {
        if (username == "admin" && password == "admin") {
            return OnResult.Success(true)
        } else if (username.isEmpty() || password.isEmpty()) {
            return OnResult.Error(CustomError("Preencha todos os campos"))
        }
        return OnResult.Error(CustomError("Usuário ou senha incorretos"))
    }
}