package com.example.listadecompras.feature.manage_list

import com.example.listadecompras.presentation.ShoppingList

class ManageListViewModel {
    private val shoppingLists = mutableListOf<ShoppingList>()
    fun addList(shoppingList: ShoppingList) {
        shoppingLists.add(shoppingList)
    }

    fun getListById(id: Int): ShoppingList? {
        shoppingLists.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

    fun removeListById(id: Int) {
        shoppingLists.forEach {
            if (it.id == id) {
                shoppingLists.remove(it)
                return;
            }
        }
    }

    fun update(id: Int, newList: ShoppingList) {
        shoppingLists.forEach {
            if (it.id == id) {
                val index: Int = shoppingLists.indexOf(it)
                shoppingLists[index] = newList
            }
        }
    }
}