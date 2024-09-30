package com.example.listadecompras.commons.validates

import android.util.Patterns

class LoginValidate {
    fun validateCreateAccount(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): String? {
        var error: String? = null
        if (username.trim().isEmpty()) {
            error = "Preencha o campo de nome de usuário"
        } else if (email.trim().isEmpty()) {
            error = "Preencha o campo de e-mail"
        } else if (!isValidEmail(email)) {
            error = "E-mail inválido"
        } else if (password.trim().isEmpty()) {
            error = "Preencha o campo de senha"
        } else if (confirmPassword.trim().isEmpty()) {
            error = "Preencha o campo de confirmação de senha"
        } else if (password != confirmPassword) {
            error = "As senhas não coincidem"
        }
        return error
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}