package com.example.listadecompras.repositories

import ShoppingItem
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.listadecompras.presentation.ShoppingListOfList
import com.google.android.engage.shopping.datamodel.ShoppingList

class ListRepository {
    private var shoppingListOfLists = mutableListOf<ShoppingListOfList>()

    fun createShoppingList(shoppingListOfList: ShoppingListOfList) {
        shoppingListOfLists.add(shoppingListOfList)
    }

    fun getShoppingListById(id: Int): ShoppingListOfList? {
        shoppingListOfLists.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

    fun removeShoppingListById(id: Int) {
        shoppingListOfLists.forEach {
            if (it.id == id) {
                shoppingListOfLists.remove(it)
                return;
            }
        }
    }

    fun updateShoppingList(id: Int, newList: ShoppingListOfList) {
        shoppingListOfLists.forEach {
            if (it.id == id) {
                val index: Int = shoppingListOfLists.indexOf(it)
                shoppingListOfLists[index] = newList
            }
        }
    }

    fun getAllLists(id: Int): List<ShoppingListOfList> {
        val lista: MutableList<ShoppingListOfList> = mutableListOf()
        shoppingListOfLists.forEach {
            if (it.id == id) {
                lista.add(it)
            }
        }
        return lista
    }

    fun addItemInList(id: Int, item: ShoppingItem) {
//        itemList.add(item)
    }

    fun removeItemById(idShoppingItem: Int, idItem: Int) {
//        itemList.forEach {
//            if (it.id == id) {
//                itemList.remove(it)
//                return
//            }
//        }
    }

    fun updateItem(id: Int, newItem: ShoppingItem) {
//        itemList.forEach {
//            if (it.id == id) {
//                val index: Int = itemList.indexOf(it)
//                itemList[index] = newItem
//                return
//            }
//        }
    }
}