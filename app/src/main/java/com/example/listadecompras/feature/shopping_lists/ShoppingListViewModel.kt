package com.example.listadecompras.feature.shopping_lists

import com.example.listadecompras.presentation.ShoppingList

class ShoppingListViewModel {

    private val shoppingLists = mutableListOf<ShoppingList>()


    fun getAllLists(): MutableList<ShoppingList> {
        return shoppingLists
    }


    fun searchList(name: String): ShoppingList? {
        return shoppingLists.find { it.name == name }
    }

    fun logout() {
        println("Logout")
    }
}