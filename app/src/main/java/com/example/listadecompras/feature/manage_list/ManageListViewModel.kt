package com.example.listadecompras.feature.manage_list

import com.example.listadecompras.presentation.ShoppingListOfList
import com.example.listadecompras.repositories.ListRepository

class ManageListViewModel(private val listRepository: ListRepository) {

    fun create(newList: ShoppingListOfList) {
        val response = listRepository.createShoppingListOfList(newList)
        response.fold(onSuccess = {}, onError = { data -> println(data.messageError()) })
    }

    fun get(id: Int) {
        val response = listRepository.getListsOfList(id)
        response.fold(
            onSuccess = { data ->
                println(data[0].id)
            },
            onError = { error -> println(error) },
        )
    }

    fun update(id: Int, newList: ShoppingListOfList) {
        val response = listRepository.updateShoppingList(id, newList)
        response.fold(
            onSuccess = { println("Editou") },
            onError = { data -> println(data.messageError()) })
    }

    fun delete(id: Int) {
        val result = listRepository.removeShoppingListById(id)
        result.fold(
            onSuccess = { println("Removeu") },
            onError = { data -> println(data.messageError()) })
    }

}