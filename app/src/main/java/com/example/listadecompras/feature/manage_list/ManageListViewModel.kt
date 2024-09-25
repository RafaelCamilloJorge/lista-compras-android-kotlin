package com.example.listadecompras.feature.manage_list

import androidx.lifecycle.ViewModel
import com.example.listadecompras.presentation.ShoppingListOfList
import com.example.listadecompras.repositories.ListRepository

class ManageListViewModel(
    private val listRepository: ListRepository
) : ViewModel() {
    fun create(newList: ShoppingListOfList) {
        val response = listRepository.createShoppingListOfList(newList)
        response.fold(onSuccess = {}, onError = { data -> println(data.messageError()) })
    }

    fun getById(id: Int) {
        val response = listRepository.getListsOfListById(id)
        response.fold(
            onSuccess = { data -> println(data.id) },
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
        val result = listRepository.removeListOfListById(id)
        result.fold(
            onSuccess = { println("Removeu") },
            onError = { data -> println(data.messageError()) })
    }

}