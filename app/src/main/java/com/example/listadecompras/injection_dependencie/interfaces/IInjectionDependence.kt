package com.example.listadecompras.injection_dependencie.interfaces

import java.util.Objects

interface IInjectionDependence {
    fun <T> newFactory(classType: Class<T>)
    fun <T> newLazySingleton(classType: Class<T>)
    fun <T> getInstance(classType: Class<T>): T
}