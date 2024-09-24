package com.example.listadecompras.injection_dependencie

import com.example.listadecompras.injection_dependencie.interfaces.IInjectionDependence
import java.util.Objects

class InjectionDependence : IInjectionDependence {
    private val instances: MutableMap<Class<*>, Any> = mutableMapOf()

    override fun <T> newFactory(classType: Class<T>) {

    }

    override fun <T> newLazySingleton(classType: Class<T>) {
        TODO("Not yet implemented")
    }

    override fun <T> getInstance(classType: Class<T>): T {
        val newInstance =
            when (val storedInstance = instances[classType]) {
                is Lazy<*> -> storedInstance.value
                else -> {
                    try {
                        classType.getConstructor().newInstance()
                    } catch (e: Exception) {
                        throw Exception("Erro ao criar a instancia")
                    }
                }
            }
        return newInstance as T;
    }

}