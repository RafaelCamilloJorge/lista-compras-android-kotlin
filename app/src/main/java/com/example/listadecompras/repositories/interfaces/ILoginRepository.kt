package com.example.listadecompras.repositories.interfaces

import OnResult

interface ILoginRepository {
    fun login(email: String, password: String): OnResult<Boolean>
    fun register(email: String, userName: String, password: String): OnResult<Boolean>
}