package com.example.listadecompras.repositories

import CustomError
import OnResult
import com.example.listadecompras.domain.User
import com.example.listadecompras.repositories.interfaces.ILoginRepository

class LoginRepository : ILoginRepository {
    private var listUser = mutableListOf<User>()

    //criar um user logado
    override fun login(email: String, password: String): OnResult<Boolean> {
        try {
            var user: User? = null
            listUser.forEach {
                if (it.email.equals(email) && it.password.equals(password)) {
                    user = it
                }
            }

            if (email == "admin@gmail.com" && password == "admin") {
                return OnResult.Success(true)
            }

            if (user != null) {
                return OnResult.Success(true)
            } else {
                return OnResult.Error(CustomError("Usuário não encontrado"))
            }
        } catch (error: Exception) {
            return OnResult.Error(CustomError("Erro ao logar"))
        }
    }

    override fun register(email: String, userName: String, password: String): OnResult<Boolean> {
        try {
            listUser.add(User(email, userName, password))
            return OnResult.Success(true)
        } catch (e: Exception) {
            return OnResult.Error(CustomError(e.message ?: "Houve um erro ao registrar"))
        }
        return OnResult.Success(false)
    }

    fun logout() {
        println("Logout")
    }
}