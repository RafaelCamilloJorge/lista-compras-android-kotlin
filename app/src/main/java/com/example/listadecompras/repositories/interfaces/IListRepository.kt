package com.example.listadecompras.repositories.interfaces

import OnResult
import ShoppingItem
import com.example.listadecompras.presentation.ShoppingListOfList

interface IListRepository {
    fun createShoppingListOfList(shoppingListOfList: ShoppingListOfList): OnResult<Nothing>

    fun getAllListOfLists(): OnResult<List<ShoppingListOfList>>

    fun removeListOfListById(id: Int): OnResult<Nothing>

    fun updateShoppingList(id: Int, newList: ShoppingListOfList): OnResult<Nothing>

    fun getListsOfListByName(name: String): OnResult<List<ShoppingListOfList>>

    fun getListsOfListById(id: Int): OnResult<ShoppingListOfList>

    fun addItemInList(item: ShoppingItem, idList: Int)

    fun getAllItemsOfList(idList: Int): List<ShoppingItem>

    fun removeItemById(idShoppingItem: Int, idItem: Int)

    fun updateItem(id: Int, newItem: ShoppingItem)
}