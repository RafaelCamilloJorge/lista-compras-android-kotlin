package com.example.listadecompras.feature.login

class LoginContracts {
    interface View {
        fun showError(message: String)
        fun navigateToListView()
    }

    interface ViewModel {
        fun login(
            email: String,
            password: String,
            onSuccess: (Boolean) -> Unit,
            onError: (String) -> Unit
        )
    }

}