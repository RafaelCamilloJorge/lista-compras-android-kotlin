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

        if(userName == "admin" && password == "admin"){
            return OnResult.Success(true)
        }

        if (user != null) {
            return OnResult.Success(true)
        } else {
            return OnResult.Error(CustomError("Preencha todos os campos"))
        }
    }

    override fun register(userName: String, password: String): OnResult<Boolean> {
        return try {
            listUser.add(User(userName, password))
            OnResult.Success(true)
        } catch (e: Exception) {
            OnResult.Error(CustomError(e.message ?: "Houve um erro ao registrar"))
        }
    }


    fun logout() {
        println("Logout")
    }
}