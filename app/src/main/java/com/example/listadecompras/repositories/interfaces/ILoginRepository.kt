package com.example.listadecompras.repositories.interfaces

import OnResult

interface ILoginRepository {
    fun login(userName: String, password: String): OnResult<Boolean>
    fun register(userName: String, password: String): OnResult<Boolean>
}