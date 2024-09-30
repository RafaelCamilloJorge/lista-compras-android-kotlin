package com.example.listadecompras.feature.register

class RegisterContracts {
    interface View {
        fun showError(message: String)
        fun goBackToLoginActivity()
    }

    interface ViewModel {
        fun register(
            email: String,
            username: String,
            password: String,
            onSuccess: (Boolean) -> Unit,
            onError: (String) -> Unit
        )
    }
}