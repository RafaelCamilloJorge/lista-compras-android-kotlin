package com.example.listadecompras.repositories

import CustomError
import OnResult
import com.example.listadecompras.domain.User
import com.example.listadecompras.repositories.interfaces.ILoginRepository

class LoginRepository : ILoginRepository {
    private var listUser = mutableListOf<User>()
    override fun login(userName: String, password: String): OnResult<Boolean> {
        var user: User? = null
        listUser.forEach {
            if (it.userName.equals(userName) && it.password.equals(password)) {
                user = it
            }
        }

        if (user != null) {
            return OnResult.Success(true)
        } else {
            return OnResult.Error(CustomError("Preencha todos os campos"))
        }
    }

    override fun register(userName: String, password: String): OnResult<Nothing> {
        try {
            listUser.add(User(userName, password))
            return OnResult.SuccessNoData
        } catch (e: Exception) {
            return OnResult.Error(CustomError(e.message ?: "Houve um erro ao registrar"))
        }
    }

    fun logout() {
        println("Logout")
    }
}