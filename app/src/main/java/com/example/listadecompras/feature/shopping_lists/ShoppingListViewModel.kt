package com.example.listadecompras.feature.shopping_lists

import OnResult
import androidx.lifecycle.ViewModel
import com.example.listadecompras.presentation.ShoppingListOfList
import com.example.listadecompras.repositories.ListRepository

class ShoppingListViewModel(private val listRepository: ListRepository) : ViewModel() {

    fun getAllLists(): OnResult<List<ShoppingListOfList>> {
        return listRepository.getAllListOfLists();
    }

    fun searchList(name: String): OnResult<List<ShoppingListOfList>> {
        return listRepository.getListsOfListByName(name)
    }

    fun removeListOfList(id: Int) {
        listRepository.removeListOfListById(id)
    }

    //temporário essa fun
    fun add(item: ShoppingListOfList) {
        listRepository.createShoppingListOfList(item);
    }

    fun openList(id: Int) {
        println("Abrir lista com ID$id")
    }

    fun logout() {
        println("Logout")
    }
}