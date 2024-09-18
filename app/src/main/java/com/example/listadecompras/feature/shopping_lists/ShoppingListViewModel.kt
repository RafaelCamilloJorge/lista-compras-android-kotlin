package com.example.listadecompras.feature.shopping_lists

import com.example.listadecompras.presentation.ShoppingListOfList

class ShoppingListViewModel {

    private val shoppingListOfLists = mutableListOf<ShoppingListOfList>()

    fun getAllLists(): MutableList<ShoppingListOfList> {
        return shoppingListOfLists
    }

    fun searchList(name: String): List<ShoppingListOfList?> {
        val resultSearch: List<ShoppingListOfList?> = shoppingListOfLists.sortedBy { it.name.contains(name) }
        return resultSearch
    }

    fun openList(id: Int) {
        println("Abrir lista com ID$id")
    }

    fun logout() {
        println("Logout")
    }
}