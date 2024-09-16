package com.example.listadecompras.feature.shopping_lists

import com.example.listadecompras.presentation.ShoppingList

class ShoppingListViewModel {

    private val shoppingLists = mutableListOf<ShoppingList>()

    fun getAllLists(): MutableList<ShoppingList> {
        return shoppingLists
    }

    fun searchList(name: String): List<ShoppingList?> {
        val resultSearch: List<ShoppingList?> = shoppingLists.sortedBy { it.name.contains(name) }
        return resultSearch
    }

    fun openList(id: Int) {
        println("Abrir lista com ID$id")
    }

    fun logout() {
        println("Logout")
    }
}